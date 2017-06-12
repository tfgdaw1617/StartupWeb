package com.startupweb.controllers;

import com.startupweb.repository.InversorRepository;
import com.startupweb.entities.FiltroBusqueda;
import com.startupweb.entities.Inversor;
import com.startupweb.entities.Proyecto;
import com.startupweb.entities.Toque;
import com.startupweb.entities.User;
import com.startupweb.entities.InversorProyecto;
import com.startupweb.entities.InversorProyectoId;
import com.startupweb.repository.ProyectoRepository;
import com.startupweb.repository.ToqueRepository;
import com.startupweb.repository.UserRepository;
import com.startupweb.repository.InversorProyectoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import java.util.List;

import javax.validation.Valid;

import java.math.BigDecimal;
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

    @RequestMapping(value="/toque/{id}", method=RequestMethod.POST)
    public String publicInversor(@RequestParam("mensaje") String mensaje, @PathVariable Long id, Model model) {
    	User userInversor = userRepository.findOne(id);
    	Toque toque = new Toque();
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
 		String email = auth.getName();
 		User user = userRepository.findByEmail(email);	
    	toque.setEmpresa(user.getEmpresa());
    	toque.setInversor(userInversor.getInversor());
    	toque.setFecha(new Date());
    	toque.setMensaje(mensaje);
    	toqueRepository.save(toque);
    	return "redirect:/access";
    }
    
    @RequestMapping(value="/recargarImporte", method=RequestMethod.POST)
    public String recargarImporte(@RequestParam("importe") Long importe, Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
 		String email = auth.getName();
 		User user = userRepository.findByEmail(email);	
 		model.addAttribute("user", user);
 		if(user.getRol().getDescripcion().equals("INVERSOR")){
 			user.getInversor().setImporte(user.getInversor().getImporte()+importe);
 		}
 		userRepository.save(user);
 		return "redirect:/access";
    }
    
    @RequestMapping(value="/recargarImporte", method=RequestMethod.GET)
    public String recargarImporte(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
 		String email = auth.getName();
 		User user = userRepository.findByEmail(email);	
 		model.addAttribute("user", user);
 		return "User/recargarImporte";
    }
    
    @RequestMapping(value="/inversor/{id}", method=RequestMethod.GET)
    public String listInversor(@PathVariable Long id, Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
 		String email = auth.getName();
 		User user = userRepository.findByEmail(email);		
 		User inversor = userRepository.findOne(id);
 		inversor.setVisitas(inversor.getVisitas()+1);
 		userRepository.save(inversor);
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
    public String busquedaEmpresasPost(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
 		String email = auth.getName();
 		User user = userRepository.findByEmail(email);
    	List<Proyecto> proyectos = (List<Proyecto>) proyectoRepository.findAll();
    	model.addAttribute("user", user);
    	model.addAttribute("proyectos", proyectos);
    	model.addAttribute("filtro", new FiltroBusqueda());
        return "Busqueda/busquedaEmpresas";
    }
    
    @RequestMapping(value="/busquedaEmpresas", method=RequestMethod.POST)
    public String busquedaEmpresas(@Valid FiltroBusqueda filtro, BindingResult bindingResult, Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
 		String email = auth.getName();
 		User user = userRepository.findByEmail(email);
    	List<Proyecto> proyectos = (List<Proyecto>) proyectoRepository.findByFiltro(filtro.getNombre(), filtro.getCantidad());
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
    
    @RequestMapping(value="/retirarInversion/{id}/{importe}", method=RequestMethod.GET)
    public String retirarInversion(@PathVariable Long id,@PathVariable Long importe, Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
 		String email = auth.getName();
 		User user = userRepository.findByEmail(email);
 		Proyecto p = proyectoRepository.findOne(id);
    	for(InversorProyecto ip : user.getInversor().getInversorProyectos()){
    		if(ip.getId().getProyecto().equals(p) && ip.getId().getInversor().equals(user.getInversor()) && ip.getImporte().equals(importe)){    		
    			user.getInversor().getInversorProyectos().remove(ip);
    			user.getInversor().setImporte(user.getInversor().getImporte()+ip.getImporte());
    			p.setImporte(p.getImporte()-ip.getImporte());
    			Double d = ((double) p.getImporte()/ (double) p.getImportInicial());
    	        p.setPorcentajeCompletado(Math.round(d*100));
    	        proyectoRepository.save(p);
    			userRepository.save(user);
    		}
    	}
        Long idProyecto = p.getId();
        return "redirect:/proyecto/"+idProyecto;
    }
    
    @RequestMapping(value="/addproyecto/{id}", method=RequestMethod.POST)
    public String inversorAddProyecto(@RequestParam("importe") Long importe, @PathVariable Long id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
 		String email = auth.getName();
 		User user = userRepository.findByEmail(email);
    	Proyecto proyecto = proyectoRepository.findOne(id);
        

          
          Boolean contains = false;
          for(InversorProyecto ip : user.getInversor().getInversorProyectos()){
        	  if(ip.getProyecto().getId() == proyecto.getId()){        		  
        		  contains = true;
        		  if(importe >= (proyecto.getImportInicial()-proyecto.getImporte())){
                	  user.getInversor().setImporte(user.getInversor().getImporte() - (proyecto.getImportInicial()-proyecto.getImporte()));
                	  ip.setFechaRegistro(new Date());
                	  ip.setImporte(ip.getImporte()+(proyecto.getImportInicial()-proyecto.getImporte()));
                	  proyecto.setImporte(proyecto.getImportInicial()-proyecto.getImporte());
                  }else{
                	  ip.setImporte(ip.getImporte()+importe);                	  
                	  proyecto.setImporte(proyecto.getImporte() + importe);
                	  user.getInversor().setImporte(user.getInversor().getImporte()-importe);
                  }
        	  }
          }
          if(!contains){
        	  InversorProyecto inversorProyecto = new InversorProyecto();
        	  inversorProyecto.setInversor(user.getInversor());
              inversorProyecto.setProyecto(proyecto);
              inversorProyecto.setEstado(1L);
              if(importe >= (proyecto.getImportInicial()-proyecto.getImporte())){
            	  user.getInversor().setImporte(user.getInversor().getImporte() - (proyecto.getImportInicial()-proyecto.getImporte()));
            	  inversorProyecto.setImporte(proyecto.getImportInicial()-proyecto.getImporte());
            	  proyecto.setImporte(proyecto.getImportInicial()-proyecto.getImporte());
              }else{
            	  inversorProyecto.setImporte(importe);
            	  proyecto.setImporte(proyecto.getImporte() + importe);
            	  user.getInversor().setImporte(user.getInversor().getImporte()-importe);
              }                        
      		  inversorProyecto.setFechaRegistro(new Date());
              Double d = ((double) proyecto.getImporte()/ (double) proyecto.getImportInicial());
              proyecto.setPorcentajeCompletado(Math.round(d*100));
              inversorProyectoRepository.save(inversorProyecto);                            
          }
          userRepository.save(user);
          proyectoRepository.save(proyecto);   
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
    
    @RequestMapping(value="/inversiones", method=RequestMethod.GET)
	String Inversiones(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userRepository.findByEmail(email);
		List<InversorProyecto> ips = new ArrayList<>(user.getInversor().getInversorProyectos());
		model.addAttribute("user", user);
		model.addAttribute("ips", ips);
    	model.addAttribute("filtro", new FiltroBusqueda());
		return "Busqueda/Inversiones";
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
