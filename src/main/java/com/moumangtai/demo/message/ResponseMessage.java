package com.moumangtai.demo.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class ResponseMessage extends Message{

    private int code;

    private String message;

    private Object data;

    public ResponseMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public boolean isSuccess(){
        return code==200;
    }

    public Object getData(){
        return data;
    }
}
