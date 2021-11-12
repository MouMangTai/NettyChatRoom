package com.moumangtai.demo.controller;



import com.moumangtai.demo.service.IUserService;
import com.moumangtai.demo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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


    @Autowired
    private IUserService iUserService;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/test")
    public void test(){
        Boolean aBoolean = iUserService.checkLogin("111", "111");
        redisUtil.set("test",122);
        String test = redisUtil.get("test");

        System.out.println(aBoolean+test);
    }

}
