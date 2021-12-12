package com.moumangtai.demo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moumangtai.demo.constant.Result;
import com.moumangtai.demo.dto.UserLoginDto;
import com.moumangtai.demo.entity.User;
import com.moumangtai.demo.service.IUserService;
import com.moumangtai.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @Resource
    private IUserService iUserService;

    /**
     * 登陆和注册一体(返回码 0 登陆成功 1注册后登陆成功 -1 登陆失败 )
     *
     * @param userLoginDto
     * @return
     */
    @PostMapping("/loginAndRegister")
    public Result loginAndRegister(@RequestBody UserLoginDto userLoginDto) {
        Map<String, Object> res = iUserService.loginAndRegister(userLoginDto.getUserName(), userLoginDto.getPassWord());
        return res == null ? Result.error() : Result.success(res);
    }

    /**
     * 判断token是否过期
     *
     * @param token
     * @return
     */
    @GetMapping("/judgeTokenExpire")
    public Result judgeTokenExpire(@RequestParam String token) {
        return JwtUtil.judgeIsExpire(token) ? Result.success(true) : Result.success(false);
    }

    /**
     * 根据token获取user
     *
     * @param token
     * @return
     */
    @GetMapping("/getUserByToken")
    public Result getUserByToken(@RequestParam String token) {
        User user = iUserService.getUserByToken(token);
        return user == null ? Result.error() : Result.success(user);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        User u = iUserService.insertUser(user);
        return u != null ? Result.success(u) : Result.error();
    }

    @GetMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        Boolean flag = iUserService.deleteById(id);
        return flag ? Result.success() : Result.error();
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        User u = iUserService.updateUser(user);
        return u != null ? Result.success(u) : Result.error();
    }

    /**
     * 分页获取用户数据
     *
     * @param pageNum
     * @param pageSize
     * @param search
     * @return
     */
    @GetMapping("/findPage")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam String search) {
        Page<?> page = iUserService.findPage(pageNum, pageSize, search);
        return page != null ? Result.success(page) : Result.error();
    }

    /**
     * 获取当前在线用户
     *
     * @return
     */
    @GetMapping("/onlineUsers")
    public Result onlineUsers() {
        return Result.success(iUserService.onlineUsers());
    }
}
