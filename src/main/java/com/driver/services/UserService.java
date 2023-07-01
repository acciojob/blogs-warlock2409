package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    public User createUser(String username, String password) throws Exception {
       User user= userRepository3.findByUserName(username);
       if(user != null){
           throw new Exception();
       }
       User newUser = User.builder()
               .firstName("")
               .lastName("")
               .userName(username)
               .password(password)
               .build();
        return userRepository3.save(newUser);
    }

    public void deleteUser(int userId) throws Exception {
        Optional<User> user= userRepository3.findById(userId);
        if(!user.isPresent()){
            throw new Exception();
        }
        userRepository3.deleteById(userId);
    }

    public User updateUser(Integer id, String password) throws Exception {
        Optional<User> user= userRepository3.findById(id);
        if(!user.isPresent()){
            throw new Exception("user not found");
        }
        User updateUser = user.get();
        updateUser.setPassword(password);

        User response =userRepository3.save(updateUser);
        System.out.println(response);
        return response;
    }
}
