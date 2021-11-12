package com.moumangtai.demo.message;

import com.moumangtai.demo.constant.MessageConstant;
import lombok.Data;

@Data
public class SendResponseMessage extends ResponseMessage{


    public SendResponseMessage(int code, String message, Object data) {
        super(code, message, data);
    }

    public SendResponseMessage(int code, String message) {
        super(code, message);
    }

    public SendResponseMessage() {
    }

    @Override
    public int getMessageType() {
        return MessageConstant.SEND_RESPONSE_MESSAGE;
    }

}
