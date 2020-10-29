package com.zhun.travels.test;

import com.zhun.travels.TravelsApplication;
import com.zhun.travels.entity.User;
import com.zhun.travels.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author zhun
 * @time 2020/10/16 - 17:23
 * @function
 */
@SpringBootTest(classes = TravelsApplication.class)
@RunWith(SpringRunner.class)
public class TestUserService {

    @Resource
    private UserService userServiceImpl;

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("zhun");
        user.setPassword("123");
        user.setEmail("755@qq.com");
        userServiceImpl.register(user);
    }

}
