package com.my.mealsys.service.impl;

import com.my.mealsys.entity.Bill;
import com.my.mealsys.enums.CodeMsgEnums;
import com.my.mealsys.mapper.BillMapper;
import com.my.mealsys.mapper.MenuBillMapper;
import com.my.mealsys.mapper.MenuMapper;
import com.my.mealsys.result.Result;
import com.my.mealsys.service.BillService;
import com.my.mealsys.vo.HistoryBillInfo;
import com.my.mealsys.vo.MenuBillInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillMapper billMapper;

    @Autowired
    private MenuBillMapper menuBillMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Map getUnfinishBill() {
        Map<String, List> dataMap = new HashMap<>();
        try {
            List<Bill> unfinishBills = billMapper.queryUnfinishBill();
            dataMap.put("unfinishBills", unfinishBills);
            return Result.success(CodeMsgEnums.SUCCESS, dataMap);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err = CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }

    @Override
    public Map getHistoryBills(String fromTime, String toTime) {
        Map<String, List> dataMap = new HashMap<>();
        try {
            List<HistoryBillInfo> historyBills = menuBillMapper.getHistoryBillsInfo(fromTime, toTime);
            dataMap.put("historyBills", historyBills);
            return Result.success(CodeMsgEnums.SUCCESS, dataMap);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err = CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }


    @Override
    public Map finishBill(String billId, Integer userId) {
        try {
            if (billMapper.checkBillUnfinish(billId) > 0) {
                return Result.error(CodeMsgEnums.BILL_ALREADY_FINISH);
            }
            if (billMapper.finishBill(billId, userId) > 0) {
                return Result.success(CodeMsgEnums.SUCCESS, null);
            } else {
                return Result.error(CodeMsgEnums.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err = CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }

    @Override
    public Map generateBill(Integer deskId, List<MenuBillInfo> menuBillInfoList) {
        try {
            //检查菜的库存够不够
            for (MenuBillInfo menuBillInfo : menuBillInfoList) {
                if(menuMapper.getQuantityById(menuBillInfo.getMenuId())<menuBillInfo.getQuantity()){
                    return Result.error(CodeMsgEnums.INSUFFICIENT_QUANTITY);
                }
            }

            //生成billId
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String orderTime = df.format(new Date());
            Bill bill = new Bill(deskId, orderTime, false);
            billMapper.generateBill(bill);

            //返回billId和orderTime
            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("billId", bill.getId() + "");
            dataMap.put("orderTime", bill.getOrderTime());

            //对对应的菜的库存进行更新
            for(MenuBillInfo menuBillInfo : menuBillInfoList){
                menuMapper.consumeMenuById(menuBillInfo);
            }

            //更新MenuBill
            Integer num = menuBillMapper.generateMenuBill(bill.getId(), menuBillInfoList);
            return num == menuBillInfoList.size() ? Result.success(CodeMsgEnums.SUCCESS, dataMap) : Result.error(CodeMsgEnums.ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err = CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }

    @Override
    public Map appendBill(Integer billId, List<MenuBillInfo> menuBillInfoList) {
        try {
            //检查菜的库存够不够
            for (MenuBillInfo menuBillInfo : menuBillInfoList) {
                if(menuMapper.getQuantityById(menuBillInfo.getMenuId())<menuBillInfo.getQuantity()){
                    return Result.error(CodeMsgEnums.INSUFFICIENT_QUANTITY);
                }
            }

            //对对应的菜的库存进行更新
            for(MenuBillInfo menuBillInfo : menuBillInfoList){
                menuMapper.consumeMenuById(menuBillInfo);
            }

            //更新MenuBill
            for (MenuBillInfo menuBillInfo : menuBillInfoList) {
                Integer quantity=menuBillMapper.getMenuQuantityByBillId(billId,menuBillInfo.getMenuId());
                if(quantity==null){
                    //没点过，插入
                    menuBillMapper.insertMenuBill(billId,menuBillInfo.getMenuId(),menuBillInfo.getQuantity());
                }else{
                    //该菜已点了，更新原有的数量
                    menuBillMapper.updateMenuQuantity(billId,menuBillInfo.getMenuId(),quantity+menuBillInfo.getQuantity());
                }
            }

            return Result.success(CodeMsgEnums.SUCCESS, null);

        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err = CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }
}
