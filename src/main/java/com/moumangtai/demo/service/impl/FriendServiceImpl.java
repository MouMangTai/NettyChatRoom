package com.moumangtai.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moumangtai.demo.entity.Friend;
import com.moumangtai.demo.entity.User;
import com.moumangtai.demo.mapper.FriendMapper;
import com.moumangtai.demo.service.IFriendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wqd
 * @since 2021-12-09
 */
@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements IFriendService {

    @Resource
    private FriendMapper friendMapper;

    /**
     * 分页获取好友
     * @param currentPage
     * @param pageSize
     * @param search
     * @param userId
     * @return
     */
    @Override
    public List<User> allFriends(Integer currentPage, Integer pageSize, String search, Long userId) {
        Map<String,Object> map = new HashMap<>();
        map.put("currentPage",currentPage);
        map.put("pageSize",pageSize);
        map.put("search",search);
        map.put("userId",userId);
        return friendMapper.allFriends(map);
    }

    /**
     * 分页获取非好友
     * @param currentPage
     * @param pageSize
     * @param search
     * @param userId
     * @return
     */
    @Override
    public List<User> allNonFriends(Integer currentPage, Integer pageSize, String search, Long userId) {
        Map<String,Object> map = new HashMap<>();
        map.put("currentPage",currentPage);
        map.put("pageSize",pageSize);
        map.put("search",search);
        map.put("userId",userId);
        return friendMapper.allNonFriends(map);
    }
}
