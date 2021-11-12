package com.moumangtai.demo.session;

import io.netty.channel.Channel;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 用于存储用户会话
 */
@Component
@Data
public class Session {


    private Map<String,Channel> nameToChannel = new HashMap<>();

    private Map<Channel,String> channelToName = new HashMap<>();

    /**
     * 绑定
     * @param userName
     * @param channel
     */
    public void bind(String userName, Channel channel) {
        nameToChannel.put(userName,channel);
        channelToName.put(channel,userName);
    }

    /**
     * 解绑
     * @param channel
     */
    public void unbind(Channel channel) {
        nameToChannel.remove(getUserName(channel));
        channelToName.remove(channel);
    }

    /**
     * 解绑
     * @param userName
     */
    public void unbind(String userName){
        nameToChannel.get(userName);
        channelToName.remove(getChannel(userName));
    }

    /**
     * 根据用户名获取channel
     * @param userName
     * @return
     */
    public Channel getChannel(String userName) {
        return nameToChannel.get(userName);
    }

    /**
     * 根据channel获取用户名
     * @param channel
     * @return
     */
    public String getUserName(Channel channel){
        return channelToName.get(channel);
    }

    /**
     * 批量获取channel
     * @param users
     * @return
     */
    public Set<Channel> getChannelBatch(Set<String> users){
        Set<Channel> set = new HashSet<>();
        users.stream().forEach(user -> {
            set.add(getChannel(user));
        });
        return set;
    }

}
