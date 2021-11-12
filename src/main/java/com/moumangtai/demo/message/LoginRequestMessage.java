package com.moumangtai.demo.message;

import com.moumangtai.demo.constant.MessageConstant;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequestMessage extends Message{

    //用户名
    private String userName;

    //密码
    private String passWord;

    @Override
    public int getMessageType() {
        return MessageConstant.LOGIN_REQUEST_MESSAGE;
    }
}
