package com.startupweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.startupweb.entities.User;
import com.startupweb.repository.UserRepository;


@Controller
public class ChatController {
	@Autowired
	private UserRepository userRepository;
	 
	@RequestMapping("/Mensajes")
	String chat(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userRepository.findByEmail(email);
		model.addAttribute("user", user);
		return "Chat/Chat";
	}
	@RequestMapping("/ContactosChat")
	String contactosChat(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
 		String email = auth.getName();
 		User user = userRepository.findByEmail(email);
 		model.addAttribute("user", user);
		return "Chat/Contacts";
	}
}
