package com.moumangtai.demo.message;

import com.moumangtai.demo.constant.MessageConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseMessage extends Message{

    private int code;

    private String message;

    @Override
    public int getMessageType() {
        return MessageConstant.LOGIN_RESPONSE_MESSAGE;
    }
}
