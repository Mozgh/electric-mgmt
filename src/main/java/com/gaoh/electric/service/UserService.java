package com.gaoh.electric.service;

import com.gaoh.electric.mapper.UserMapper;
import com.gaoh.electric.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public int createUser(User user) throws Exception {
        try {
            if (user.getUsername() != null && user.getName() != null) {
                userMapper.insertUser(user);
                return user.getId();
            } else {
                throw new Exception("user name can not be null");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    public void updateUser(Integer id, User user) throws Exception {
        try {
            if (id == null) {
                throw new Exception("id is null");
            }
            User userExist = userMapper.selectUserById(id);

            if (userExist != null) {
                user.setId(userExist.getId());
                userMapper.updateUserById(user);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    public void deleteUser(Integer id) throws Exception {
        try {
            if (id == null) {
                throw new Exception("id is null");
            }
            User user = userMapper.selectUserById(id);

            if (user != null) {
                userMapper.deleteUserById(id);
            } else {
                throw new Exception("user does not exist");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    public List<User> queryUser(String query) {
        try {
            if (query == null) {
                query = "";
            }
            return userMapper.queryUser("%" + query + "%");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    public boolean checkUser(String username, String password) throws Exception {
        List<User> users = userMapper.selectUserByUsername(username);
        if (users == null) {
            throw new Exception("user does not exist");
        }
        User user = users.get(0);
        return user.getPassword().equals(password);
    }
}
