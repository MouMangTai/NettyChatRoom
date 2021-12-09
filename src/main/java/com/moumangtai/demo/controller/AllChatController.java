package com.moumangtai.demo.controller;


import com.moumangtai.demo.constant.Result;
import com.moumangtai.demo.service.IAllChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private IAllChatService iAllChatService;

    @GetMapping("/getAllChatByPage")
    public Result getAllChatByPage(@RequestParam Long userId,@RequestParam Integer PageSize){
        return Result.success(iAllChatService.getAllChatByPage(userId,PageSize));
    }
}
