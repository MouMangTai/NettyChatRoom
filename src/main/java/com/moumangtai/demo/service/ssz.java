package com.moumangtai.demo.service;

import com.moumangtai.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wqd
 * @since 2021-11-12
 */
public interface ssz extends IService<User> {

    /**
     * 登陆和注册一体(返回码 0 登陆成功 1注册后登陆成功 -1 登陆失败 )
     * @param userName
     * @param passWord
     * @return
     */
    Integer loginAndRegister(String userName,String passWord);

    /**
     * 注册
     * @param userName
     * @param passWord
     * @return
     */
    Boolean register(String userName,String passWord);


    /**
     * 测试RPC的调用
     * @param value
     * @return
     */
    String RpcTest(Integer value);
}
