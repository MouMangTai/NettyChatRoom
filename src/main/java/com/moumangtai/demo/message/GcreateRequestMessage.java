package com.moumangtai.demo.message;

import com.moumangtai.demo.constant.MessageConstant;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * 创建群的请求命令消息
 */
@Data
@AllArgsConstructor
public class GcreateRequestMessage extends Message{

    //群聊名称
    private String groupName;

    //创建群聊预先拉取的用户
    private Set<String> users;

    @Override
    public int getMessageType() {
        return MessageConstant.GCREATE_REQUEST_MESSAGE;
    }
}
