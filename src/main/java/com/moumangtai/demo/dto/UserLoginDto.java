package com.moumangtai.demo.dto;

import lombok.Data;

@Data
public class UserLoginDto {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 加密后的密码
     */
    private String passWord;
}
