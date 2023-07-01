package com.driver.controller;

import com.driver.models.User;
import com.driver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/create")
    public ResponseEntity<Void> createUser(@RequestParam String username, @RequestParam String password) throws Exception {
        // create a new user with given username and password
        try{
            userService.createUser(username,password);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) throws Exception {
        // delete user using deleteById
        try{
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.OK);

        }catch (Exception e){
            throw new Exception(e.getMessage());

        }

    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateUser(@RequestParam Integer id, @RequestParam String password) throws Exception {
        try{
            userService.updateUser(id,password);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            throw new Exception(e.getMessage());

        }
        // update password of given user

    }
}
