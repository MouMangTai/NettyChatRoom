package com.moumangtai.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 全频道聊天表
 * </p>
 *
 * @author wqd
 * @since 2021-12-07
 */
@Data
@AllArgsConstructor
public class AllChat implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 发送方id
     */
    private Long fromId;

    /**
     * 内容
     */
    private String content;

    /**
     * 消息发送时间
     */
    private Date createDate;
}
