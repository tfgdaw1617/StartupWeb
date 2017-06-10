package com.startupweb.controllers;

import com.startupweb.entities.Proyecto;
import com.startupweb.entities.User;
import com.startupweb.repository.ProyectoRepository;
import com.startupweb.repository.UserRepository;

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

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ProyectoController {

    @Autowired
    ProyectoRepository proyectoRepository;
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value="proyecto/{id}/edit", method=RequestMethod.GET)
    public String getEditProyecto(@PathVariable Long id, Model model){
            model.addAttribute("proyecto", proyectoRepository.findOne(id));
            return "proyectoform";
    }

    @RequestMapping(value="/proyecto/{id}", method=RequestMethod.GET)
    public String showProyecto(@PathVariable Long id, Model model) {
        Proyecto proyecto = proyectoRepository.findOne(id);
        proyectoRepository.save(proyecto);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userRepository.findByEmail(email);
        model.addAttribute("proyecto", proyecto);
        model.addAttribute("user", user);
        return "Proyectos/ProyectoIndex";
    }
    
    @RequestMapping(value="proyecto/{id}/edit", method=RequestMethod.POST)
    public String postEditProyecto(@PathVariable Long id, Proyecto p, Model model) {
        Proyecto proyecto = proyectoRepository.findOne(id);
        proyecto.setTitulo(p.getTitulo());
        proyecto.setDescripcion(p.getDescripcion());
        proyecto.setImporte(p.getImporte());
        proyectoRepository.save(proyecto);

        model.addAttribute("proyectos", proyectoRepository.findAll());
        return "redirect:/proyectos/";
    }

    @RequestMapping(value="/crearProyecto", method=RequestMethod.GET)
	public String listProyectos(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
 		String email = auth.getName();
 		User user = userRepository.findByEmail(email);
        model.addAttribute("proyecto", new Proyecto());
        model.addAttribute("user", user);
        return "Proyectos/crearProyecto";
	}

    @RequestMapping(value="/crearProyecto", method=RequestMethod.POST)
	public String addProyecto(Proyecto p, Model model) {
    	p.setImporte(0L);
    	p.setPorcentajeCompletado(0D);
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
 		String email = auth.getName();
 		User user = userRepository.findByEmail(email);
 		if(user.getRol().getDescripcion().equals("EMPRESA")) p.setEmpresa(user.getEmpresa());	
        proyectoRepository.save(p);
        return "redirect:/access";
	}

}