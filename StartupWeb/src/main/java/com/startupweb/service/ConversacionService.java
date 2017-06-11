package com.startupweb.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.startupweb.entities.Conversacion;
import com.startupweb.entities.User;

@Service
public interface ConversacionService {
	public Conversacion findByUsers(Set<User> users);
	public void saveConversacion(Conversacion conversacion);
}
