package com.moumangtai.demo.message;

import com.moumangtai.demo.constant.MessageConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendRequestMessage extends Message{

    private String userName;
    private String content;


    @Override
    public int getMessageType() {
        return MessageConstant.SEND_REQUEST_MESSAGE;
    }
}
