package com.startupweb.controllers;

import com.startupweb.repository.InversorRepository;
import com.startupweb.entities.FiltroBusqueda;
import com.startupweb.entities.Inversor;
import com.startupweb.entities.Proyecto;
import com.startupweb.entities.InversorProyecto;
import com.startupweb.repository.ProyectoRepository;
import com.startupweb.repository.InversorProyectoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Date;

@Controller
public class InversorController {

    @Autowired
    private InversorRepository inversorRepository;

    @Autowired
    ProyectoRepository proyectoRepository;

    @Autowired
    InversorProyectoRepository inversorProyectoRepository;

    @RequestMapping(value="/busquedaEmpresas", method=RequestMethod.GET)
    public String busquedaEmpresas(Model model) {
    	model.addAttribute("filtro", new FiltroBusqueda());
        return "Busqueda/busquedaEmpresas";
    }
    
    @RequestMapping(value="/inversor/{id}/addproyecto", method=RequestMethod.GET)
    public String viewInversor(@PathVariable Long id, Model model) {
        model.addAttribute("inversor", inversorRepository.findOne(id));
        model.addAttribute("proyectos", proyectoRepository.findAll());
        return "inversor";
    }

    @RequestMapping(value="/inversor/{id}/addproyecto", method=RequestMethod.POST)
    public String inversorAddProyecto(@PathVariable Long id, @RequestParam Long proyecto_id, Model model) {
        Proyecto proyecto = proyectoRepository.findOne(proyecto_id);
        Inversor inversor = inversorRepository.findOne(id);

        if (inversor != null && proyecto != null 
            && !inversor.poseeProyecto(proyecto) 
            && proyecto.getImporte() > 0
            && inversor.getImporte() > 0) {

          InversorProyecto inversorProyecto = new InversorProyecto();
          inversorProyecto.setInversor(inversor);
          inversorProyecto.setProyecto(proyecto);

          if (proyecto.getImporte() > inversor.getImporte()) {
            inversorProyecto.setImporte(inversor.getImporte());;
            proyecto.setImporte(proyecto.getImporte() - inversor.getImporte());
            inversor.setImporte(0L);
          } else {
            inversorProyecto.setImporte(proyecto.getImporte());
            inversor.setImporte(inversor.getImporte() - proyecto.getImporte());
            proyecto.setImporte(0L);
          }
          inversorProyectoRepository.save(inversorProyecto);

          inversorRepository.save(inversor);
          proyectoRepository.save(proyecto);    

          model.addAttribute("inversor", inversorRepository.findOne(id));
          model.addAttribute("proyectos", proyectoRepository.findAll());
          return "redirect:/inversor/" + inversor.getId() + "/addproyecto";
        }

        model.addAttribute("inversores", inversorRepository.findAll());
        return "redirect:/inversores";
    }

    @RequestMapping(value="inversor/{id}/edit", method=RequestMethod.GET)
    public String getEditInversor(@PathVariable Long id, Model model){
        model.addAttribute("inversor", inversorRepository.findOne(id));
        return "inversorform";
    }

    @RequestMapping(value="inversor/{id}/edit", method=RequestMethod.POST)
    public String postEditInversor(@PathVariable Long id, Inversor i){
        Inversor inversor = inversorRepository.findOne(id);
        inversor.setNombre(i.getNombre());
        inversor.setApellido(i.getApellido());
        inversor.setImporte(i.getImporte());
        inversorRepository.save(inversor);

        return "redirect:/inversores";
    }

    @RequestMapping("inversor/{id}/delete")
    public String delInversor(@PathVariable Long id){   
        inversorRepository.delete(id);
        return "redirect:/inversores";
    }

    @RequestMapping(value="/inversores", method=RequestMethod.GET)
    public String inversoresList(Model model) {
        model.addAttribute("inversor", new Inversor());
        model.addAttribute("inversores", inversorRepository.findAll());
        return "inversores";
    }

    @RequestMapping(value="/inversores", method=RequestMethod.POST)
    public String addIinversor(Inversor inversor, Model model) {
        inversorRepository.save(inversor);

        model.addAttribute("inversores", inversorRepository.findAll());
        model.addAttribute("proyectos", proyectoRepository.findAll());
        return "inversores";
    }
}
