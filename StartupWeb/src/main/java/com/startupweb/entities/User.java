package com.startupweb.entities;

//import java.util.List;
//import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {

	private long id;
	private String nombre;
	private String pass;
	private Integer telefono;
	private String email;
	
	
	public User() {
		super();
	}
	
	public User(String nombre, String pass, Integer telefono, String email) {
		super();
		this.nombre = nombre;
		this.pass = pass;
		this.telefono = telefono;
		this.email = email;
	}
	
	@Id
    @GeneratedValue
    @Column(name = "USER_ID")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getTelefono() {
		return telefono;
	}
	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
