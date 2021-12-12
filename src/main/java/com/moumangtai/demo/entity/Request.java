package com.moumangtai.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wqd
 * @since 2021-12-09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 0为发送消息，1为加好友请求，2为群聊
     */
    private Integer type;

    /**
     * 发送方id
     */
    private Long fromId;

    /**
     * 接收方id
     */
    private Long toId;

    /**
     * 0为服务器接收到，1为已经转发，2为拒绝
     */
    private Integer state;
}
