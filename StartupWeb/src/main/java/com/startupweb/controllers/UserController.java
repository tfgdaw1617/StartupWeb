package com.startupweb.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.startupweb.entities.FiltroBusqueda;
import com.startupweb.entities.InversorProyecto;
import com.startupweb.entities.Opinion;
import com.startupweb.entities.User;
import com.startupweb.repository.OpinionRepository;
import com.startupweb.repository.ProyectoRepository;
import com.startupweb.repository.UserRepository;
import com.startupweb.service.UserService;


@Controller
public class UserController {
	
	@Autowired
    ProyectoRepository proyectoRepository;
	
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    OpinionRepository opinionRepository;

	@Autowired
	private UserService userService;
	
	@RequestMapping("/Configuracion")
	String index(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userRepository.findByEmail(email);
		model.addAttribute("user", user);
		return "User/Configuracion";
	}
	
	@RequestMapping(value="/guardaCambios", method=RequestMethod.POST)
	String GuardaCambios(@Valid User userChange, BindingResult bindingResult,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userRepository.findByEmail(email);
		user.setDireccion(userChange.getDireccion());
		user.setEmail(userChange.getEmail());
		user.setTelefono(userChange.getTelefono());
		user.setNombre(userChange.getNombre());
		user.setDescripcion(userChange.getDescripcion());
		if(user.getRol().getDescripcion().equals("INVERSOR")){
			user.getInversor().setApellido(userChange.getInversor().getApellido());
			user.getInversor().setDni(userChange.getInversor().getDni());
		}else if(user.getRol().getDescripcion().equals("EMPRESA")){
			user.getEmpresa().setCif(userChange.getEmpresa().getCif());
		}
		userService.saveUser(user);
		model.addAttribute("user", user);
		return "redirect:/access";
	}
	
	@RequestMapping("/Contactos")
	String Contactos() {
		return "User/contacts";
	}
	
	@RequestMapping("/CambiarImagen")
	String CambiarImagen(@RequestParam("imagen") byte[] file, RedirectAttributes redirectAttributes) {
		try{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String email = auth.getName();
			User user = userRepository.findByEmail(email);
			user.setImagen(file);
			userRepository.save(user);
		}catch(Exception e){
			
		}
		return "redirect:/access";
	}
	
	@RequestMapping(value="/guardarComentario/{id}", method=RequestMethod.POST)
    public String guardarComentario(@RequestParam("mensaje") String mensaje, @PathVariable Long id, Model model) {
    	User userTo = userRepository.findOne(id);
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
 		String email = auth.getName();
 		User user = userRepository.findByEmail(email);	
 		Opinion opinion = new Opinion();
    	opinion.setUserFrom(user);
    	opinion.setUserTo(userTo);
    	opinion.setMensaje(mensaje);
    	opinion.setFecha(new Date());
    	opinionRepository.save(opinion);
    	if(user.getRol().getDescripcion().equals("INVERSOR"))
    		return "redirect:/inversor/"+userTo.getId();
    	else
    		return "redirect:/empresa/"+userTo.getId();
    }
}
