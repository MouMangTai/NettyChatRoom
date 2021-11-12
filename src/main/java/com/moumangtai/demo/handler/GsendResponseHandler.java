package com.moumangtai.demo.handler;

import com.moumangtai.demo.message.GsendResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class GsendResponseHandler extends SimpleChannelInboundHandler<GsendResponseMessage> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GsendResponseMessage msg) throws Exception {
        System.out.println(msg.getMessage());
    }
}
