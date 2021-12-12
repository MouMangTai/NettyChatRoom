package com.moumangtai.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moumangtai.demo.constant.BaseConstant;
import com.moumangtai.demo.entity.User;
import com.moumangtai.demo.factory.SessionFactory;
import com.moumangtai.demo.mapper.UserMapper;
import com.moumangtai.demo.service.IUserService;
import com.moumangtai.demo.util.JwtUtil;
import com.moumangtai.demo.util.RedisUtil;
import com.moumangtai.demo.util.md5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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

    @Resource
    private RedisUtil redisUtil;

    /**
     * 登陆和注册一体((如果成功返回User和Token,如果失败则返回NULL))
     *
     * @param userName
     * @param passWord
     * @return
     */
    @Override
    public Map<String, Object> loginAndRegister(String userName, String passWord) {
        try {
            Map<String, Object> res = new HashMap<>();

            User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_name", userName).eq("pass_word", md5Util.code(passWord)));
            System.out.println(1);
            if (user == null) {
                System.out.println(2);

                User register = register(userName, passWord);
                System.out.println(3);

                if (register != null) {
                    String token = JwtUtil.getToken(register);
                    res.put("user", register);
                    res.put("token", token);
                    res.put("flag", 0);
                    redisUtil.setex("userToken:" + register.getId(), token, BaseConstant.USER_TOKEN);
                    return res;
                }
            } else {
                String token = JwtUtil.getToken(user);
                res.put("user", user);
                res.put("token", token);
                res.put("flag", 1);
                redisUtil.setex("userToken:" + user.getId(), token, BaseConstant.USER_TOKEN);
                return res;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 注册(如果注册成功返回User,注册失败返回NULL）
     *
     * @param userName
     * @param passWord
     * @return
     */
    @Override
    public User register(String userName, String passWord) {
        User user = userMapper.selectOne(new QueryWrapper<User>().select("id").eq("user_name", userName));
        System.out.println(3);

        if (user == null) {
            System.out.println(4);

            user = new User();
            user.setUserName(userName);
            user.setPassWord(md5Util.code(passWord));
            int insert = userMapper.insert(user);
            if(insert>0) return user;
            return null;
        }
        return null;
    }

    /**
     * 根据token获取User
     *
     * @param token
     * @return
     */
    @Override
    public User getUserByToken(String token) {
        String userId = JwtUtil.getUserIdByToken(token);
        return userMapper.selectOne(new QueryWrapper<User>().eq("id", Long.parseLong(userId)));
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    public User insertUser(User user) {
        if (user.getId() == null) {
            user.setPassWord(md5Util.code(user.getPassWord()));
            int insert = userMapper.insert(user);
            if(insert>0){
                return user;
            }
        } else {
            int update = userMapper.updateById(user);
            if(update>0){
                return user;
            }
        }
        return null;
    }

    /**
     * 分页查询用户数据
     *
     * @param pageNum
     * @param pageSize
     * @param search
     */
    @Override
    public Page<?> findPage(Integer pageNum, Integer pageSize, String search) {
        Page<User> userPage = userMapper.selectPage(
                new Page<>(pageNum, pageSize),
                new QueryWrapper<User>().like("user_name", search)
        );
        return userPage;
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @Override
    public User updateUser(User user) {
        User oldUser = userMapper.selectOne(new QueryWrapper<User>().select("pass_word").eq("id", user.getId()));
        if (oldUser != null && !oldUser.getPassWord().equals(user.getPassWord())) {
            user.setPassWord(md5Util.code(user.getPassWord()));
        }
        int update = userMapper.updateById(user);
        if(update>0){
            return user;
        }
        return null;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    public Boolean deleteById(Integer id) {
        int delete = userMapper.deleteById(id);
        return delete>0;
    }

    /**
     * 获取当前在线用户
     * @return
     */
    @Override
    public Map<String,Object> onlineUsers() {
        Collection<Long> ids = SessionFactory.getSession().onlineUsers();
        Map<String,Object> map = new HashMap<>();
        if(ids!=null&&ids.size()!=0){
            List<User> users = userMapper.selectBatchIds(ids);
            map.put("users",users);
            map.put("ids",new ArrayList<>(ids));
            return map;
        }
        return null;
    }

}
