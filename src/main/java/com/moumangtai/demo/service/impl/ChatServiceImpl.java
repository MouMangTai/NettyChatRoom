package com.moumangtai.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moumangtai.demo.entity.Chat;
import com.moumangtai.demo.entity.User;
import com.moumangtai.demo.mapper.ChatMapper;
import com.moumangtai.demo.mapper.UserMapper;
import com.moumangtai.demo.service.IChatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    private UserMapper userMapper;

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

    /**
     * 分页获取好友聊天记录
     * @param userId
     * @param friendId
     * @param PageSize
     * @return
     */
    @Override
    public List<Object> getChatByPage(Long userId, Long friendId, Integer PageSize) {
        List<Object> res = new ArrayList<>();
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("userId",userId);
        parameters.put("friendId",friendId);
        parameters.put("PageSize",PageSize);
        List<Chat> chats = chatMapper.getChatByPage(parameters);
        chats.forEach(e->{
            Map<String,Object> map = new HashMap<>();
            User user = userMapper.selectById(e.getFromId());
            map.put("type",e.getFromId()==userId?1:0);
            map.put("user",user);
            map.put("content",e.getContent());
            res.add(map);
        });
        return res;
    }

}
