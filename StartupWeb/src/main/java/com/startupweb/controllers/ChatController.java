package com.startupweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ChatController {
	@RequestMapping("/Mensajes")
	String chat() {
		return "Chat/Chat";
	}
	@RequestMapping("/ContactosChat")
	String contactosChat() {
		return "Chat/Contacts";
	}
}
