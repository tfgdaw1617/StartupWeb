package com.startupweb.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPRESA")
public class Empresa {

	private long id;
	private String cif;
	private User user;
	private Set<Proyecto> proyectos;
	private Set<Toque> toques;
	
	public Empresa() {
	}

	public Empresa(String nombre, User user, String cif, Set<Proyecto> proyectos, Set<Toque> toques) {

		this.user = user;
		this.cif = cif;
		this.proyectos = proyectos;
		this.toques = toques;
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
		this.cif = cif;
	}
	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
	public Set<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(Set<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
	public Set<Toque> getToques() {
		return toques;
	}

	public void setToques(Set<Toque> toques) {
		this.toques = toques;
	}

}
