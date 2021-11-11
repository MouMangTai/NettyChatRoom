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

    public boolean isSuccess(){
        return code==200;
    }
}
