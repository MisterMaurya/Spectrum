package com.spectrum.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spectrum.entity.User;
import com.spectrum.repository.UserRepository;
import com.spectrum.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> listUsers() {

		List<User> usersList = userRepository.findAll();
		// usersList.forEach(user->user.setPassword(null));
		return usersList;
	}

	@Override
	public User createUser(User user) {
		user.setModifiedOn(null);
		user.setPassword(
				user.getFirstName().substring(0, 3) + user.getLastName().substring(user.getLastName().length() - 3));
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findByEmail(String email) {
		User existingUser = null;
		existingUser = userRepository.findByEmail(email);
		return existingUser;
	}

	// if contact is unique

	/*
	 * @Override public User findByContact(String contact) { User existingUser =
	 * null; existingUser = userRepository.findByContact(contact); return
	 * existingUser; }
	 */

	@Override
	public User findByUsername(String username) {
		User existingUser = null;
		existingUser = userRepository.findByUsername(username);
		return existingUser;
	}

	@Override
	public User findById(Integer userId) {
		User existingUser = userRepository.findById(userId).get();
		return existingUser;
	}

	@Override
	public boolean existsById(Integer userId) {
		return userRepository.existsById(userId);
	}

}
