package com.practice.springboot.p05;

import com.practice.springboot.p05.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface User2Mapper {
    @Select("SELECT * FROM test_user WHERE id = #{id}")
    User findById(@Param("id") long id);

    User getByIdByXmlMapper(User user);
}