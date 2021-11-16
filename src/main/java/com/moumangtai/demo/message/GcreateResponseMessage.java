package com.moumangtai.demo.message;

import com.moumangtai.demo.constant.MessageConstant;
import lombok.Data;

/**
 * 创建群的响应消息
 */
@Data
public class GcreateResponseMessage extends ResponseMessage{

    public GcreateResponseMessage() {
    }

    public GcreateResponseMessage(int code, String message, Object data) {
        super(code, message, data);
    }

    public GcreateResponseMessage(int code, String message) {
        super(code, message);
    }

    @Override
    public int getMessageType() {
        return MessageConstant.GCREATE_RESPONSE_MESSAGE;
    }
}
