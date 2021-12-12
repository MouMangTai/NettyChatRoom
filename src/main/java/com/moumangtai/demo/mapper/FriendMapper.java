package com.moumangtai.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moumangtai.demo.entity.Friend;
import com.moumangtai.demo.entity.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wqd
 * @since 2021-12-09
 */
public interface FriendMapper extends BaseMapper<Friend> {

    /**
     * 分页获取好友
     * @param map
     * @return
     */
    List<User> allFriends(Map<String,Object> map);

    /**
     * 分页获取非好友
     * @param map
     * @return
     */
    List<User> allNonFriends(Map<String, Object> map);
}
