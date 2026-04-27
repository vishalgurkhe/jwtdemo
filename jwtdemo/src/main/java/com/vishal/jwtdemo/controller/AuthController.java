package com.vishal.jwtdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vishal.jwtdemo.dto.LoginRequestDto;
import com.vishal.jwtdemo.dto.LoginResponseDto;
import com.vishal.jwtdemo.security.AuthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")

public class AuthController {
	
	private final AuthService authService ;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest){
		
		
		return ResponseEntity.ok(authService.login(loginRequest));
		
	}

}
