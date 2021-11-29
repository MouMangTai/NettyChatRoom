package com.moumangtai.demo.handler;

import com.moumangtai.demo.message.GcreateResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GcreateResponseHandler extends SimpleChannelInboundHandler<GcreateResponseMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GcreateResponseMessage msg) throws Exception {
        log.error(msg.getMessage());
    }
}
