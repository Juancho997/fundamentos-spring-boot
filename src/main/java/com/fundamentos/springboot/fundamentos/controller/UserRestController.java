package com.fundamentos.springboot.fundamentos.controller;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.usecase.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final GetUsers getUsers;
    private final CreateUser createUser;
    private final DeleteUser deleteUser;
    private final UpdateUser updateUser;
    private final UserRepository userRepository; //hacer un UseCase -> no mezclar capas/responsabilidades

    public UserRestController(
            GetUsers getUsers,
            CreateUser createUser,
            UpdateUser updateUser,
            DeleteUser deleteUser,
            UserRepository userRepository) {
        this.getUsers = getUsers;
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.deleteUser = deleteUser;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    List<User> getAllUsers(){
        return getUsers.getAll();
    }

    @PostMapping("/")
    ResponseEntity<User> newUser(@RequestBody User newUser){
        return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Long id){
        deleteUser.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    ResponseEntity updateUser(@PathVariable Long id, @RequestBody User newUser){
        return new ResponseEntity(updateUser.update(id, newUser), HttpStatus.OK);
    }

    @GetMapping("/pageable")
    List<User> getUserPageable(@RequestParam int page, @RequestParam int size){
        return userRepository.findAll(PageRequest.of(page, size)).getContent();

    }


}
