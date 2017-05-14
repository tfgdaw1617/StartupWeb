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
@Table(name = "INVERSOR")
public class Inversor {

	private long id;
	private String nombre;
	private String apellido;
	private String email;
	private Long importe;
	private Set<InversorProyecto> inversorProyectos = new HashSet<>();

	public Inversor() {
	}

	public Inversor(String nombre, String apellido, String email, Long importe) {

		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.importe = importe;
	}

    @Id
    @GeneratedValue
	@Column(name = "INVERSOR_ID")
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getImporte() {
		return importe;
	}

	public void setImporte(Long importe) {
		this.importe = importe;
	}

    @OneToMany(mappedBy = "id.inversor", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<InversorProyecto> getInversorProyectos() {
		return inversorProyectos;
	}

	public void setInversorProyectos(Set<InversorProyecto> inversorProyectos) {
		this.inversorProyectos = inversorProyectos;
	}

    public void addInversorProyecto(InversorProyecto inversorProyecto) {
        this.inversorProyectos.add(inversorProyecto);
    }

	public boolean poseeProyecto(Proyecto proyecto) {
		for (InversorProyecto ip: getInversorProyectos()) {
		  if (ip.getProyecto().getId() == proyecto.getId()) {
		    return true;
		  }
		}
		return false;
	}

}
