package com.moumangtai.demo.handler;

import com.moumangtai.demo.factory.GroupSessionFactory;
import com.moumangtai.demo.factory.SessionFactory;
import com.moumangtai.demo.message.GcreateRequestMessage;
import com.moumangtai.demo.message.GcreateResponseMessage;
import com.moumangtai.demo.session.GroupSession;
import com.moumangtai.demo.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Set;

public class GcreateRequestHandler extends SimpleChannelInboundHandler<GcreateRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GcreateRequestMessage msg) throws Exception {
        Set<String> users = msg.getUsers();
        String groupName = msg.getGroupName();
        Session session = SessionFactory.getSession();
        GroupSession groupSession = GroupSessionFactory.getGroupSession();

        Set<Channel> group = groupSession.createGroup(groupName, session.getChannelBatch(users));
        if(group==null){
            ctx.channel().writeAndFlush(new GcreateResponseMessage(500,"群聊已经存在"));
        }else{
            group.stream().forEach(channel -> {
                if(session.getUserName(channel).equals(session.getUserName(ctx.channel()))){
                    ctx.channel().writeAndFlush(new GcreateResponseMessage(200,"群聊["+groupName+"]创建成功"));
                }else{
                    channel.writeAndFlush(new GcreateResponseMessage(200,session.getUserName(ctx.channel())+"创建了群聊 ["+groupName+"] 并将您拉入群内"));
                }
            });
        }

    }
}
