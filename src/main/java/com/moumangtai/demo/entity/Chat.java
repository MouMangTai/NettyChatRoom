package com.moumangtai.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 聊天记录表
 * </p>
 *
 * @author wqd
 * @since 2021-11-30
 */
@AllArgsConstructor
@Data
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 消息类型（0为单发，1为群发）
     */
    private Integer msgType;

    /**
     * 消息发送方id
     */
    private Long fromId;

    /**
     * 消息接收方id
     */
    private Long toId;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息状态（0为已发送，1为已签收)
     */
    private Integer state;

    /**
     * 消息发送时间
     */
    private Date createDate;

}
