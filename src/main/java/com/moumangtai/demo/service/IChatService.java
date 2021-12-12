package com.moumangtai.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moumangtai.demo.entity.Chat;

import java.util.List;

/**
 * <p>
 * 聊天记录表 服务类
 * </p>
 *
 * @author wqd
 * @since 2021-11-30
 */
public interface IChatService extends IService<Chat> {

    /**
     * 批量根据消息id更新消息的状态为已经签收
     * @param ids
     */
    void updateACKSendBatch(List<Long> ids);

    /**
     * 根据用户的id查询该用户未接受到的消息，即state为0的消息
     * @param userId
     * @return
     */
    List<Chat> getUnSendChatByUserId(Long userId);

    /**
     * 分页获取好友聊天记录
     * @param userId
     * @param friendId
     * @param PageSize
     * @return
     */
    List<Object> getChatByPage(Long userId, Long friendId, Integer PageSize);
}
