package com.moumangtai.demo.handler;

import com.moumangtai.demo.message.GcreateResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class GcreateResponseHandler extends SimpleChannelInboundHandler<GcreateResponseMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GcreateResponseMessage msg) throws Exception {
        System.out.println(msg.getMessage());
    }
}
