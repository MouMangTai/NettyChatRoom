package com.moumangtai.demo.handler;

import com.moumangtai.demo.factory.GroupSessionFactory;
import com.moumangtai.demo.factory.SessionFactory;
import com.moumangtai.demo.message.GsendRequestMessage;
import com.moumangtai.demo.message.GsendResponseMessage;
import com.moumangtai.demo.session.GroupSession;
import com.moumangtai.demo.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Set;

public class GsendRequestHandler extends SimpleChannelInboundHandler<GsendRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GsendRequestMessage msg) throws Exception {
        String groupName = msg.getGroupName();
        String content = msg.getContent();
        GroupSession groupSession = GroupSessionFactory.getGroupSession();
        Session session = SessionFactory.getSession();
        Set<Channel> channels = groupSession.getChannels(groupName);
        if(channels==null){
            ctx.channel().writeAndFlush(new GsendResponseMessage(500,"群聊不存在"));
        }else{
            channels.stream().forEach(channel -> {
                if(session.getUserName(channel).equals(session.getUserName(ctx.channel()))) {
                    channel.writeAndFlush(new GsendResponseMessage(200,"发送群聊消息成功"));
                }else{
                    channel.writeAndFlush(new GsendResponseMessage(200,"["+groupName+"]"+ session.getUserName(ctx.channel()) +":"+content));
                }
            });
        }
    }
}
