package com.example.masraf_takip.api.controller;

import com.example.masraf_takip.business.service.UserService;
import com.example.masraf_takip.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
        	User newUser = userService.saveUser(user);
        	return ResponseEntity.ok(newUser);
        }catch(Exception e) {
        	e.printStackTrace();
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    	
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
    	User user = userService.findByUsername(username);
    	return ResponseEntity.ok(user);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<User>> getAllUsers() {
    	List<User> users = userService.getAllUsers();
    	return ResponseEntity.ok(users);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
    	return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
