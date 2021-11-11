package com.moumangtai.demo.handler;

import com.moumangtai.demo.message.LoginRequestMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;


@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("连接到服务器{}成功",ctx.channel().remoteAddress());


        Scanner sc = new Scanner(System.in);
        new Thread(()->{
            System.out.println("请先登陆");
            System.out.println("输入您的用户名");
            String userName = sc.nextLine();
            System.out.println("输入您的密码");
            String passWord = sc.nextLine();

            //发送到服务器校验
            LoginRequestMessage loginRequestMessage = new LoginRequestMessage(userName, passWord);
            ctx.channel().writeAndFlush(loginRequestMessage);
        }).start();
    }
}
