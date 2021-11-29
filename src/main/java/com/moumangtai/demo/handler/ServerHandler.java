package com.moumangtai.demo.handler;

import com.moumangtai.demo.factory.SessionFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.error("有链接建立:{}",ctx.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.error("{}的连接断开",ctx.channel().remoteAddress());
        SessionFactory.getSession().unbind(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("服务器发生异常 ，异常为：{}",ctx.channel().remoteAddress(),cause);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.error("{}",msg);

        ctx.writeAndFlush(msg);
    }
}
