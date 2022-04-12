package com.moumangtai.demo.controller;


import com.moumangtai.demo.constant.Result;
import com.moumangtai.demo.service.IAllChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 全频道聊天表 前端控制器
 * </p>
 *
 * @author wqd
 * @since 2021-12-07
 */
@RestController
@RequestMapping("/all-chat")
public class AllChatController {

    @Resource
    private IAllChatService iAllChatService;

    /**
     * 分页获取全频道聊天记录
     * @return
     */
    @GetMapping("/getAllChatByPage")
    public Result getAllChatByPage(@RequestParam Long userId,@RequestParam Integer PageSize){
        return Result.success(iAllChatService.getAllChatByPage(userId,PageSize));
    }

    /**
     * 测试接口
     * @return
     */
    @GetMapping("/test")
    public String test(){
        return "测试测试";
    }
}
