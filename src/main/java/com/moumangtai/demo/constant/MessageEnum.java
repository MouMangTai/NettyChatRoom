package com.moumangtai.demo.constant;

import lombok.AllArgsConstructor;

/**
 * 消息类型的枚举
 */

@AllArgsConstructor
public enum MessageEnum {
    CONNECT(0,"首次连接"),
    SEND(1,"发送单聊消息"),
    GROUP_SEND(2,"发送群聊消息"),
    ACK_SEND(3,"签收消息"),
    ACK_READ(4,"已读"),
    ALL_SEND(5,"广播"),
    DISCONNECT(6,"断开连接"),
    FRIEND(7,"发送好友申请"),
    AGREE_FRIEND(8,"好友申请接受应答"),
    REFUSE_FRIEND(9,"好友申请拒绝应答"),
    ACK_FRIEND(10,"好友请求应答");

    public Integer code;
    public String type;


}
