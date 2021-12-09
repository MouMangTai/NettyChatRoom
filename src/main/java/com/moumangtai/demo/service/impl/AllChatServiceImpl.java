package com.moumangtai.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moumangtai.demo.entity.AllChat;
import com.moumangtai.demo.entity.User;
import com.moumangtai.demo.mapper.AllChatMapper;
import com.moumangtai.demo.mapper.UserMapper;
import com.moumangtai.demo.service.IAllChatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 全频道聊天表 服务实现类
 * </p>
 *
 * @author wqd
 * @since 2021-12-07
 */
@Service
public class AllChatServiceImpl extends ServiceImpl<AllChatMapper, AllChat> implements IAllChatService {


    @Resource
    private AllChatMapper allChatMapper;

    @Resource
    private UserMapper userMapper;

    /**
     * 分页获取全频道聊天记录
     * @param pageSize
     * @return
     */
    @Override
    public List<Object> getAllChatByPage(Long userId,Integer pageSize) {
        List<Object> res = new ArrayList<>();
        Integer total = allChatMapper.selectCount(new QueryWrapper<>());
        if(total%pageSize==0) {
            List<AllChat> records = allChatMapper.selectPage(new Page<>(total / pageSize, pageSize), new QueryWrapper<>()).getRecords();
            records.stream().forEach(e->{
                Map<String,Object> map = new HashMap<>();
                User user = userMapper.selectById(e.getFromId());
                map.put("type",e.getFromId()==userId?1:0);
                map.put("user",user);
                map.put("content",e.getContent());
                res.add(map);
            });
            return res;

        }
        List<AllChat> records = allChatMapper.selectPage(new Page<>(total / pageSize +1, pageSize), new QueryWrapper<>()).getRecords();

        if(records.size()<pageSize){
            records.addAll(0,allChatMapper.selectPage(new Page<>(total/pageSize, pageSize), new QueryWrapper<>()).getRecords());
        }
        records.stream().forEach(e->{
            Map<String,Object> map = new HashMap<>();
            User user = userMapper.selectById(e.getFromId());
            map.put("type",e.getFromId()==userId?1:0);
            map.put("user",user);
            map.put("content",e.getContent());
            res.add(map);
        });
        return res;
    }
}
