package com.ecom.management.service;

import com.ecom.management.dto.UserResponse;
import com.ecom.management.entity.Gender;
import com.ecom.management.entity.User;
import com.ecom.management.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepo userRepo;

    public static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserResponse createUser(User user){

        User u = new User();
        u.setName(user.getName());
        u.setPhoneno(user.getPhoneno());
        u.setEmailid(user.getEmailid());
        u.setGender(user.getGender());
        u.setAddress(user.getAddress());
        User saved = userRepo.save(u);
        return  new UserResponse(saved.getUserid(), saved.getName(), saved.getPhoneno(), saved.getEmailid(), saved.getGender(),saved.getAddress());

    }

    public List<UserResponse> getAllUser(){
        return userRepo.findAll()
                .stream()
                .map(u->new UserResponse(
                        u.getUserid(),
                        u.getName(),
                        u.getPhoneno(),
                        u.getEmailid(),
                        u.getGender(),
                        u.getAddress()))
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(Long userid){
        logger.info("Get user by id");

        User user =  userRepo.findById(userid).orElseThrow(()-> new RuntimeException("User not found by " +userid));
        return  new UserResponse(user.getUserid(), user.getName(),user.getPhoneno(), user.getEmailid(), user.getGender(),user.getAddress());
    }


    public List<UserResponse> getUserByGender(Gender gender) {
        logger.info("Get users by Gender");
        List<User> users = userRepo.findUserByGender(Gender.valueOf(gender.name()));
        return users.stream()
                .map(user -> new UserResponse(
                        user.getUserid(),
                        user.getName(),
                        user.getPhoneno(),
                        user.getEmailid(),
                        user.getGender(),
                        user.getAddress()
                ))
                .toList();
    }


    public UserResponse updateUserbyId(Long userid,User updatedUserData){
        logger.info("Update the user");

        // Fetch existing user from DB
        User existingUser = userRepo.findById(userid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update fields with new data
        existingUser.setName(updatedUserData.getName());
        existingUser.setPhoneno(updatedUserData.getPhoneno());
        existingUser.setEmailid(updatedUserData.getEmailid());
        existingUser.setGender(updatedUserData.getGender());

        // Save updated user
        User saved = userRepo.save(existingUser);

        // Return response
        return new UserResponse(
                saved.getUserid(),
                saved.getName(),
                saved.getPhoneno(),
                saved.getEmailid(),
                saved.getGender(),
                saved.getAddress()
        );

    }

}

