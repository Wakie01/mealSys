package com.my.mealsys.contronller;

import com.my.mealsys.enums.CodeMsgEnums;
import com.my.mealsys.result.Result;
import com.my.mealsys.service.MenuBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class MenuBillController {

    @Autowired
    private MenuBillService menuBillService;

    /**
     * 获取某单号的详细点菜信息
     * @param billId
     * @param httpSession
     * @return
     */
    @PostMapping("/getSpecificMenuBill")
    public Map getSpecificMenuBill(@RequestParam("billId") String billId, HttpSession httpSession){
        try {
//            if(httpSession.getAttribute("user")==null){
//                return Result.error(CodeMsgEnums.LOGIN_FIRST);
//            }
            return menuBillService.getSpecificMenuBill(billId);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }

    /**
     * 对某分单号的某份菜进行上菜记录
     * @param billId
     * @param menuId
     * @param httpSession
     * @return
     */
    @PostMapping("/updateMenuBillStatus")
    public Map updateMenuBillStatus(@RequestParam("billId") String billId,@RequestParam("menuId") String menuId,@RequestParam("status") String status, HttpSession httpSession){
        try {
//            Integer userId=(Integer)httpSession.getAttribute("user");
//            if(userId==null){
//                return Result.error(CodeMsgEnums.LOGIN_FIRST);
//            }
            Integer userId=1;    //暂定
            return menuBillService.updateMenuBillStatus(billId, menuId,userId, status.equals("1"));
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }
}
