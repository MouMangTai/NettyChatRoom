package com.moumangtai.demo.handler;

import com.moumangtai.demo.constant.MessageConstant;
import com.moumangtai.demo.message.RpcResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.Promise;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RpcResponseHandler extends SimpleChannelInboundHandler<RpcResponseMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcResponseMessage msg) throws Exception {
        log.error("{}",msg);

        Promise<Object> promise = MessageConstant.getPromise(msg.getSequenceId());
        if(promise!=null) {
            Object returnValue = msg.getReturnValue();
            Exception exception = msg.getException();
            if (exception == null) {
                promise.setSuccess(returnValue);
            } else {
                promise.setFailure(exception);
            }
        }
    }
}
