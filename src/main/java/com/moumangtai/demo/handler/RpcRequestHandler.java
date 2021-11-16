package com.moumangtai.demo.handler;

import com.moumangtai.demo.message.RpcRequestMessage;
import com.moumangtai.demo.message.RpcResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.lang.reflect.Method;

/**
 * 处理Rpc请求的Handler，通过反射的机制来创建对象，调用方法，最后返回数据。
 */
public class RpcRequestHandler extends SimpleChannelInboundHandler<RpcRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequestMessage msg) throws Exception {

        RpcResponseMessage rpcResponseMessage = new RpcResponseMessage();
        rpcResponseMessage.setSequenceId(msg.getSequenceId());
        rpcResponseMessage.setMessageType(msg.getMessageType());
        try {

            //Class.forName获取类对象本身的.class属性
            Class<?> aClass = Class.forName(msg.getClassName());

            //获取方法对象
            Method method = aClass.getMethod(msg.getMethodName(), msg.getParameterTypes());

            System.out.println(aClass.newInstance());

            //调用方法 .newInstance()调用构造函数生成对象
            Object invoke = method.invoke(aClass.newInstance(), msg.getParameters());

            rpcResponseMessage.setReturnValue(invoke);
        } catch (Exception e){
            e.printStackTrace();
            rpcResponseMessage.setException(new Exception(e.getMessage()));
        }

        ctx.writeAndFlush(rpcResponseMessage);

    }
}
