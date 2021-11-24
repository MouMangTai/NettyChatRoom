package com.moumangtai.demo.server;

import com.moumangtai.demo.handler.ChatRoomHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.stereotype.Component;


/**
 * 服务端的Channel初始化器
 */
@Component
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    private ChatRoomHandler chatRoomHandler = new ChatRoomHandler();

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        /*channel.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
        channel.pipeline().addLast(new MessageCodec());
        channel.pipeline().addLast(new LoginRequestHandler());
        channel.pipeline().addLast(new SendRequestHandler());
        channel.pipeline().addLast(new GcreateRequestHandler());
        channel.pipeline().addLast(new GsendRequestHandler());
        channel.pipeline().addLast(new RegisterRequestHandler());
        channel.pipeline().addLast(new RpcRequestHandler());
        channel.pipeline().addLast(new ServerHandler());*/

        //编解码http消息
        channel.pipeline().addLast(new HttpServerCodec());
        //支持大数据流
        channel.pipeline().addLast(new ChunkedWriteHandler());
        //将解码后的多条信息整合为一条的handler
        channel.pipeline().addLast(new HttpObjectAggregator(65536));
        //建立握手
        channel.pipeline().addLast(new WebSocketServerProtocolHandler("/websocket"));
        //监听读写的handler
        channel.pipeline().addLast(chatRoomHandler);

    }
}
