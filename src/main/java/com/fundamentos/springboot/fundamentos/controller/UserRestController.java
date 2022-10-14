package com.fundamentos.springboot.fundamentos.controller;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.usecase.GetUsers;
import com.fundamentos.springboot.fundamentos.usecase.GetUsersImplement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final GetUsers getUsers;

    public UserRestController(GetUsers getUsers) {
        this.getUsers = getUsers;
    }

    @GetMapping("/")
    List<User> getAllUsers(){
        return getUsers.getAll();
    }
}
