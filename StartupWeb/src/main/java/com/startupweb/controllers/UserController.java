package com.startupweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UserController {
	@RequestMapping("/Configuracion")
	String index() {
		return "User/Configuracion";
	}
	
	@RequestMapping("/Contactos")
	String Contactos() {
		return "User/contacts";
	}
}
