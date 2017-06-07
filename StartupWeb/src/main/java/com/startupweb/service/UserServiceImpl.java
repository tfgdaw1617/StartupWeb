package com.startupweb.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.startupweb.entities.Rol;
import com.startupweb.entities.User;
import com.startupweb.repository.RolRepository;
import com.startupweb.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void saveEmpresa(User user) {
		Rol userRole = rolRepository.findByDescripcion("EMPRESA");
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRol(userRole);
        user.setActive(1);
		userRepository.save(user);
		
	}

	@Override
	public void saveInversor(User user) {
		Rol userRole = rolRepository.findByDescripcion("INVERSOR");
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRol(userRole);
        user.setActive(1);
		userRepository.save(user);
		
	}


}
