package com.moumangtai.demo.message;

import com.moumangtai.demo.constant.MessageConstant;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GsendRequestMessage extends Message{


    private String groupName;

    private String content;

    @Override
    public int getMessageType() {
        return MessageConstant.GSEND_REQUEST_MESSAGE;
    }
}
