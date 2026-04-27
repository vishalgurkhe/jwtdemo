package com.vishal.jwtdemo.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
	
	private String jwtToken;
	
	private Long userId;

}
