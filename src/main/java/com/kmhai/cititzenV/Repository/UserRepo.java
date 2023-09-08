package com.kmhai.cititzenV.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kmhai.cititzenV.entity.User;


public interface UserRepo extends JpaRepository<User, Integer> {
    
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}
