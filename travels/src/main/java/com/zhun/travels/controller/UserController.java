package com.zhun.travels.controller;

import com.zhun.travels.entity.Result;
import com.zhun.travels.entity.User;
import com.zhun.travels.service.UserService;
import com.zhun.travels.utils.CreateImageCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhun
 * @time 2020/10/16 - 15:30
 * @function
 */
@Controller
@RequestMapping("user")
@CrossOrigin  //允许跨域
@Slf4j
public class UserController {

    @Resource
    private UserService userServiceImpl;

    @ResponseBody
    @PostMapping("login")
    public Result login(@RequestBody User user,HttpServletRequest request){
        Result result=new Result();
        log.info("user: "+user);
        try{
            User loginUser = userServiceImpl.login(user);
            //登录成功后，保存用户的标记 ServletContext application  Redis ; userid :loginUser
            request.getServletContext().setAttribute(loginUser.getId(),loginUser);
            result.setMsg("登录成功").setUserId(loginUser.getId());
        }catch (Exception e){
            e.printStackTrace();
            result.setMsg(e.getMessage()).setState(false);
        }
        return result;
    }

    /**
     * 用户注册
     * @param code
     * @param key
     * @param user
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("register")
    public Result register(String code, String key, @RequestBody User user, HttpServletRequest request) {
        Result result = new Result();
        log.info("接收的User对象：" + user);
        log.info("接收的验证码：" + code);
        //验证验证码
        String keyCode = (String) request.getServletContext().getAttribute(key);
        log.info( "通过全局作用域存入的key，获取验证码： "+keyCode);
        try {
            if (code.equalsIgnoreCase(keyCode)) {
                //注册用户
                userServiceImpl.register(user);
                result.setMsg("注册成功！！");
            }else {
                throw new RuntimeException("验证码错误！！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg(e.getMessage()).setState(false);//可以获取到前面抛出的异常错误信息提示
        }
        return result;
    }

    /**
     * 生成验证码
     * @param request
     * @return
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("getImage")
    public Map<String,String> getImage( HttpServletRequest request) throws IOException {
        Map<String,String> result=new HashMap<>();
        CreateImageCode createImageCode = new CreateImageCode();
        //获取验证码
        String code = createImageCode.getCode();
        //验证码存入全局作用域
        String key = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        request.getServletContext().setAttribute(key, code);//存入全局配置
        //生成图片
        BufferedImage image = createImageCode.getBuffImg();
        //创建一个字节数组缓冲区，将图片的数据保存在该字节数组缓冲区中
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        ImageIO.write(image, "png", bos);
        //进行base64编码
        String string = Base64Utils.encodeToString(bos.toByteArray());
        result.put("key",key);//键值对，键是key ；值是key：时间戳
        result.put("image",string);
        return result;
    }
}
