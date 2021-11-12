package com.moumangtai.demo.handler;

import com.moumangtai.demo.factory.SessionFactory;
import com.moumangtai.demo.message.SendRequestMessage;
import com.moumangtai.demo.message.SendResponseMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SendRequestHandler extends SimpleChannelInboundHandler<SendRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendRequestMessage msg) throws Exception {
        String userName = msg.getUserName();
        String content = msg.getContent();
        Channel channel = SessionFactory.getSession().getChannel(userName);


        if(channel!=null){
            //给聊天发送方发送一条消息
            ctx.channel().writeAndFlush(new SendResponseMessage(200,"发送成功"));
            //给聊天接收方发送一条消息
            channel.writeAndFlush(new SendResponseMessage(200,SessionFactory.getSession().getUserName(ctx.channel())+"给你发送了一条信息:"+content));
        }else{
            ctx.channel().writeAndFlush(new SendResponseMessage(500,"发送失败（用户不存在或者用户已经下线)"));
        }
    }
}
