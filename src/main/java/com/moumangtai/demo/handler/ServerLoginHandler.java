package com.moumangtai.demo.handler;

import com.moumangtai.demo.factory.SessionFactory;
import com.moumangtai.demo.message.LoginRequestMessage;
import com.moumangtai.demo.message.LoginResponseMessage;
import com.moumangtai.demo.service.IUserService;
import com.moumangtai.demo.service.impl.UserServiceImpl;
import com.moumangtai.demo.util.SpringUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerLoginHandler extends SimpleChannelInboundHandler<LoginRequestMessage> {

    private static IUserService iUserService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage loginRequestMessage) throws Exception {
        String userName = loginRequestMessage.getUserName();
        String passWord = loginRequestMessage.getPassWord();

        iUserService = SpringUtil.getBean(UserServiceImpl.class);

        if(iUserService.checkLogin(userName,passWord)){
            //绑定用户名和channel
            SessionFactory.getSession().bind(userName, ctx.channel());
            Channel channel = SessionFactory.getSession().getChannel(userName);
            log.info("{}绑定了channel",channel.remoteAddress());
            ctx.channel().writeAndFlush(new LoginResponseMessage(200,"登陆成功",null));
        }else{
            ctx.channel().writeAndFlush(new LoginResponseMessage(300,"账号密码错误",null));
        }
    }
}
