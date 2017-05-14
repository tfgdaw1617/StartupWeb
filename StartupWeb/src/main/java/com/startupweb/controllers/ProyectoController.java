package com.startupweb.controllers;

import com.startupweb.entities.Proyecto;
import com.startupweb.repository.ProyectoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProyectoController {

    @Autowired
    ProyectoRepository proyectoRepository;

    @RequestMapping(value="proyecto/{id}/edit", method=RequestMethod.GET)
    public String getEditProyecto(@PathVariable Long id, Model model){
            model.addAttribute("proyecto", proyectoRepository.findOne(id));
            return "proyectoform";
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

    @RequestMapping(value="/proyectos", method=RequestMethod.GET)
	public String listProyectos(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        model.addAttribute("proyectos", proyectoRepository.findAll());
        return "proyectos";
	}

    @RequestMapping(value="/proyectos", method=RequestMethod.POST)
	public String addProyecto(Proyecto p, Model model) {
        proyectoRepository.save(p);

        model.addAttribute("proyecto", new Proyecto());
        model.addAttribute("proyectos", proyectoRepository.findAll());
        return "proyectos";
	}

}