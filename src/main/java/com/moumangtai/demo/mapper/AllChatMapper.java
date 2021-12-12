package com.moumangtai.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moumangtai.demo.entity.AllChat;

import java.util.List;

/**
 * <p>
 * 全频道聊天表 Mapper 接口
 * </p>
 *
 * @author wqd
 * @since 2021-12-07
 */
public interface AllChatMapper extends BaseMapper<AllChat> {

    /**
     * 分页获取全频道聊天
     * @param pageSize
     * @return
     */
    List<AllChat> getAllChatByPage(Integer pageSize);
}
