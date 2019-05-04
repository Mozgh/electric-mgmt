package com.gaoh.electric.mapper;


import com.gaoh.electric.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    int insertUser(User user);

    void updateUserById(User user);

    void deleteUserById(@Param("id") int id);

    List<User> queryUser(@Param("query") String query);

    User selectUserById(@Param("id") int id);

    List<User> selectUserByUsername(@Param("username") String username);

}
