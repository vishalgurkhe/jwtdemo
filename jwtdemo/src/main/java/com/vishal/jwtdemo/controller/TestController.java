package com.vishal.jwtdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@GetMapping("/getData")
	public String getTestData() {
		return "hii";
	}

}
