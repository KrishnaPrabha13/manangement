package com.ecom.management.controller;


import com.ecom.management.dto.UserResponse;
import com.ecom.management.entity.Gender;
import com.ecom.management.entity.User;
import com.ecom.management.service.UserServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody User user){
        logger.info("Creating user");
        UserResponse userResponse = userServiceImp.createUser(user);
        logger.info("User created " + userResponse.getUserid());

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }


    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserResponse>> getAllUser(){
        logger.info("get all user");
        List<UserResponse> userList = userServiceImp.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }


    @GetMapping("/getUserById/{userid}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userid){
        logger.info("Get User by id");
        UserResponse userResponse = userServiceImp.getUserById(userid);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }


    @GetMapping("/getUserByGender/{gender}")
    public ResponseEntity<List<UserResponse>> getUserByGender(@PathVariable Gender gender) {
        logger.info("Get User By gender");
        List<UserResponse> userResponse = userServiceImp.getUserByGender(gender);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }


    @PutMapping("/updateUserById/{userid}")
    public ResponseEntity<UserResponse> updateUserById(@PathVariable Long userid, @RequestBody User updatedUserById){
        logger.info("Update the user ");
        UserResponse userResponse = userServiceImp.updateUserbyId(userid, updatedUserById);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }
}
