package com.moumangtai.demo.controller;


import com.moumangtai.demo.constant.Result;
import com.moumangtai.demo.service.IRequestService;
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
@RequestMapping("/request")
public class RequestController {

    @Resource
    private IRequestService iRequestService;

    /**
     * 分页获取所有的好友请求
     * @param currentPage
     * @param pageSize
     * @param search
     * @param userId
     * @return
     */
    @GetMapping("/allFriendRequest")
    public Result allFriendRequest(@RequestParam Integer currentPage,
                                   @RequestParam Integer pageSize,
                                   @RequestParam String search,
                                   @RequestParam Long userId){
        return Result.success(iRequestService.allFriendRequest(currentPage,pageSize,search,userId));
    }
}
