package com.practice.springboot.p02;

import com.practice.springboot.p02.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface User2Mapper {
    @Select("SELECT * FROM test_user WHERE id = #{id}")
    User findById(@Param("id") long id);
    User getByIdByXmlMapper(User user);
}