package com.ecom.management.repo;

import com.ecom.management.entity.Gender;
import com.ecom.management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    List<User> findUserByGender(Gender gender);
}
