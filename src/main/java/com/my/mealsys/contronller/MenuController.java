package com.my.mealsys.contronller;

import com.my.mealsys.enums.CodeMsgEnums;
import com.my.mealsys.result.Result;
import com.my.mealsys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/getMenuInfo")
    public Map getMenuInfo(HttpSession httpSession){
        try {
            if(httpSession.getAttribute("user")==null){
                return Result.error(CodeMsgEnums.LOGIN_FIRST);
            }
            return menuService.getMenuInfo();
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }

}
