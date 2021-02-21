package com.my.mealsys.contronller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.my.mealsys.enums.CodeMsgEnums;
import com.my.mealsys.result.Result;
import com.my.mealsys.service.BillService;
import com.my.mealsys.vo.MenuBillInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
public class BillController {

    @Autowired
    private BillService billService;

    /**
     * 获取未完成的订单,即当前订单
     * @param httpSession
     * @return
     */
    @PostMapping("/getUnfinishBill")
    public Map getUnfinishBill(HttpSession httpSession){
        try {
//            if(httpSession.getAttribute("user")==null){
//                return Result.error(CodeMsgEnums.LOGIN_FIRST);
//            }
            return billService.getUnfinishBill();
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }

    /**
     * 获取历史账单
     * @param times
     * @param httpSession
     * @return
     */
    @PostMapping("/getHistoryBills")
    public Map getHistoryBills(@RequestBody String times, HttpSession httpSession){
        String fromTime,toTime;
        try {
//            if(httpSession.getAttribute("user")==null){
//                return Result.error(CodeMsgEnums.LOGIN_FIRST);
//            }

            JSONObject jsonObject= JSON.parseObject(times);
            if(jsonObject.containsKey("fromTime")){
                fromTime=jsonObject.getString("fromTime");
            }else{
                return Result.error(CodeMsgEnums.LACK_PARAM);
            }
            if(jsonObject.containsKey("toTime")){
                toTime=jsonObject.getString("toTime");
            }else{
                return Result.error(CodeMsgEnums.LACK_PARAM);
            }
            return billService.getHistoryBills(fromTime,toTime);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }

    }

    @PostMapping("/finishBill")
    public Map finishBill(@RequestParam("billId") String billId,HttpSession httpSession){
        try {
//            Integer userId=(Integer) httpSession.getAttribute("user");
//            if(userId==null){
//                return Result.error(CodeMsgEnums.LOGIN_FIRST);
//            }
            Integer userId=1;   //假定
            return billService.finishBill(billId,userId);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }

    /**
     * 生成新的账单
     * @param billInfo
     * @return
     */
    @PostMapping("/generateBill")
    public Map generateBill(@RequestBody String billInfo){
        try {
            JSONObject jsonObject=JSON.parseObject(billInfo);
            Integer deskId=jsonObject.getInteger("deskId");
            List<MenuBillInfo> menuBillInfoList=JSONArray.parseArray(jsonObject.getString("menuBill"),MenuBillInfo.class);
            return billService.generateBill(deskId,menuBillInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());

            return Result.error(err);
        }
    }

    /**
     * 在旧的账单上继续加菜
     * @param billInfo
     * @return
     */
    @PostMapping("/appendBill")
    public Map appendBill(@RequestBody String billInfo){
        try {
            JSONObject jsonObject=JSON.parseObject(billInfo);
            Integer billId=jsonObject.getInteger("billId");
            List<MenuBillInfo> menuBillInfoList=JSONArray.parseArray(jsonObject.getString("menuBill"),MenuBillInfo.class);
            return billService.appendBill(billId,menuBillInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err = CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }


}
