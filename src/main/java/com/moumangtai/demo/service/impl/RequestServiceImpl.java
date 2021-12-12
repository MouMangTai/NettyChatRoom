package com.moumangtai.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moumangtai.demo.entity.Request;
import com.moumangtai.demo.entity.User;
import com.moumangtai.demo.mapper.RequestMapper;
import com.moumangtai.demo.mapper.UserMapper;
import com.moumangtai.demo.service.IRequestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wqd
 * @since 2021-12-09
 */
@Service
public class RequestServiceImpl extends ServiceImpl<RequestMapper, Request> implements IRequestService {


    @Resource
    private RequestMapper requestMapper;

    @Resource
    private UserMapper userMapper;

    /**
     * 分页获取所有的好友请求
     * @param currentPage
     * @param pageSize
     * @param search
     * @param userId
     * @return
     */
    @Override
    public List<Object> allFriendRequest(Integer currentPage, Integer pageSize, String search, Long userId) {
        Map<String,Object> parameter = new HashMap<>();
        parameter.put("currentPage",currentPage);
        parameter.put("pageSize",pageSize);
        parameter.put("search",search);
        parameter.put("userId",userId);
        List<Request> requests =  requestMapper.allFriendRequest(parameter);
        List<Object> res = new ArrayList<>();
        requests.stream().forEach(e->{
            Map<String,Object> map = new HashMap<>();
            User user = userMapper.selectById(e.getFromId());
            map.put("messageId",e.getId());
            map.put("user",user);
            res.add(map);
        });
        return res;
    }


}
