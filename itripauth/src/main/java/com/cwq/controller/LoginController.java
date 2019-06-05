package com.cwq.controller;

import com.alibaba.fastjson.JSONObject;
import com.cwq.entity.itripUser;
import com.cwq.md5.MD5Utils;
import com.cwq.redis.RedisAPI;
import com.cwq.service.itripUserService;
import com.cwq.status.DataType;
import com.cwq.token.UserToken;
import com.cwq.tokenservice.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:崔文青
 * @Description:
 * @Date:Created in 14:01 2019/5/23
 */
@Controller
public class LoginController {
    @Autowired
    itripUserService service;
    @Autowired
    TokenService tokenService;
    @Autowired
    RedisAPI redisAPI;
    @RequestMapping("/login")
    @ResponseBody
    public Object login(String username,String password,HttpServletRequest request){
        itripUser user = service.getUserByUserCode(username);
        if(user!=null){
            //验证登录密码
            password= MD5Utils.getMD5(password);
            if(user.getUserpassword().equals(password)){
                //生成token 并将token响应给客户端
                //token:userCodeMD5+当前系统时间+user-agentMD5
                String token = tokenService.genToken(user,request.getHeader("user-agent"));
                UserToken userToken = new UserToken(token,TokenService.TOKEN_LIFE*1000);
                //将token保存到redis中
                redisAPI.set(token,TokenService.TOKEN_LIFE, JSONObject.toJSONString(userToken));
                return new DataType(0,userToken );
            }else {
                return new DataType(1,"密码错误");
            }
        }else {
            return new DataType(1,"用户名不存在");
        }
    }
    //判断是否登录成功
    @RequestMapping("/success")
    @ResponseBody
    public Object success(HttpServletRequest request){
        String token = request.getHeader("token");
        if(tokenService.validateToken(token))
            return new DataType(0,"SUCCESS");
        else
            return new DataType(1,"Token is Faild");
    }
    //注销
    @RequestMapping("/logout")
    @ResponseBody
    public Object logout(HttpServletRequest request){
        String token = request.getHeader("token");
        tokenService.delToken(token);
        return new DataType(0,"注销成功");
    }
}
