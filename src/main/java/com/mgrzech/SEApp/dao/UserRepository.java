package com.mgrzech.SEApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mgrzech.SEApp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
	
}
