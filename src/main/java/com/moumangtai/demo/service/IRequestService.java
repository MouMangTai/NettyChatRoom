package com.moumangtai.demo.service;

import com.moumangtai.demo.entity.Request;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wqd
 * @since 2021-12-09
 */
public interface IRequestService extends IService<Request> {

    /**
     * 分页获取所有的好友请求
     * @param currentPage
     * @param pageSize
     * @param search
     * @param userId
     * @return
     */
    List<Object> allFriendRequest(Integer currentPage, Integer pageSize, String search, Long userId);
}
