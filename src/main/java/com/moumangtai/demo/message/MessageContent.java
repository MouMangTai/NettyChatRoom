package com.moumangtai.demo.message;

import lombok.Data;

@Data
public class MessageContent {

    /**
     * 发送方id
     */
    private Long fromId;

    /**
     * 接收方id（根据messageType可能是userId或groupId）
     */
    private Long toId;

    /**
     * 内容
     */
    private String content;
}
