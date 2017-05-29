package com.startupweb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPRESA")
public class Empresa {

	private long id;
	private String nombre;
	private String direccion;
	private String cif;
	private User user;

	public Empresa() {
	}

	public Empresa(String nombre, String direccion, User user, String cif) {

		this.nombre = nombre;
		this.direccion = direccion;
		this.user = user;
		this.cif = cif;
	}

    @Id
    @GeneratedValue
	@Column(name = "EMPRESA_ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@OneToOne(mappedBy = "empresa")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		cif = cif;
	}

}
