package com.api.challenge.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.challenge.models.User;
import com.api.challenge.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User createUser(User user) {
		user.setActive(true);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public User update(User user) {
		return userRepository.save(user);
	}
	
	public User deleteUser(User user) {
		user.setActive(false);
		return userRepository.save(user);
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
}
