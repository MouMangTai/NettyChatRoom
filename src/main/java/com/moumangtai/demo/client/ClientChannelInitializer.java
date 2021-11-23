package com.moumangtai.demo.client;

import com.moumangtai.demo.handler.*;
import com.moumangtai.demo.protocol.MessageCodec;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * 客户端Channel初始化类
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
//        channel.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
        channel.pipeline().addLast(new MessageCodec());
        channel.pipeline().addLast(new LoginResponseHandler());
        channel.pipeline().addLast(new SendResponseHandler());
        channel.pipeline().addLast(new GcreateResponseHandler());
        channel.pipeline().addLast(new GsendResponseHandler());
        channel.pipeline().addLast(new RegisterResponseHandler());
        channel.pipeline().addLast(new RpcResponseHandler());
        channel.pipeline().addLast(new ClientHandler());

    }
}
