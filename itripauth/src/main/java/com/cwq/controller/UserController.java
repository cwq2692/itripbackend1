package com.cwq.controller;

import com.cwq.email.SendEmail;
import com.cwq.entity.itripUser;
import com.cwq.md5.MD5Utils;
import com.cwq.message.GetMessage;
import com.cwq.redis.RedisAPI;
import com.cwq.service.itripUserService;
import com.cwq.status.DataType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by cmy on 2019/5/21.
 */
@Controller
@Async
public class UserController {

    @Autowired
    SendEmail email;

    @Autowired
    RedisAPI redisAPI;
    @Autowired
    itripUserService service;
    @Autowired
    GetMessage message;

    //获取验证码的方法
    @RequestMapping("/getEmailCode")
    @ResponseBody
    public Object getEmailCode(String userCode){
        try{
            String s = email.sendEmail(userCode);
            redisAPI.set(userCode,60,s);
            return new DataType(0,"发送成功");
        }catch (Exception e){
            e.printStackTrace();
            return new DataType(1,"邮件发送失败");
        }
    }
    //获取短信验证码的方法
    @RequestMapping("/getMessage")
    @ResponseBody
    public Object getMessage(String userCode){
        try{
        String s = message.getResult(userCode);
        redisAPI.set(userCode,60,s);
        return new DataType(0,"发送成功");
    }catch (Exception e){
        e.printStackTrace();
        return new DataType(1,"短信发送失败");
    }
    }

    @RequestMapping("/register")
    @ResponseBody
    public Object register(itripUser user,String code){
        String vc=redisAPI.get(user.getUsercode());
        if(code.equals(vc)){ //验证码正确
            //注册操作
            itripUser userByUserCode = service.getUserByUserCode(user.getUsercode());
            if (userByUserCode==null){
                user.setUserpassword(MD5Utils.getMD5(user.getUserpassword()));
                service.insert(user);
                return new DataType(0,"注册成功");
            }else {
                return new DataType(1,"账号已注册，请重新登录");
            }
        }else {
            return new DataType(1,"验证码错误，请重新输入");
        }

    }

}
