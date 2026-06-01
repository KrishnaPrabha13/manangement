package com.ecom.management.service;

import com.ecom.management.dto.UserResponse;
import com.ecom.management.entity.Gender;
import com.ecom.management.entity.User;
import java.util.List;


public interface UserService {

    UserResponse createUser(User user);

    List<UserResponse> getAllUser();

    UserResponse getUserById(Long userid);

    List<UserResponse> getUserByGender(Gender gender);

    UserResponse updateUserbyId(Long userid, User updatedUserData);


}
