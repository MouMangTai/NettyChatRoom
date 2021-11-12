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
public interface IUserService extends IService<User> {

    /**
     * 登陆验证
     * @param userName
     * @param passWord
     * @return
     */
    Boolean checkLogin(String userName,String passWord);
}
