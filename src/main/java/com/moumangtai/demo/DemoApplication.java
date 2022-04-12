package com.moumangtai.demo;

import com.moumangtai.demo.server.Server;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.InetSocketAddress;

@SpringBootApplication
@MapperScan("com.moumangtai.demo.mapper")
public class DemoApplication{


    public static void main(String[] args) throws InterruptedException {
        System.out.println("服务器启动....");
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        Server server = context.getBean(Server.class);
        System.out.println("服务器启动111");

        InetSocketAddress address = new InetSocketAddress("0.0.0.0",8114);
        System.out.println("服务器启动2");

        server.bind(address);

        System.out.println("服务器启动.222222");


    }

}
