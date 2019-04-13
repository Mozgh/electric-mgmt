package com.gaoh.electric.service;

import com.gaoh.electric.mapper.UserMapper;
import com.gaoh.electric.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public int createUser(User user) throws Exception {
        try {
            if (user.getUsername() != null && user.getName() != null) {
                userMapper.insert(user);
                return user.getId();
            } else {
                throw new Exception("user name can not be null");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
