package com.moumangtai.demo.message;

import com.moumangtai.demo.constant.MessageConstant;
import lombok.Data;

@Data
public class RegisterResponseMessage extends ResponseMessage{


    public RegisterResponseMessage(int code, String message, Object data) {
        super(code, message, data);
    }

    public RegisterResponseMessage() {
    }

    public RegisterResponseMessage(int code, String message) {
        super(code, message);
    }

    @Override
    public int getMessageType() {
        return MessageConstant.REGISTER_RESPONSE_MESSAGE;
    }
}
