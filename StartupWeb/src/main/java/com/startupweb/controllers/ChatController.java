package com.startupweb.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import net.minidev.json.parser.JSONParser;

//import org.json.*;
import com.startupweb.entities.Conversacion;
import com.startupweb.entities.InversorProyecto;
import com.startupweb.entities.Mensaje;
import com.startupweb.entities.User;
import com.startupweb.repository.ConversacionRepository;
import com.startupweb.repository.MensajeRepository;
import com.startupweb.repository.UserRepository;
import com.startupweb.entities.Proyecto;
import com.startupweb.service.ConversacionService;
import com.startupweb.service.UserService;

@Controller
public class ChatController {
	@Autowired
	private UserService userService;
	@Autowired
	private ConversacionService conversacionService;
	
	@Autowired
	private ConversacionRepository conversacionRespository;
	
	@Autowired
	private MensajeRepository mensajeRespository;
	
	@Autowired
	UserRepository userRepository;
	@RequestMapping("/ContactosChat")
	String contactosChat(Model model) {
		User user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		List<User> contactos = new ArrayList<User>();
		for(Conversacion c : user.getConversaciones()){
			for(User u : c.getUsers())
				if(u.getId()!=user.getId()) contactos.add(u);
		}
		model.addAttribute("contactos", contactos);
		model.addAttribute("user", user);
		return "Chat/Contacts";
	}
	
	@RequestMapping("/Mensajes/{id}")
	String chat(Model model, @PathVariable long id) {
		Conversacion conversacion = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userTo = userService.findById(id);
		User userFrom = userService.findUserByEmail(auth.getName());

		boolean encontrada = false;
		int i = 0;
		ArrayList<Conversacion> conversaciones = new ArrayList<>(userFrom.getConversaciones());
		while (!encontrada && i < conversaciones.size()) {
			if (conversaciones.get(i).getUsers().contains(userTo)) {
				conversacion = conversaciones.get(i);
				encontrada = true;
			} else {
				i++;
			}
		}

		if (!encontrada) {
			Set<User> usuarios = new HashSet<User>();
			usuarios.add(userTo);
			usuarios.add(userFrom);
			conversacion = new Conversacion();
			conversacion.setUsers(usuarios);
			conversacion.setMensajes(new HashSet<Mensaje>());
			
			userFrom.addConversacion(conversacion);
			userTo.addConversacion(conversacion);
			conversacionService.saveConversacion(conversacion);
		
		}
		List<Mensaje> mNoLeido = mensajeRespository.findMensajesNoLeidos(userFrom.getId());
		for(Mensaje m : mNoLeido){
			m.setEstado(1);
			mensajeRespository.save(m);
		}
		List<Mensaje> mensajes = mensajeRespository.findMensajesById(conversacion.getId());
		model.addAttribute("mensajes", mensajes);
		model.addAttribute("user", userFrom);
		model.addAttribute("userTo", userTo);
		return "Chat/Chat";
	}
	
	@MessageMapping("/chat")
	@SendTo("/topic/mensajes")
	String sendMessage(String mensajeUnido) {
		String[] mensaje = mensajeUnido.split("::");
		User user = userRepository.findByEmail(mensaje[2]);
		User userTo = userRepository.findOne(new Long(mensaje[1]));
		Conversacion conversacion = new Conversacion();
		for(Conversacion c : user.getConversaciones()){
			for(User u: c.getUsers()){
				if(u.getId() == userTo.getId()){
					conversacion = conversacionRespository.findOne(c.getId());
				}
			}
		}
		Mensaje mensajeObj = new Mensaje(conversacion,userTo, user, mensaje[3], new Date(),0);
		if(conversacion.getMensajes()==null){
			Set<Mensaje> mensajes = new HashSet<>();
			mensajes.add(mensajeObj);
			conversacion.setMensajes(mensajes);
		}else{
			conversacion.addMensaje(mensajeObj);
		}
		conversacionService.saveConversacion(conversacion);
		return mensajeObj.getUserFrom().getNombre()+"::"+mensajeObj.getMensaje()+"::"+mensajeObj.getUserFrom().getId();

	}
}
