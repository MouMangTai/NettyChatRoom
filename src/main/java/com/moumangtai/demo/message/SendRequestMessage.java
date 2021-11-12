package com.moumangtai.demo.message;

import com.moumangtai.demo.constant.MessageConstant;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SendRequestMessage extends Message{

    //发送对象的用户名
    private String userName;

    //发送内容
    private String content;


    @Override
    public int getMessageType() {
        return MessageConstant.SEND_REQUEST_MESSAGE;
    }
}
