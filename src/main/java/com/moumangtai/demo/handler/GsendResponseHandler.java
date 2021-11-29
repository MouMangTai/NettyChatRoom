package com.moumangtai.demo.handler;

import com.moumangtai.demo.message.GsendResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class GsendResponseHandler extends SimpleChannelInboundHandler<GsendResponseMessage> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GsendResponseMessage msg) throws Exception {
        log.error(msg.getMessage());
    }
}
