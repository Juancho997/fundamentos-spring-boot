package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.service.UserService;
import com.fundamentos.springboot.fundamentos.usecase.GetUsers;
import com.fundamentos.springboot.fundamentos.usecase.GetUsersImplement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {
    @Bean
    GetUsers getUser(UserService userService) {
        return new GetUsersImplement(userService);
    }
}
