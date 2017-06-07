package com.startupweb.controllers;

import com.startupweb.repository.InversorRepository;
import com.startupweb.entities.FiltroBusqueda;
import com.startupweb.entities.Inversor;
import com.startupweb.entities.Proyecto;
import com.startupweb.entities.Toque;
import com.startupweb.entities.User;
import com.startupweb.entities.InversorProyecto;
import com.startupweb.repository.ProyectoRepository;
import com.startupweb.repository.ToqueRepository;
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
public class InversorController {

    @Autowired
    private InversorRepository inversorRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    ProyectoRepository proyectoRepository;

    @Autowired
    InversorProyectoRepository inversorProyectoRepository;
    @Autowired
    ToqueRepository toqueRepository;

    @RequestMapping(value="/toque/{id}", method=RequestMethod.GET)
    public String publicInversor(@PathVariable Long id, Model model) {
    	User userInversor = userRepository.findOne(id);
    	Toque toque = new Toque();
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
 		String email = auth.getName();
 		User user = userRepository.findByEmail(email);	
    	toque.setEmpresa(user.getEmpresa());
    	toque.setInversor(userInversor.getInversor());
    	toqueRepository.save(toque);
    	return "redirect:/access";
    }
    
    @RequestMapping(value="/inversor/{id}", method=RequestMethod.GET)
    public String darToque(@PathVariable Long id, Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
 		String email = auth.getName();
 		User user = userRepository.findByEmail(email);		
 		User inversor = userRepository.findOne(id);
 		List<Proyecto> proyectos = new ArrayList<>();
 		for(InversorProyecto ip : inversor.getInversor().getInversorProyectos()){
 			proyectos.add(ip.getProyecto());
 		}
 		model.addAttribute("proyectos", proyectos);
    	model.addAttribute("inversor", inversor);
    	model.addAttribute("filtro", new FiltroBusqueda());
    	model.addAttribute("user", user);
        return "User/publicInversor";
    }
    
    @RequestMapping(value="/busquedaEmpresas", method=RequestMethod.GET)
    public String busquedaEmpresas(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
 		String email = auth.getName();
 		User user = userRepository.findByEmail(email);
    	List<Proyecto> proyectos = (List<Proyecto>) proyectoRepository.findAll();
    	model.addAttribute("user", user);
    	model.addAttribute("proyectos", proyectos);
    	model.addAttribute("filtro", new FiltroBusqueda());
        return "Busqueda/busquedaEmpresas";
    }
    
    @RequestMapping(value="/inversor/{id}/addproyecto", method=RequestMethod.GET)
    public String viewInversor(@PathVariable Long id, Model model) {
        model.addAttribute("inversor", inversorRepository.findOne(id));
        model.addAttribute("proyectos", proyectoRepository.findAll());
        return "inversor";
    }

    @RequestMapping(value="/addproyecto/{id}", method=RequestMethod.POST)
    public String inversorAddProyecto(@RequestParam("importe") Long importe, @PathVariable Long id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
 		String email = auth.getName();
 		User user = userRepository.findByEmail(email);
    	Proyecto proyecto = proyectoRepository.findOne(id);
        

          InversorProyecto inversorProyecto = new InversorProyecto();
          inversorProyecto.setInversor(user.getInversor());
          inversorProyecto.setProyecto(proyecto);

          if (proyecto.getImporte() > importe) {
            inversorProyecto.setImporte(importe);;
            proyecto.setImporte(proyecto.getImporte() - importe);
            user.getInversor().setImporte(user.getInversor().getImporte()-importe);
          } else {
            inversorProyecto.setImporte(proyecto.getImporte());
            user.getInversor().setImporte(user.getInversor().getImporte() - proyecto.getImporte());
            proyecto.setImporte(0L);
          }
          inversorProyectoRepository.save(inversorProyecto);
          userRepository.save(user);
          proyectoRepository.save(proyecto);    
        model.addAttribute("inversores", inversorRepository.findAll());
        return "redirect:/access";
    }

    @RequestMapping(value="/inversor", method=RequestMethod.GET)
    public String getEditInversor(Model model){
        model.addAttribute("user", userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "inversorform";
    }

    @RequestMapping(value="inversor/{id}/edit", method=RequestMethod.POST)
    public String postEditInversor(@PathVariable Long id, Inversor i){
        Inversor inversor = inversorRepository.findOne(id);
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
