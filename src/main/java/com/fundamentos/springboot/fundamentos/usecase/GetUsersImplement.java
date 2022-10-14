package com.fundamentos.springboot.fundamentos.usecase;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.service.UserService;

import java.util.List;

public class GetUsersImplement implements GetUsers{

    private final UserService userService;

    public GetUsersImplement(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll() {
        return userService.getAllUsers();
    }
}
