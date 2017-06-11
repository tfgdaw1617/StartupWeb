package com.startupweb.service;

import com.startupweb.entities.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
	public void saveEmpresa(User user);
	public void saveInversor(User user);
	public 	User findById(long id);
}
