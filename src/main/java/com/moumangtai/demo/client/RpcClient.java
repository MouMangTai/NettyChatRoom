package com.moumangtai.demo.client;

import com.moumangtai.demo.constant.MessageConstant;
import com.moumangtai.demo.constant.SequenceIdGenerator;
import com.moumangtai.demo.message.RpcRequestMessage;
import com.moumangtai.demo.service.IUserService;
import com.moumangtai.demo.service.impl.UserServiceImpl;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultPromise;

import java.lang.reflect.Proxy;

/**
 * 用于Rpc的客户端启动器
 */
public class RpcClient {

    public static void main(String[] args) {
        IUserService iUserService = new UserServiceImpl();
        IUserService proxyService = getProxyService(iUserService.getClass());
        System.out.println(proxyService.RpcTest(200));
        System.out.println(proxyService.RpcTest(300));
        System.out.println(proxyService.RpcTest(400));
        System.out.println(proxyService.RpcTest(200));
        System.out.println(proxyService.RpcTest(300));
        System.out.println(proxyService.RpcTest(400));
        System.out.println(proxyService.RpcTest(200));
        System.out.println(proxyService.RpcTest(300));
        System.out.println(proxyService.RpcTest(400));
        System.out.println(proxyService.RpcTest(200));
        System.out.println(proxyService.RpcTest(300));
        System.out.println(proxyService.RpcTest(400));
    }

    public static <T> T getProxyService(Class<T> service){
        ClassLoader classLoader = service.getClassLoader();
        int sequenceId = SequenceIdGenerator.getSequenceId();
        // 生成代理对象实例
        Object o = Proxy.newProxyInstance(classLoader, service.getInterfaces(), (proxy, method, args) -> {
            // proxy 代理对象 method 代理对象执行的方法 args 执行方法的参数列表
            // 1. 将方法调用转换为 消息对象
            RpcRequestMessage rpcRequestMessage = new RpcRequestMessage(
                    sequenceId,
                    service.getName(),
                    method.getName(),
                    method.getReturnType(),
                    method.getParameterTypes(),
                    args
            );
            // 2. 将请求发送出去
            getChannel().writeAndFlush(rpcRequestMessage).addListener(future -> {
                if(!future.isSuccess()){
                    System.out.println(future.cause());
                }
            });

            // 3. 准备一个Promise对象来接受结果并放入map容器中   指定Promise异步接受结果的线程
            DefaultPromise promise = new DefaultPromise(getChannel().eventLoop());
            MessageConstant.putPromise(sequenceId,promise);

            // 4. 同步等待结果
            promise.await();

            // 5. 返回数据
            if(promise.isSuccess()){
                return promise.getNow();
            }else{
                return promise.cause();
            }

        });
        return (T) o;
    }

    public static volatile Channel channel = null;

    private static final Object LOCK = new Object();

    public static Channel getChannel(){
        if(channel==null){
            synchronized (LOCK){
                if(channel==null){
                    initChannel();
                }
            }
        }
        return channel;
    }


    /**
     * 初始化Channel
     */
    public static void initChannel(){
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer());
            ChannelFuture future = bootstrap.connect("152.136.230.44",8485).sync();
            channel = future.channel();


            // 以异步的方式去关闭channel，防止线程堵塞。
            channel.closeFuture().addListener( e -> {
                group.shutdownGracefully();
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
