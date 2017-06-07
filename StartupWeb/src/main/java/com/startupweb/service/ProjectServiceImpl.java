package com.startupweb.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.startupweb.entities.Proyecto;
import com.startupweb.entities.Rol;
import com.startupweb.entities.User;
import com.startupweb.repository.RolRepository;
import com.startupweb.repository.UserRepository;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	





}
