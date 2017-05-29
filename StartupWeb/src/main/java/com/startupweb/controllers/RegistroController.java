package com.startupweb.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.startupweb.entities.User;
import com.startupweb.service.UserService;


@Controller
public class RegistroController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/registro", method=RequestMethod.GET)
	public String Registro(Model model){
		model.addAttribute("user", new User());
		return "/Registro/tipoUsuario";
	}
	
	@RequestMapping(value="/formEmpresa", method=RequestMethod.GET)
	public String FormEmpresa(Model model){
		model.addAttribute("user", new User());
		return "/Registro/registroEmpresa";
	}
	
	@RequestMapping(value="/formInversor", method=RequestMethod.GET)
	public String FormInversor(Model model){
		model.addAttribute("user", new User());
		return "/Registro/registroInversor";
	}
	
	@RequestMapping(value="/accesoEmpresa", method=RequestMethod.POST)
	public String registrarEmpresa(@Valid User user, BindingResult bindingResult,Model model){
		userService.saveEmpresa(user);
		model.addAttribute("user", new User());
		return "/login/login";
	}
	
	@RequestMapping(value="/accesoInversor", method=RequestMethod.POST)
	public String registrarInversor(@Valid User user, BindingResult bindingResult,Model model){
		userService.saveInversor(user);
		model.addAttribute("user", new User());
		return "/login/login";
	}
	
}