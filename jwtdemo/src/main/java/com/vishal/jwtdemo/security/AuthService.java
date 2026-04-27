package com.vishal.jwtdemo.security;

import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.vishal.jwtdemo.dto.LoginRequestDto;
import com.vishal.jwtdemo.dto.LoginResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final AuthenticationManager authManager;
	
	public LoginResponseDto login(LoginRequestDto loginRequest) {
		// TODO Auto-generated method stub
//		
		//Creating authentication obj
		Authentication authentication=
				authManager.authenticate(
						new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword())
						);
				
		return null;
	}
	
	

}
