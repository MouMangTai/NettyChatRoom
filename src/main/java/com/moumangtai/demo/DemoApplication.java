package com.moumangtai.demo;

import com.moumangtai.demo.server.Server;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

@SpringBootApplication
@MapperScan("com.moumangtai.demo.mapper")
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private Server server;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        InetSocketAddress address = new InetSocketAddress("127.0.0.1",8282);
        server.bind(address);
    }
}
