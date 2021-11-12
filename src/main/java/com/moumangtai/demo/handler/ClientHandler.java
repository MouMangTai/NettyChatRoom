package com.moumangtai.demo.handler;

import com.moumangtai.demo.constant.MessageConstant;
import com.moumangtai.demo.message.GcreateRequestMessage;
import com.moumangtai.demo.message.GsendRequestMessage;
import com.moumangtai.demo.message.LoginRequestMessage;
import com.moumangtai.demo.message.SendRequestMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("连接到服务器{}成功",ctx.channel().remoteAddress());

        new Thread(()->{
            Scanner sc = new Scanner(System.in);
            System.out.println("------------------");
            System.out.println("|     请先登陆     |");
            System.out.println("------------------");
            System.out.println("输入您的用户名");
            String userName = sc.nextLine();
            System.out.println("输入您的密码");
            String passWord = sc.nextLine();

            //发送到服务器校验
            LoginRequestMessage loginRequestMessage = new LoginRequestMessage(userName, passWord);
            ctx.channel().writeAndFlush(loginRequestMessage);
            log.info("执行了发送请求");


            try {
                MessageConstant.WAIT_FOR_LOGIN.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(MessageConstant.IS_LOGIN.get()){
                while(true){
                    System.out.println("------------------------------------------");
                    System.out.println("|         登陆成功(请根据提示输入指令）         |");
                    System.out.println("------------------------------------------");
                    System.out.println("| send    [userName]  [content] 发送单聊消息 |");
                    System.out.println("| gsend   [groupName] [content] 发送群聊消息 |");
                    System.out.println("| gcreate [groupName] [user1..] 创建群聊     |");
                    System.out.println("------------------------------------------");
                    String s = sc.nextLine();
                    sendCommand(ctx,s);
                }
            }else{
                System.out.println("------------------------");
                System.out.println("|     登陆失败重新登陆     |");
                System.out.println("------------------------");
                ctx.channel().close();
                return ;
            }
        }).start();

    }

    private boolean sendCommand(ChannelHandlerContext ctx,String s) {
        String[] splits = s.split(" ");
        log.info("{},{},{}",splits[0],splits[1],splits[2]);
        switch(splits[0]){
            case "send" :
                ctx.channel().writeAndFlush(new SendRequestMessage(splits[1],splits[2]));
                break; //可选
            case "gcreate" :
                Set<String> users = new HashSet<>(Arrays.asList(splits[2].split(",")));
                ctx.channel().writeAndFlush(new GcreateRequestMessage(splits[1],users));
                break;
            case "gsend" :
                ctx.channel().writeAndFlush(new GsendRequestMessage(splits[1],splits[2]));
                break;
        }
        return true;
    }


}
