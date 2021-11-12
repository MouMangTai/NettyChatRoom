package com.moumangtai.demo.handler;

import com.moumangtai.demo.message.SendResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientSendHandler extends SimpleChannelInboundHandler<SendResponseMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendResponseMessage msg) throws Exception {
        System.out.println(msg.getMessage());
    }
}
