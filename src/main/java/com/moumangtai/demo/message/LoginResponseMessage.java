package com.moumangtai.demo.message;

import com.moumangtai.demo.constant.MessageConstant;
import lombok.Data;

@Data
public class LoginResponseMessage extends ResponseMessage{


    public LoginResponseMessage() {
        super();
    }

    public LoginResponseMessage(int code, String message) {
        super(code, message);
    }

    @Override
    public int getMessageType() {
        return MessageConstant.LOGIN_RESPONSE_MESSAGE;
    }

}
