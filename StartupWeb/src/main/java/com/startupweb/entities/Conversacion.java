package com.startupweb.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;

@Entity
@Table(name = "CONVERSACION")
public class Conversacion {

	private long id;
	private Set<User> users;
	private Set<Mensaje> mensajes;



    public Conversacion() {
		super();
	}
    
    
	public Conversacion(long id, Set<User> users, Set<Mensaje> mensajes) {
		super();
		this.id = id;
		this.users = users;
		this.mensajes = mensajes;
	}


	@Id
    @GeneratedValue
	@Column(name = "CONVERSACION_ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToMany(mappedBy = "conversaciones")
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	
	@OneToMany(mappedBy = "conversacion", cascade = CascadeType.ALL)
	public Set<Mensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(Set<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

}
