package com.moumangtai.demo.controller;



import com.moumangtai.demo.factory.SessionFactory;
import com.moumangtai.demo.message.SendRequestMessage;
import io.netty.channel.Channel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wqd
 * @since 2021-11-12
 */
@RestController
@RequestMapping("/demo/user")
public class UserController  {

    @GetMapping("/test")
    public void test(@RequestParam String userName,@RequestParam String content){
        Channel channel = SessionFactory.getSession().getChannel(userName);
        channel.writeAndFlush(new SendRequestMessage(userName,content));
    }

}
