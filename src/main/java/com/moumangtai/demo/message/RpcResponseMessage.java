package com.moumangtai.demo.message;

import com.moumangtai.demo.constant.MessageConstant;
import lombok.Data;

@Data
public class RpcResponseMessage extends ResponseMessage{

    // 正确放回的数据
    private Object returnValue;

    // 发生异常时的信息
    private Exception exception;


    public RpcResponseMessage() {
    }

    public RpcResponseMessage(int code, String message, Object data) {
        super(code, message, data);
    }

    public RpcResponseMessage(int code, String message) {
        super(code, message);
    }

    @Override
    public int getMessageType() {
        return MessageConstant.RPC_RESPONSE_MESSAGE;
    }
}
