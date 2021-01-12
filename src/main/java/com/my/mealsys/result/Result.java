package com.my.mealsys.result;


import com.my.mealsys.enums.CodeMsgEnums;

import java.util.HashMap;
import java.util.Map;

public class Result {

    public static Map success(CodeMsgEnums code,Map data){
        Map<String,Object> result=new HashMap<>();
        result.put("code",code.getCode());
        result.put("data",data);
        return result;
    }

    public static Map error(CodeMsgEnums code){
        Map<String,String> errResult=new HashMap<>();
        errResult.put("code",code.getCode()+"");
        errResult.put("msg",code.getMsg());
        return errResult;
    }
}
