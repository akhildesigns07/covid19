package com.akhil.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akhil.project.model.User;
import com.akhil.project.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@GetMapping("/all")
    public List<User> all() {
       List<User> allUsers =  this.userRepository.findAll();            				
        return allUsers;
    }
	@PostMapping("/save")
	public User save(@RequestBody User user) {
		User Id = userRepository.save(user);
		return Id;
	}
	
}
