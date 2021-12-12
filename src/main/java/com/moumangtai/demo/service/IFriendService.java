package com.moumangtai.demo.service;

import com.moumangtai.demo.entity.Friend;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moumangtai.demo.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wqd
 * @since 2021-12-09
 */
public interface IFriendService extends IService<Friend> {


    /**
     * 分页获取好友
     * @param currentPage
     * @param pageSize
     * @param search
     * @param userId
     * @return
     */
    List<User> allFriends(Integer currentPage, Integer pageSize, String search, Long userId);

    /**
     * 分页获取非好友
     * @param currentPage
     * @param pageSize
     * @param search
     * @param userId
     * @return
     */
    List<User> allNonFriends(Integer currentPage, Integer pageSize, String search, Long userId);


}
