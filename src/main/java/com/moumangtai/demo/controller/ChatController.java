package com.moumangtai.demo.controller;


import com.moumangtai.demo.constant.Result;
import com.moumangtai.demo.service.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 聊天记录表 前端控制器
 * </p>
 *
 * @author wqd
 * @since 2021-11-30
 */
@RestController
@RequestMapping("/demo/chat")
public class ChatController {

    @Autowired
    private IChatService iChatService;


    @PostMapping("/text")
    public Result text(@RequestParam String token){
//        List<Chat> unSendChatByUserId = iChatService.getUnSendChatByUserId(35L);
//        System.out.println(unSendChatByUserId.toString());
//        User user = new User();
//        user.setId(25L);
//        user.setUserName("");
//        user.setPassWord("213123");
//        System.out.println(JwtUtil.verifyToken(JwtUtil.getToken(user)));
//        return null;

        return null;
    }

}
