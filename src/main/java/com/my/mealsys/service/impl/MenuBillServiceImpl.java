package com.my.mealsys.service.impl;

import com.my.mealsys.vo.ServeInfo;
import com.my.mealsys.enums.CodeMsgEnums;
import com.my.mealsys.mapper.MenuBillMapper;
import com.my.mealsys.result.Result;
import com.my.mealsys.service.MenuBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuBillServiceImpl implements MenuBillService {

    @Autowired
    private MenuBillMapper menuBillMapper;

    @Override
    public Map getSpecificMenuBill(String billId) {
        try {
            Map<String,List> dataMap=new HashMap<>();
            List<ServeInfo> menuBills=menuBillMapper.selectByBillId(billId);
            dataMap.put("menuBills",menuBills);
            return Result.success(CodeMsgEnums.SUCCESS,dataMap);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }


    @Override
    public Map updateMenuBillStatus(String billId, String menuId, Integer userId, Boolean status) {
        try {
            SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
            String menuTime=df.format(new Date());
            int result=menuBillMapper.updateMenuBillStatus(billId, menuId, userId, status,menuTime);
            return result>0? Result.success(CodeMsgEnums.SUCCESS,null):Result.error(CodeMsgEnums.ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }
}
