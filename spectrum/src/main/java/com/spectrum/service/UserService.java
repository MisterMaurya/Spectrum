package com.spectrum.service;

import java.util.List;

import com.spectrum.entity.User;

public interface UserService {

	public List<User> listUsers();

	public User updateUser(User user);

	public User createUser(User user);

	public User findByEmail(String email);
	
		
	// if contact is unique
	// public User findByContact(String contact);

	public User findByUsername(String username);
	
	public User findById(Integer userId) ;
	
	public boolean existsById(Integer userId);
	
}
