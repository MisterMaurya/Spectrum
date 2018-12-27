package com.spectrum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spectrum.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByEmail(String email);

	//if contact is unique
	//public User findByContact(String contact);

	public User findByUsername(String userName);

}
