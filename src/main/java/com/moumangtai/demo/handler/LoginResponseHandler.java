package com.moumangtai.demo.handler;

import com.moumangtai.demo.constant.MessageConstant;
import com.moumangtai.demo.message.LoginResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponseMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponseMessage msg) throws Exception {
        if(msg.isSuccess()){
            MessageConstant.IS_LOGIN.set(true);
        }
        MessageConstant.WAIT_FOR_LOGIN.countDown();
    }
}
