package com.moumangtai.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moumangtai.demo.entity.AllChat;

import java.util.List;

/**
 * <p>
 * 全频道聊天表 服务类
 * </p>
 *
 * @author wqd
 * @since 2021-12-07
 */
public interface IAllChatService extends IService<AllChat> {

    /**
     * 分页获取全频道聊天记录
     * @param pageSize
     * @return
     */
    List<Object> getAllChatByPage(Long userId,Integer pageSize);

}
