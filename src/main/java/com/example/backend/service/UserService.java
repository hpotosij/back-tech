package com.example.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    //private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User createUser(User user){
        System.out.println("12123123123dxxee1x1eex1exe1r   : "); 
        System.out.println(user.toString());
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String userName){
        return userRepository.findByUserName(userName);
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public boolean checkPassword(String userName, String password){
        Optional<User> user = userRepository.findByUserName(userName);
        if(user.isPresent()){
            return user.get().getPassword().equals(password);
        }
        return false;
    }

   // public boolean checkPassword(String rawPassword, User user){
   //     return passwordEncoder.matches(rawPassword, user.getPassword());
   // }
}
