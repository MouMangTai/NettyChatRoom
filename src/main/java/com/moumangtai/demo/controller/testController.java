package com.moumangtai.demo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class testController {

    @GetMapping("/test")
    public void test(){
        log.info("info");
        System.out.println("ceshi");
    }
}
