package com.startupweb.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ROL")
public class Rol {

	private long id;
	private String descripcion;
	private Set<User> users;
	
	
	public Rol() {
		super();
	}
	
	
	public Rol(long id, String descripcion, Set<User> users) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}


	@Id
    @GeneratedValue
	@Column(name = "ROL_ID")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@ManyToMany(mappedBy = "roles")
	public Set<User> getUsers() {
		return users;
	}


	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
