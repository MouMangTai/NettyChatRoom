package com.moumangtai.demo.controller;


import com.moumangtai.demo.constant.Result;
import com.moumangtai.demo.service.IFriendService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wqd
 * @since 2021-12-09
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Resource
    private IFriendService iFriendService;

    /**
     * 分页获取好友
     * @return
     */
    @GetMapping("/allFriends")
    public Result allFriends(@RequestParam Integer currentPage,
                             @RequestParam Integer pageSize,
                             @RequestParam String search,
                             @RequestParam Long userId){
        return Result.success(iFriendService.allFriends(currentPage,pageSize,search,userId));
    }

    /**
     * 分页获取非好友
     * @param currentPage
     * @param pageSize
     * @param search
     * @param userId
     * @return
     */
    @GetMapping("/allNonFriends")
    public Result allNonFriends(@RequestParam Integer currentPage,
                                @RequestParam Integer pageSize,
                                @RequestParam String search,
                                @RequestParam Long userId){
        return Result.success(iFriendService.allNonFriends(currentPage,pageSize,search,userId));
    }



}
