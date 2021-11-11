package com.moumangtai.demo.handler;

import com.moumangtai.demo.message.LoginResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientLoginHandler extends SimpleChannelInboundHandler<LoginResponseMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponseMessage msg) throws Exception {
        log.info("{},{}",msg.getCode(),msg.getMessage());
    }
}
