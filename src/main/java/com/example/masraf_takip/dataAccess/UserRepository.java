package com.example.masraf_takip.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.masraf_takip.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
