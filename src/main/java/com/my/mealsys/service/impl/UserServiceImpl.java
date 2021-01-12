package com.my.mealsys.service.impl;

import com.my.mealsys.enums.CodeMsgEnums;
import com.my.mealsys.mapper.UserMapper;
import com.my.mealsys.result.Result;
import com.my.mealsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map signUp(String acot, String password) {
        try {
            if(userMapper.checkUserExist(acot)>0){
                return Result.error(CodeMsgEnums.USER_ALREADY_EXIST);
            }
            return userMapper.signUpUser(acot,password)>0 ? Result.success(CodeMsgEnums.SUCCESS,null):Result.error(CodeMsgEnums.SIGN_UP_FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }
    }

    @Override
    public Map login(String acot, String password, HttpSession httpSession) {
        try {
            if(userMapper.checkUserAcotPsw(acot, password)>0){
                httpSession.setAttribute("user",acot);
                return Result.success(CodeMsgEnums.SUCCESS,null);
            }else{
                return Result.error(CodeMsgEnums.LOGIN_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CodeMsgEnums err=CodeMsgEnums.ERROR;
            err.setMsg(e.getMessage());
            return Result.error(err);
        }

    }
}
