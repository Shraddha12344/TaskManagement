package com.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.model.User;

@Service
public interface UserService {

	public void register(User user);
	public User findByUsername(String username);
	String login(LoginRequest loginRequest);
	String login(com.model.LoginRequest loginRequest);
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	public User findById(Long taskId);
}
