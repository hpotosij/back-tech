package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.User;
import com.example.backend.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public User createUser(@RequestBody User user){

        return userService.createUser(user);
    }

    @GetMapping("id/{id}")
    public User getUserById(@PathVariable String id){
        return userService.getUserById(id).orElse(null);
        }

    @GetMapping("user/{userName}") 
    public User getUserByUsername(@PathVariable String userName){
        return userService.findByUsername(userName).orElse(null);
    } 

    @DeleteMapping("/{userName}")
    public void deleteUser(@PathVariable String userName){
        userService.deleteUser(userName);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        System.out.println("entro login controller  " + user.getUserName() + " " + user.getPassword());
        boolean check = userService.checkPassword(user.getUserName(), user.getPassword());
        if(check)
            return ResponseEntity.ok("Login Successful");
        else
            return ResponseEntity.status(401).body("Invalid credentials");

    }
    

}