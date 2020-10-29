package com.zhun.travels.service.impl;

import com.zhun.travels.entity.User;
import com.zhun.travels.mapper.UserMapper;
import com.zhun.travels.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zhun
 * @time 2020/10/16 - 16:59
 * @function
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        User userDB = userMapper.findByUsername(user.getUsername());
        if(userDB!=null){
            if(userDB.getPassword().equals(user.getPassword())){
                return userDB;
            }else{
                throw new RuntimeException("密码输入错误!");
            }
        }else{
            throw new RuntimeException("用户名输入错误!");
        }
    }

    @Override
    public void register(User user) {
        User userf = userMapper.findByUsername(user.getUsername());
        if(userf==null){
            userMapper.save(user);
        }else {
            throw new RuntimeException("用户名已存在");
        }

    }
}
