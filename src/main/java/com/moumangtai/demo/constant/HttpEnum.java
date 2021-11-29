package com.moumangtai.demo.constant;


/**
 * http响应码枚举
 */
public enum HttpEnum {

    SUCCESS(200,"操作成功"),
    ERROR(500,"操作失败");

    private Integer code;

    private String msg;

    HttpEnum(int code ,String msg){
        this.code=code;
        this.msg=msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
