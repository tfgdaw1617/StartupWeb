package com.startupweb.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {

	private long id;
	private String nombre;
	private String pass;
	private Integer telefono;
	private String email;
	private Set<Rol> roles;
	private Inversor inversor;
	
	
	public User() {
		super();
	}
	
	public User(String nombre, String pass, Integer telefono, String email, Set<Rol> roles, Inversor inversor) {
		super();
		this.nombre = nombre;
		this.pass = pass;
		this.telefono = telefono;
		this.email = email;
		this.roles = roles;
		this.inversor = inversor;
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
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "ROL_ID"))
	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "INVERSOR_ID")
	public Inversor getInversor() {
		return inversor;
	}

	public void setInversor(Inversor inversor) {
		this.inversor = inversor;
	}
	

}
