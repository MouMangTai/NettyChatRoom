package com.moumangtai.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moumangtai.demo.entity.User;
import com.moumangtai.demo.mapper.UserMapper;
import com.moumangtai.demo.service.IUserService;
import com.moumangtai.demo.util.md5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wqd
 * @since 2021-11-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 登陆验证
     * @param userName
     * @param passWord
     * @return
     */
    @Override
    public Boolean checkLogin(String userName, String passWord) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_name", userName).eq("pass_word", md5Util.code(passWord)));
        return user!=null;
    }

    /**
     * 注册
     * @param userName
     * @param passWord
     * @return
     */
    @Override
    public Boolean register(String userName, String passWord) {
        User user = userMapper.selectOne(new QueryWrapper<User>().select("id").eq("user_name", userName));
        if(user==null){
            user = new User();
            user.setUserName(userName);
            user.setPassWord(md5Util.code(passWord));
            user.setCreateTime(new Date());
            user.setModifiedTime(new Date());
            user.setDeleted(0);
            userMapper.insert(user);
            return true;
        }
        return false;

    }
}
