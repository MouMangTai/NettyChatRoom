package com.moumangtai.demo.session;

import io.netty.channel.Channel;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * 用于存储群会话
 */
public class GroupSession {

    private Map<String, Set<Channel>> map = new HashMap<>();

    /**
     * 创建群聊
     * @param groupName
     * @param channels
     * @return
     */
    public Set<Channel> createGroup(String groupName,Set<Channel> channels){
        Set<Channel> channelSet = map.get(groupName);
        if(channelSet==null){
            channelSet = new HashSet<>();
            for(Channel c:channels){
                channelSet.add(c);
            }
            map.put(groupName,channelSet);
            return channelSet;
        }
        return null;
    }

    /**
     * 绑定（用于加入群聊）
     * @param channel
     * @param groupName
     * @return
     */
    public Set<Channel> bind(Channel channel,String groupName){
        Set<Channel> channels = map.get(groupName);
        if(channels==null) return null;
        channels.add(channel);
        return channels;
    }

    /**
     * 解绑（用于退出群聊）
     */
    public Set<Channel> unBind(Channel channel,String groupName){
        Set<Channel> channels = map.get(groupName);
        if(channels==null) return null;
        if(channels.contains(channel)){
            channels.remove(channel);
        }
        return channels;
    }

    /**
     * 获取群聊中所有的channel
     * @param groupName
     * @return
     */
    public Set<Channel> getChannels(String groupName){
        return map.get(groupName);
    }


}
