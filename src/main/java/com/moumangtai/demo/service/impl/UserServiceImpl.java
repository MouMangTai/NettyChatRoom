package com.moumangtai.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moumangtai.demo.entity.User;
import com.moumangtai.demo.mapper.UserMapper;
import com.moumangtai.demo.service.IUserService;
import com.moumangtai.demo.util.md5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wqd
 * @since 2021-11-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 登陆和注册一体(返回码 0 登陆成功 1注册后登陆成功 -1 登陆失败 )
     * @param userName
     * @param passWord
     * @return
     */
    @Override
    public Integer loginAndRegister(String userName, String passWord) {
        try {
            User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_name", userName).eq("pass_word", md5Util.code(passWord)));
            if(user==null){
                Boolean register = register(userName, passWord);
                return register?1:-1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
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
            userMapper.insert(user);
            return true;
        }
        return false;

    }

    /**
     * 测试RPC的调用
     * @param value
     * @return
     */
    @Override
    public String RpcTest(Integer value) {
        return "测试数据为:"+value;
    }
}
