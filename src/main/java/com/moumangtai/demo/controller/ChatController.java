package com.moumangtai.demo.controller;


import com.moumangtai.demo.constant.Result;
import com.moumangtai.demo.service.IChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 聊天记录表 前端控制器
 * </p>
 *
 * @author wqd
 * @since 2021-11-30
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Resource
    private IChatService iChatService;

    /**
     * 分页获取好友聊天记录
     * @param userId
     * @param friendId
     * @param PageSize
     * @return
     */
    @GetMapping("/getChatByPage")
    public Result getAllChatByPage(@RequestParam Long userId,@RequestParam Long friendId,@RequestParam Integer PageSize){
        return Result.success(iChatService.getChatByPage(userId,friendId,PageSize));
    }

}
