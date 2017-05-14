package com.startupweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Login {
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public static String Login(Model model){
		return "/login/login";
	}
}
