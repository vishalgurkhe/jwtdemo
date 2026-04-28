package com.vishal.jwtdemo.security;

import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vishal.jwtdemo.dto.LoginRequestDto;
import com.vishal.jwtdemo.dto.LoginResponseDto;
import com.vishal.jwtdemo.dto.SignupResponseDto;
import com.vishal.jwtdemo.models.User;
import com.vishal.jwtdemo.repos.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final AuthenticationManager authManager;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtUtil jwtUtil;
	
	private final UserRepository userRepo;
	
	public LoginResponseDto login(LoginRequestDto loginRequest) {
		// TODO Auto-generated method stub
//		
		//Creating authentication obj
		Authentication authentication=
				authManager.authenticate(
						new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword())
						);
				
		User user=(User)authentication.getPrincipal();
		
		String accessToken = jwtUtil.generateAccessToken(user);
		
		return new LoginResponseDto(accessToken,user.getId());
		
	}
	
	public SignupResponseDto signup(LoginRequestDto loginRequest) {
		// TODO Auto-generated method stub

		//need to add logic for if user is already exists
		User user =new User();
		
		user.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
		user.setUserName(loginRequest.getUserName());
		
		User savedUser = this.userRepo.save(user);
	    System.out.println("saved User ---> "+savedUser.toString());
	    return new SignupResponseDto(savedUser.getId(),savedUser.getUsername());
		
	}

}
