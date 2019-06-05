package com.cwq.tokenservice;


import com.cwq.entity.itripUser;
import com.cwq.md5.MD5Utils;
import com.cwq.redis.RedisAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cmy on 2019/5/23.
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    RedisAPI redisAPI;
    SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
    @Override
    public String genToken(itripUser user, String agent) {
        //token:PC-usercode(MD5)-userid-date-agent
        StringBuilder sb=new StringBuilder("token:PC-");
        sb.append(MD5Utils.getMD5(user.getUsercode()));
        sb.append("-");
        sb.append(user.getId());
        sb.append("-");
        sb.append(sdf.format(new Date()));
        sb.append("-");
        sb.append(agent);
        return sb.toString();
    }

    @Override
    public void delToken(String token) {
        redisAPI.delete(token);
    }

    @Override
    public boolean validateToken(String token) {
        //借助Redis去验证
        return redisAPI.exist(token);
    }
}
