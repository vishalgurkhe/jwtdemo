package com.vishal.jwtdemo.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishal.jwtdemo.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public Optional<User> findByUserName(String userName);

}
