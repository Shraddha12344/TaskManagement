package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dao.UserRepository;
import com.model.LoginRequest;
import com.model.User;
import com.security.JwtUtil;

@Service
public class UserServiceImple implements UserService{

	

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final TaskService taskService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImple(UserRepository userRepo, PasswordEncoder passwordEncoder, 
                            TaskService taskService, JwtUtil jwtUtil, 
                            AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.taskService = taskService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    
    @Override
    public void register(User user) {
        // Check if the user already exists
        if (userRepo.existsByUsername(user.getUsername())) {
            throw new RuntimeException("User already exists");
        }
        // Encrypt the password and save the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    @Override
    public String login(LoginRequest loginRequest) {
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            // Generate JWT token
            User user = (User) authentication.getPrincipal();
            return jwtUtil.generateToken(user.getUsername());
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid username or password");
        }
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        		
        return (UserDetails) user; // Assuming `User` implements `UserDetails`
    }

	@Override
	public String login(com.service.LoginRequest loginRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(Long taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
