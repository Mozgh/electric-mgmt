package com.gaoh.electric.mapper;


import com.gaoh.electric.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    int insert(User user);

    void updateById(User user);

    void deleteById(@Param("id") int id);

    List<User> queryUser(@Param("query") String query);

    User selectById(@Param("id") int id);

}
