package com.startupweb.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.startupweb.entities.InversorProyecto;
import com.startupweb.entities.Proyecto;
import com.startupweb.entities.User;
import com.startupweb.repository.UserRepository;

@Controller
public class LoginController {
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public static String Login(Model model){
		return "/login/login";
	}
	@RequestMapping(value="/access", method=RequestMethod.GET)
	public String LoginAccess(Model model) throws IOException{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userRepository.findByEmail(email);
		List<Proyecto> proyectos = new ArrayList<>();
		if(user.getRol().getDescripcion().equals("INVERSOR")){
			List<InversorProyecto> ips = new ArrayList<>(user.getInversor().getInversorProyectos());
			 for(InversorProyecto ip : ips){
					proyectos.add(ip.getProyecto());
				}
		}else{
			proyectos.addAll(user.getEmpresa().getProyectos());
		}
		model.addAttribute("proyectos", proyectos);
		model.addAttribute("user", user);
		/*byte[] encodeBase64 = Base64.encode(user.getImagen());
        String base64Encoded = new String(encodeBase64, "UTF-8");
        String image = "data:image/png;base64,"+base64Encoded;
        model.addAttribute("img",base64Encoded);*/
		return "/User/UserIndex";
	}
}