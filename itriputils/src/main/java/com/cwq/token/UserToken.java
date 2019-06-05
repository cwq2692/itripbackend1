package com.cwq.token;

/**
 * Created by cmy on 2019/5/23.
 */
public class UserToken {

    private String token;

    private long lifeTime;//有效时间

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(long lifeTime) {
        this.lifeTime = lifeTime;
    }

    public UserToken(String token, long lifeTime) {
        this.token = token;
        this.lifeTime = lifeTime;
    }

    public UserToken(){

    }
}
