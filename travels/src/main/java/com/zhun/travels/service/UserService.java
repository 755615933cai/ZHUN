package com.zhun.travels.service;

import com.zhun.travels.entity.User;

/**
 * @author zhun
 * @time 2020/10/16 - 16:57
 * @function
 */
public interface UserService {
    //注册用户
    void register(User user);
    //登录用户
    User login(User user);
}
