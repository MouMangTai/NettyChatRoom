package com.moumangtai.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moumangtai.demo.entity.User;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wqd
 * @since 2021-11-27
 */
public interface IUserService extends IService<User> {

    /**
     * 登陆和注册一体(如果成功返回User和Token,如果失败则返回NULL)
     * @param userName
     * @param passWord
     * @return
     */
    Map<String,Object> loginAndRegister(String userName, String passWord);

    /**
     * 注册
     * @param userName
     * @param passWord
     * @return
     */
    User register(String userName,String passWord);

    /**
     * 根据token获取User
     * @param token
     * @return
     */
    User getUserByToken(String token);


    /**
     * 添加用户
     * @param user
     * @return
     */
    User insertUser(User user);

    /**
     * 分页获取用户数据
     * @param pageNum
     * @param pageSize
     * @param search
     */
    Page<?> findPage(Integer pageNum, Integer pageSize, String search);

    /**
     * 修改用户
     * @param user
     * @return
     */
    User updateUser(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    Boolean deleteById(Integer id);

    /**
     * 获取当前在线用户列表以及id列表
     * @return
     */
    Map<String,Object> onlineUsers();
}
