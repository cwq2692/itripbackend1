package com.cwq.serviceImpl;

import com.cwq.entity.itripUser;
import com.cwq.mapper.itripUserMapper;
import com.cwq.service.itripUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by cmy on 2019/5/21.
 */
@Transactional
@Service
public class itripUserServiceImpl implements itripUserService {

    @Autowired
    itripUserMapper mapper;

    @Override
    public int insert(itripUser record) {
        return mapper.insert(record);
    }

    @Override
    public itripUser getUserByUserCode(String email) {
        return mapper.getUserByUserCode(email);
    }
}
