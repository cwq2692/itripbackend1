package com.cwq.mapper;

import com.cwq.entity.itripUser;
import org.apache.ibatis.annotations.Select;

public interface itripUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(itripUser record);

    int insertSelective(itripUser record);

    itripUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(itripUser record);

    int updateByPrimaryKey(itripUser record);

    @Select("select * from itrip_user where usercode=#{_parameter}")
    itripUser getUserByUserCode(String email);
}