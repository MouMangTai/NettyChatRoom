package com.moumangtai.demo.handler;

import com.moumangtai.demo.constant.MessageConstant;
import com.moumangtai.demo.message.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("连接到服务器{}成功",ctx.channel().remoteAddress());

        new Thread(()->{

            Scanner sc = new Scanner(System.in);
            while(true){
                printHomePage();
                String select = sc.nextLine();
                if(select.equals("1")){
                    System.out.println("输入您的用户名");
                    String userName = sc.nextLine();
                    System.out.println("输入您的密码");
                    String passWord = sc.nextLine();
                    //发送到服务器校验
                    LoginRequestMessage loginRequestMessage = new LoginRequestMessage(userName, passWord);
                    ctx.channel().writeAndFlush(loginRequestMessage);

                    try {
                        MessageConstant.WAIT_FOR_LOGIN.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(MessageConstant.IS_LOGIN.get()){
                        while(true){
                            printCommandPage();
                            String s = sc.nextLine();
                            try{
                                sendCommand(ctx,s);
                            }catch (Exception e){
                                System.out.println("------------------------");
                                System.out.println("|     输入指令存在问题     |");
                                System.out.println("------------------------");
                            }
                        }
                    }else{
                        System.out.println("------------------------");
                        System.out.println("|     登陆失败重新登陆     |");
                        System.out.println("------------------------");
                        MessageConstant.WAIT_FOR_LOGIN = new CountDownLatch(1);
                    }

                }else if(select.equals("2")){
                    System.out.println("输入要注册的用户名（服务器唯一）");
                    String userName = sc.nextLine();
                    System.out.println("输入注册用户的密码");
                    String passWord = sc.nextLine();

                    //发送到服务器校验
                    RegisterRequestMessage registerRequestMessage = new RegisterRequestMessage(userName,passWord);
                    ctx.channel().writeAndFlush(registerRequestMessage);

                    try {
                        MessageConstant.WAIT_FOR_REGISTER.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(MessageConstant.IS_REGISTER.get()){
                        System.out.println("------------------------");
                        System.out.println("|     注册成功，请登录     |");
                        System.out.println("------------------------");
                    }else{
                        System.out.println("------------------------");
                        System.out.println("|  注册失败，用户名已经存在  |");
                        System.out.println("------------------------");
                        MessageConstant.WAIT_FOR_REGISTER = new CountDownLatch(1);
                    }

                }else if(select.equals("3")){
                    ctx.channel().close();
                    return ;
                }else{
                    printExceptionPage();
                }
            }

        }).start();

    }

    private boolean sendCommand(ChannelHandlerContext ctx,String s) {
        String[] splits = s.split(" ");
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
            case "q":


        }
        return true;
    }

    /**
     * 输出异常信息
     */
    public void printExceptionPage(){
        System.out.println("------------------------");
        System.out.println("|     输入指令异常        |");
        System.out.println("------------------------");
    }

    /**
     * 输出首页引导信息
     */
    public void printHomePage(){
        System.out.println("------------------");
        System.out.println("|输入              |");
        System.out.println("|1.登陆            |");
        System.out.println("|2.注册            |");
        System.out.println("|3.退出系统         |");
        System.out.println("------------------");
    }

    public void printCommandPage(){
        System.out.println("------------------------------------------");
        System.out.println("|         登陆成功(请根据提示输入指令）         |");
        System.out.println("------------------------------------------");
        System.out.println("| send    [userName]  [content] 发送单聊消息 |");
        System.out.println("| gsend   [groupName] [content] 发送群聊消息 |");
        System.out.println("| gcreate [groupName] [user1..] 创建群聊     |");
        System.out.println("| q                             退出系统     |");
        System.out.println("------------------------------------------");
    }
}
