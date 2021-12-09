package com.moumangtai.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moumangtai.demo.entity.Chat;
import com.moumangtai.demo.mapper.ChatMapper;
import com.moumangtai.demo.service.IChatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 聊天记录表 服务实现类
 * </p>
 *
 * @author wqd
 * @since 2021-11-30
 */
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements IChatService {

    @Resource
    private ChatMapper chatMapper;

    /**
     * 批量根据消息id更新消息的状态为已经签收
     * @param ids
     */
    @Override
    public void updateACKSendBatch(List<Long> ids) {
        chatMapper.updateACKSendBatch(ids);
    }

    /**
     * 根据用户的id查询该用户未接受到的消息，即state为0的消息
     * @param userId
     * @return
     */
    @Override
    public List<Chat> getUnSendChatByUserId(Long userId) {
        return chatMapper.getUnSendChatByUserId(userId);
    }

}
