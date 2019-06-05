package com.cwq.controller;

import com.cwq.entity.itripUser;
import com.cwq.service.itripUserService;
import com.cwq.status.DataType;
import com.cwq.wx.GetJson;
import com.cwq.wx.WxUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @Author:崔文青
 * @Description:
 * @Date:Created in 19:13 2019/5/22
 */
@Controller
public class WXController {
    @Autowired
    WxUtils wxUtils;
    @Autowired
    GetJson getJson;
    @Autowired
    itripUserService service;
    public WXController() {
    }

    @RequestMapping({"/wxlogin"})
    public void login(HttpServletResponse response) throws IOException {
        String url = "https://open.weixin.qq.com/connect/qrconnect?appid=" + this.wxUtils.appid + "&redirect_uri=" + this.wxUtils.redirect_uri + "&response_type=" + this.wxUtils.response_type + "&scope=" + this.wxUtils.scope + "&state=" + this.wxUtils.state;
        response.sendRedirect(url);
    }

    @RequestMapping({"/wxloginCallback"})
    @ResponseBody
    public Object callBack(String code, HttpServletResponse response) throws IOException {
        if (code == null && code.equals("")) {
            System.out.println("用户未通过认证");
            return null;
        } else {
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + this.wxUtils.appid + "&secret=" + this.wxUtils.secret + "&code=" + code + "&grant_type=authorization_code";
            JSONObject httpJson = this.getJson.getHttpJson(url, 1);
            String openid = httpJson.getString("openid");
            String access_token = httpJson.getString("access_token");
            String url2 = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
            JSONObject httpJson1 = this.getJson.getHttpJson(url2.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid), 1);
            //注册
            String userCode = httpJson1.getString("openid");
            if(service.getUserByUserCode(userCode)==null){
                itripUser user = new itripUser();
                user.setUsercode(userCode);
                String s = new String(httpJson1.getString("nickname").getBytes("GBK"), "UTF-8");
                user.setUsername(s);
                System.out.println(s);
                user.setCreationdate(new Date());
                service.insert(user);
            }
            return new DataType(0,"登录成功");
        }
    }
}
