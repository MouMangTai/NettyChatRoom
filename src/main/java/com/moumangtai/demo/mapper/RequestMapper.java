package com.moumangtai.demo.mapper;

import com.moumangtai.demo.entity.Request;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
public interface RequestMapper extends BaseMapper<Request> {
    /**
     * 分页获取所有的好友请求
     * @param parameter
     * @return
     */
    List<Request> allFriendRequest(Map<String, Object> parameter);
}
