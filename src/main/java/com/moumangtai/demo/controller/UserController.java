package com.moumangtai.demo.controller;


import com.moumangtai.demo.constant.Result;
import com.moumangtai.demo.dto.UserLoginDto;
import com.moumangtai.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wqd
 * @since 2021-11-27
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;


    /**
     * 登陆和注册一体(返回码 0 登陆成功 1注册后登陆成功 -1 登陆失败 )
     * @param userLoginDto
     * @return
     */
    @PostMapping("/LoginAndRegister")
    public Result LoginAndRegister(@RequestBody UserLoginDto userLoginDto){
        Integer flag = iUserService.loginAndRegister(userLoginDto.getUserName(), userLoginDto.getPassWord());
        Map<String,Object> data = new HashMap<>();
        data.put("user",userLoginDto);
        switch (flag){
            case 0:
                return Result.success(data);
            case 1:
                return Result.success(data);
            case -1:
                return Result.error();
        }
        return Result.error();
    }
}
