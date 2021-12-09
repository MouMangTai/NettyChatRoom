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
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        Server server = context.getBean(Server.class);
        InetSocketAddress address = new InetSocketAddress("127.0.0.1",8114);
        server.bind(address);
    }

}
