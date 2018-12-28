package com.spectrum.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spectrum.entity.User;
import com.spectrum.repository.UserRepository;
import com.spectrum.service.UserService;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	@Override
	public List<User> listUsers() {

		List<User> usersList = userRepository.findAll();
		// usersList.forEach(user->user.setPassword(null));
		return usersList;
	}

	@Override
	public User createUser(User user) {

		user.setModifiedOn(null);
		user.setPassword(bcryptEncoder.encode(
				user.getFirstName().substring(0, 3) + user.getLastName().substring(user.getLastName().length() - 3)));
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRole().forEach(role -> {
			// authorities.add(new SimpleGrantedAuthority(role.getName()));
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
			System.out.println("ppppp"+role.getRoleName());
		});
		return authorities;
		// return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

}
