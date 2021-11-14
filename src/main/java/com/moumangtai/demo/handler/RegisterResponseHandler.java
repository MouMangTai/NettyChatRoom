package com.moumangtai.demo.handler;

import com.moumangtai.demo.constant.MessageConstant;
import com.moumangtai.demo.message.RegisterResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class RegisterResponseHandler extends SimpleChannelInboundHandler<RegisterResponseMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RegisterResponseMessage msg) throws Exception {
        if (msg.isSuccess()) {
            MessageConstant.IS_REGISTER.set(true);
        }
        MessageConstant.WAIT_FOR_REGISTER.countDown();
    }
}
