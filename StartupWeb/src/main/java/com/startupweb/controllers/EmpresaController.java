package com.startupweb.controllers;

import com.startupweb.repository.InversorRepository;
import com.startupweb.entities.FiltroBusqueda;
import com.startupweb.entities.Inversor;
import com.startupweb.entities.Proyecto;
import com.startupweb.entities.User;
import com.startupweb.entities.InversorProyecto;
import com.startupweb.repository.ProyectoRepository;
import com.startupweb.repository.UserRepository;
import com.startupweb.repository.InversorProyectoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class EmpresaController {

    @Autowired
    private InversorRepository inversorRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    ProyectoRepository proyectoRepository;

    @Autowired
    InversorProyectoRepository inversorProyectoRepository;

    @RequestMapping(value="/busquedaInversores", method=RequestMethod.GET)
    public String busquedaInversores(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
 		String email = auth.getName();
 		User user = userRepository.findByEmail(email);
 		List<Inversor> inversores = (List<Inversor>) inversorRepository.findAll(); 		
    	model.addAttribute("inversores", inversores);
    	model.addAttribute("filtro", new FiltroBusqueda());
    	model.addAttribute("user", user);
        return "Busqueda/busquedaInversores";
    }
    
    @RequestMapping(value="/empresa/{id}", method=RequestMethod.GET)
    public String perfilEmpresa(@PathVariable Long id, Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
 		String email = auth.getName();
 		User user = userRepository.findByEmail(email);		
 		User empresa = userRepository.findOne(id);
 		List<Proyecto> proyectos = new ArrayList<>();
 		proyectos.addAll(empresa.getEmpresa().getProyectos());
 		model.addAttribute("proyectos", proyectos);
    	model.addAttribute("empresa", empresa);
    	model.addAttribute("filtro", new FiltroBusqueda());
    	model.addAttribute("user", user);
        return "User/publicEmpresa";
    }
    
   
}
