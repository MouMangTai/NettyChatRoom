package com.moumangtai.demo.server;

import com.moumangtai.demo.handler.MessageHandler;
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

    private MessageHandler messageHandler = new MessageHandler();

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        // websocket 基于 http 协议，编解码 http 消息
        channel.pipeline().addLast(new HttpServerCodec());
        // 支持大数据流，提供对数据流的读写
        channel.pipeline().addLast(new ChunkedWriteHandler());
        // 对 http的消息进行聚合，聚合成 FullHttpRequest 和 FullHttpResponse
        channel.pipeline().addLast(new HttpObjectAggregator(65536));
        // 帮助管理 websocket 建立握手动作，包含 websocket 的关闭， ping， pong 等
        // 对于 websocket 来讲，都是以 frame 来进行传输的，不同的数据类型对应的 frame 也不同
        // 即 TextWebSocketFrame，专门用于处理文本对象
        channel.pipeline().addLast(new WebSocketServerProtocolHandler("/websocket"));
        // 自定义handler，管理生命周期以及读写
        channel.pipeline().addLast(messageHandler);
    }
}
