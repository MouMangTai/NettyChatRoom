package com.moumangtai.demo.message;

import com.moumangtai.demo.constant.MessageConstant;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequestMessage extends Message{

    private String userName;
    private String passWord;

    @Override
    public int getMessageType() {
        return MessageConstant.REGISTER_REQUEST_MESSAGE;
    }
}
