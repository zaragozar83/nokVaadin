package com.coffeenok.nok.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.coffeenok.nok.domain.User;

public interface UserService {
	
	public UserDetails loadUserByUsername(String username);
	public List<User> findAllUsers();
}
