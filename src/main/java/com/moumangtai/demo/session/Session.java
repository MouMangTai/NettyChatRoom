package com.moumangtai.demo.session;

import com.moumangtai.demo.util.RedisUtil;
import io.netty.channel.Channel;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class Session {


    private Map<String,Channel> nameToChannel = new HashMap<>();

    private Map<Channel,String> channelToName = new HashMap<>();


    @Resource
    private RedisUtil redisUtil;


    public void bind(String userName, Channel channel) {
//        boolean set = redisUtil.set(userName, channel.id().asLongText());
//        return set;
        nameToChannel.put(userName,channel);
        channelToName.put(channel,userName);
    }


    public void unbind(String userName,Channel channel) {
        nameToChannel.remove(userName);
        channelToName.remove(channel);
    }


    public io.netty.channel.Channel getChannel(String userName) {
        return nameToChannel.get(userName);
    }

}
