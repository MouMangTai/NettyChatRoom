package com.moumangtai.demo.message;


import lombok.Data;

/**
 * 消息头
 */
@Data
public class Message {

    /**
     * 消息id（自增）
     */
    private Long messageId;

    /**
     * 消息类型（利用枚举）
     */
    private Integer messageType;

    /**
     * 消息内容体
     */
    private MessageContent messageContent;

    /**
     * 扩展信息
     */
    private String extend;
}
