package com.moumangtai.demo.message;

import com.moumangtai.demo.constant.MessageConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestMessage extends Message{
    private String userName;
    private String passWord;

    @Override
    public int getMessageType() {
        return MessageConstant.LOGIN_REQUEST_MESSAGE;
    }
}
