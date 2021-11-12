package com.moumangtai.demo.server;

import com.moumangtai.demo.handler.*;
import com.moumangtai.demo.protocol.MessageCodec;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.stereotype.Component;

/**
 * 服务端的Channel初始化器
 */
@Component
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        channel.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
        channel.pipeline().addLast(new MessageCodec());
        channel.pipeline().addLast(new LoginRequestHandler());
        channel.pipeline().addLast(new SendRequestHandler());
        channel.pipeline().addLast(new GcreateRequestHandler());
        channel.pipeline().addLast(new GsendRequestHandler());
        channel.pipeline().addLast(new ServerHandler());
    }
}
