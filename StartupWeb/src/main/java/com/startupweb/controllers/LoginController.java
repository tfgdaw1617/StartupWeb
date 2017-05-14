package com.startupweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public static String Login(Model model){
		return "/login/login";
	}
	@RequestMapping(value="/access", method=RequestMethod.GET)
	public static String LoginAccess(Model model){
		return "/User/UserIndex";
	}
	@RequestMapping(value="/registro", method=RequestMethod.GET)
	public static String Registro(Model model){
		return "/login/registro";
	}
}