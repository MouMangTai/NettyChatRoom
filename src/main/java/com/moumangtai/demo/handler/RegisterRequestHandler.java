package com.moumangtai.demo.handler;

import com.moumangtai.demo.message.RegisterRequestMessage;
import com.moumangtai.demo.message.RegisterResponseMessage;
import com.moumangtai.demo.service.IUserService;
import com.moumangtai.demo.service.impl.UserServiceImpl;
import com.moumangtai.demo.util.SpringUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class RegisterRequestHandler extends SimpleChannelInboundHandler<RegisterRequestMessage> {

    private static IUserService iUserService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RegisterRequestMessage msg) throws Exception {
        String userName = msg.getUserName();
        String passWord = msg.getPassWord();

        iUserService = SpringUtil.getBean(UserServiceImpl.class);

        Boolean register = iUserService.register(userName, passWord);
        if (register){
            ctx.channel().writeAndFlush(new RegisterResponseMessage(200,"注册成功"));
        }else{
            ctx.channel().writeAndFlush(new RegisterResponseMessage(500,"用户名已经存在"));
        }
    }
}
