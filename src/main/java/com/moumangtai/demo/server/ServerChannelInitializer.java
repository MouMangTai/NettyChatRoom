package com.moumangtai.demo.server;

import com.moumangtai.demo.handler.ServerHandler;
import com.moumangtai.demo.handler.ServerLoginHandler;
import com.moumangtai.demo.handler.ServerSendHandler;
import com.moumangtai.demo.protocol.MessageCodec;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.stereotype.Component;

@Component
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        channel.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
        channel.pipeline().addLast(new MessageCodec());
        channel.pipeline().addLast(new ServerLoginHandler());
        channel.pipeline().addLast(new ServerSendHandler());
        channel.pipeline().addLast(new ServerHandler());
    }
}
