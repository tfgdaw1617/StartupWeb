package com.startupweb.repository;

import com.startupweb.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	 User findByEmail(String email);
}
