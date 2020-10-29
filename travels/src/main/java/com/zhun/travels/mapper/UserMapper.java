package com.zhun.travels.mapper;

import com.zhun.travels.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhun
 * @time 2020/10/16 - 16:58
 * @function
 */
@Mapper
public interface UserMapper {
    //根据用户名查询用户信息
    User findByUsername(String username);
    //注册用户
    void save(User user);
}
