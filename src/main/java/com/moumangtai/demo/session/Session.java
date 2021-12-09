package com.moumangtai.demo.session;

import io.netty.channel.Channel;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 用于存储用户会话
 */
@Component
@Data
public class Session {


    private Map<Long,Channel> IdToChannel = new HashMap<>();

    private Map<Channel,Long> ChannelToId = new HashMap<>();

    /**
     * 绑定
     * @param userId
     * @param channel
     */
    public void bind(Long userId, Channel channel) {
        IdToChannel.put(userId,channel);
        ChannelToId.put(channel,userId);
    }

    /**
     * 解绑
     * @param channel
     */
    public void unbind(Channel channel) {
        IdToChannel.remove(getUserId(channel));
        ChannelToId.remove(channel);
    }

    /**
     * 解绑
     * @param userId
     */
    public void unbind(Long userId){
        IdToChannel.get(userId);
        ChannelToId.remove(getChannel(userId));
    }

    /**
     * 根据用户Id获取channel
     * @param userId
     * @return
     */
    public Channel getChannel(Long userId) {
        return IdToChannel.get(userId);
    }

    /**
     * 根据channel获取用户Id
     * @param channel
     * @return
     */
    public Long getUserId(Channel channel){
        return ChannelToId.get(channel);
    }

    /**
     * 批量获取channel
     * @param users
     * @return
     */
    public Set<Channel> getChannelBatch(Set<Long> users){
        Set<Channel> set = new HashSet<>();
        users.stream().forEach(user -> {
            set.add(getChannel(user));
        });
        return set;
    }

    /**
     * 获取所有在线用户的Id
     *
     * @return
     */
    public Collection<Long> onlineUsers(){
        return ChannelToId.values();
    }



}
