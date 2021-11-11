package com.moumangtai.demo.handler;

import com.moumangtai.demo.message.LoginRequestMessage;
import com.moumangtai.demo.message.LoginResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerLoginHandler extends SimpleChannelInboundHandler<LoginRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage loginRequestMessage) throws Exception {
        String userName = loginRequestMessage.getUserName();
        String passWord = loginRequestMessage.getPassWord();


        log.info("接收到的用户名为：{},密码为：{}",userName,passWord);
        if(userName.equals("wqd")&&passWord.equals("123")){
            ctx.channel().writeAndFlush(new LoginResponseMessage(200,"登陆成功"));
        }else{
            ctx.channel().writeAndFlush(new LoginResponseMessage(300,"账号密码错误"));
        }
    }
}
