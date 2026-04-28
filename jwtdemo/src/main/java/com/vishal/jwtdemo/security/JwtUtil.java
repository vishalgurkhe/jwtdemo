package com.vishal.jwtdemo.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.vishal.jwtdemo.models.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	//afterwords take this value form app.properties file, now setting here
	private final String secretKey="my-super-secret-key-which-is-at-least-32-characters";
	
//	converts your plain string secret (secretKey) into a secure SecretKey object that can be used for HMAC-based JWT signing (like HS256, HS512, etc.)
	private SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	}
	
	//generating token
	public String generateAccessToken(User user) {
		
		String token=Jwts.builder()
					.setSubject(user.getUsername())
					.claim("userId", user.getId().toString())
					.setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis()+1000*60*10))
					.signWith(getSecretKey())
					.compact();
		
		return token;
	}
	

}
