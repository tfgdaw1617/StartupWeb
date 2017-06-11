package com.startupweb.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startupweb.entities.Conversacion;
import com.startupweb.entities.User;
import com.startupweb.repository.ConversacionRepository;

@Service("conversacionService")
public class ConversacionServiceImpl implements ConversacionService{
	@Autowired
	private ConversacionRepository conversacionRepository;

	public Conversacion findByUsers(Set<User> users) {
		Conversacion conv = new Conversacion();
		try{
			conv =  conversacionRepository.findByUsers(users);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return conv;
	}

	@Override
	public void saveConversacion(Conversacion conversacion) {	
		conversacionRepository.save(conversacion);		
	}
	

}
