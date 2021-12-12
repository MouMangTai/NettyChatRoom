package com.moumangtai.demo.mapper;

import com.moumangtai.demo.entity.Chat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 聊天记录表 Mapper 接口
 * </p>
 *
 * @author wqd
 * @since 2021-11-30
 */
public interface ChatMapper extends BaseMapper<Chat> {

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
     * 分页获取好友列表
     * @param map
     * @return
     */
    List<Chat> getChatByPage(Map<String, Object> map);
}
