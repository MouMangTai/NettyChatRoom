package com.moumangtai.demo.service;

import io.netty.channel.Channel;

public interface ISessionService {
    /**
     * 绑定用户名和通道
     * @param userName
     * @param channel
     * @return
     */
    boolean bind(String userName, Channel channel);

    /**
     * 解绑用户名和通道
     * @param userName
     * @return
     */
    boolean unbind(String userName);

    /**
     * 根据用户名获取通道
     * @param userName
     * @return
     */
    Channel getChannel(String userName);
}
