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
    LOGOUT_FAIL(6,"用户已注销"),
    LOGIN_FIRST(7,"请先登录"),
    BILL_ALREADY_FINISH(8,"该账单已经结束"),
    INSUFFICIENT_QUANTITY(9,"库存不足"),
    IMAGE_UPLOAD_FAIL(10,"图片上传出错"),
    USER_ONLINE(11,"用户已登录"),
    USER_OFFLINE(12,"用户未登录"),
    ;

    private int code;
    private String msg;

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
