package com.gaoh.electric.controller;

import com.gaoh.electric.dto.ResponseDto;
import com.gaoh.electric.model.User;
import com.gaoh.electric.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth")
    public ResponseDto<Boolean> auth(@RequestBody User user) {
        try {
            return ResponseDto.<Boolean>builder().code(ResponseDto.SUCCESS_CODE).res(userService.checkUser(user.getUsername(), user.getPassword())).build();
        } catch (Exception e) {
            return ResponseDto.<Boolean>builder().code(ResponseDto.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @PostMapping("/user")
    public ResponseDto<Integer> createUser(@RequestBody User user) {
        try {
            return ResponseDto.<Integer>builder().code(ResponseDto.SUCCESS_CODE).res(userService.createUser(user)).build();
        } catch (Exception e) {
            return ResponseDto.<Integer>builder().code(ResponseDto.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @PutMapping("/user/{id}")
    public ResponseDto<Void> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        try {
            userService.updateUser(id, user);
            return ResponseDto.<Void>builder().code(ResponseDto.SUCCESS_CODE).build();
        } catch (Exception e) {
            return ResponseDto.<Void>builder().code(ResponseDto.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseDto<Void> deleteUser(@PathVariable("id") int id) {
        try {
            userService.deleteUser(id);
            return ResponseDto.<Void>builder().code(ResponseDto.SUCCESS_CODE).build();
        } catch (Exception e) {
            return ResponseDto.<Void>builder().code(ResponseDto.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @GetMapping("/user")
    public ResponseDto<List<User>> queryUser(@RequestParam(value = "query", required = false) String query) {
        try {
            return ResponseDto.<List<User>>builder().code(ResponseDto.SUCCESS_CODE).res(userService.queryUser(query)).build();
        } catch (Exception e) {
            return ResponseDto.<List<User>>builder().code(ResponseDto.FAILED_CODE).error(e.getMessage()).build();
        }
    }
}
