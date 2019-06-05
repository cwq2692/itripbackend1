package com.cwq.service;

import com.cwq.entity.itripUser;

/**
 * Created by cmy on 2019/5/21.
 */
public interface itripUserService {
    int insert(itripUser record); //插入用户对象的方法
    itripUser getUserByUserCode(String email);
}
