package com.moumangtai.demo.client;

import com.moumangtai.demo.handler.ClientHandler;
import com.moumangtai.demo.handler.ClientLoginHandler;
import com.moumangtai.demo.handler.ClientSendHandler;
import com.moumangtai.demo.protocol.MessageCodec;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
//        channel.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
        channel.pipeline().addLast(new MessageCodec());
        channel.pipeline().addLast(new ClientLoginHandler());
        channel.pipeline().addLast(new ClientSendHandler());
        channel.pipeline().addLast(new ClientHandler());
    }
}
