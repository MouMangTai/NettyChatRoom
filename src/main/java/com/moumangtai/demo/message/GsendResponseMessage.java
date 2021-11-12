package com.moumangtai.demo.message;

public class GsendResponseMessage extends ResponseMessage{

    public GsendResponseMessage() {
    }

    public GsendResponseMessage(int code, String message, Object data) {
        super(code, message, data);
    }

    public GsendResponseMessage(int code, String message) {
        super(code, message);
    }

    @Override
    public int getMessageType() {
        return 0;
    }
}
