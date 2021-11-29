package com.moumangtai.demo.handler;

import com.moumangtai.demo.message.SendResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SendResponseHandler extends SimpleChannelInboundHandler<SendResponseMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendResponseMessage msg) throws Exception {
        log.error(msg.getMessage());
    }
}
