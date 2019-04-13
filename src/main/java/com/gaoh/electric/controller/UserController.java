package com.gaoh.electric.controller;

import com.gaoh.electric.dto.ResponseDto;
import com.gaoh.electric.model.User;
import com.gaoh.electric.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/listUser")
    public ResponseDto<String> test() {

        return ResponseDto.<String>builder().code(ResponseDto.SUCCESS_CODE).build();
    }

    @PostMapping("/user")
    public ResponseDto<Integer> createUser(@RequestBody User user) {
        try {
            return ResponseDto.<Integer>builder().code(ResponseDto.SUCCESS_CODE).res(userService.createUser(user)).build();
        } catch (Exception e) {
            return ResponseDto.<Integer>builder().code(ResponseDto.FAILED_CODE).error(e.getMessage()).build();
        }
    }
}
