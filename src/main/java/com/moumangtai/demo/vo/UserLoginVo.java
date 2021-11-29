package com.moumangtai.demo.vo;

import lombok.Data;

@Data
public class UserLoginVo {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 加密后的密码
     */
    private String passWord;
}
