package com.my.mealsys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum CodeMsgEnums {

    SUCCESS(0,"成功"),

    LOGIN_FAIL(1,"登录失败"),
    SIGN_UP_FAIL(2,"注册失败"),
    LACK_PARAM(3,"缺少参数"),
    USER_ALREADY_EXIST(4,"该账号已被注册"),
    ERROR(5,"出错"),
    LOGOUT_FAIL(6,"注销失败"),
    LOGIN_FIRST(7,"请先登录"),
    ;

    private int code;
    private String msg;

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
