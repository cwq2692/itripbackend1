package com.cwq.tokenservice;


import com.cwq.entity.itripUser;

/**
 * Created by cmy on 2019/5/23.
 */
public interface TokenService {
    int TOKEN_LIFE=600;//单位为秒

    //1:生产token的方法 agent:PC MOBILE
    String genToken(itripUser user, String agent);
    //2:移除token的方法
    void delToken(String token);
    //3:验证token是否正确的方法
    boolean validateToken(String token);
}
