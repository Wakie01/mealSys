package com.my.mealsys.contronller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.my.mealsys.enums.CodeMsgEnums;
import com.my.mealsys.result.Result;
import com.my.mealsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public Map signUp(@RequestBody String reqData){
        String acot,password;
        try {
            JSONObject jsonObject= JSON.parseObject(reqData);
            if(jsonObject.containsKey("acot")){
                acot=jsonObject.getString("acot");
            }else{
                return Result.error(CodeMsgEnums.LACK_PARAM);
            }
            if(jsonObject.containsKey("password")){
                password=jsonObject.getString("password");
            }else{
                return Result.error(CodeMsgEnums.LACK_PARAM);
            }
            return userService.signUp(acot,password);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }

    @PostMapping("/login")
    public Map login(@RequestBody String reqData, HttpSession httpSession){
        String acot,password;
        try {
            JSONObject jsonObject= JSON.parseObject(reqData);
            if(jsonObject.containsKey("acot")){
                acot=jsonObject.getString("acot");
            }else{
                return Result.error(CodeMsgEnums.LACK_PARAM);
            }
            if(jsonObject.containsKey("password")){
                password=jsonObject.getString("password");
            }else{
                return Result.error(CodeMsgEnums.LACK_PARAM);
            }
            return userService.login(acot,password,httpSession);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }

    @PostMapping("/logout")
    public Map logout(HttpSession httpSession){
        try {
            if(httpSession.getAttribute("user")==null){
                return Result.error(CodeMsgEnums.LOGOUT_FAIL);
            }else{
                httpSession.removeAttribute("user");
                return Result.success(CodeMsgEnums.SUCCESS,null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }


    @PostMapping("/checkOnline")
    public Map checkOnline(HttpSession httpSession){
        try {
            return httpSession.getAttribute("user")!=null ? Result.success(CodeMsgEnums.USER_ONLINE,null):
                    Result.success(CodeMsgEnums.USER_OFFLINE,null);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }






























}
