package com.practice.springboot.p07.dao;

import com.practice.springboot.p07.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM test_user WHERE NAME = #{name}")
    User findByName(@Param("name") String name);
    @Select("SELECT * FROM test_user WHERE id = #{id}")
    User findById(@Param("id") long id);
    @Insert("INSERT INTO test_user(NAME, AGE) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);
    @Insert("insert into test_user(name,age) values (#{name},#{age})")
    int insertByUser(User user);
    @Insert("insert into test_user(name,age) values (#{name,jdbcType=VARCHAR},#{age,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age")
    })
    @Select("SELECT id,name, age FROM test_user")
    List<User> findAll();

    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age")
    })
    @Select("SELECT id,name, age FROM test_user")
    List<User> queryAll2(RowBounds rowBounds);

    @Update("UPDATE test_user SET name=#{name} WHERE id=#{id}")
    void update(User user);
    @Delete("DELETE FROM test_user WHERE id =#{id}")
    void delete(Long id);

    User getByIdByXmlMapper(User user);
}