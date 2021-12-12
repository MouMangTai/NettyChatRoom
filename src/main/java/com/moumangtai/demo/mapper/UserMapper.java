package com.moumangtai.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moumangtai.demo.entity.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author wqd
 * @since 2021-11-27
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 分页获取所有用户并且判断是否是好友
     * @return
     */
    List<User> allUsers(Map<String,Object> map);
}
