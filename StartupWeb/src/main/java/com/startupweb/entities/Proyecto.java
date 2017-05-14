package com.startupweb.entities;

//import java.util.List;
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
@Table(name = "PROYECTO")
public class Proyecto {

	private long id;
    private String titulo;
    private String descripcion;
	private Long importe;
	private Set<InversorProyecto> proyectoInversores = new HashSet<>();

    public Proyecto() {
    }

    public Proyecto(String titulo, String descripcion, Long importe) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.importe = importe;
	}

    @Id
    @GeneratedValue
	@Column(name = "PROYECTO_ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getImporte() {
		return importe;
	}

	public void setImporte(Long importe) {
		this.importe = importe;
	}

    @OneToMany(mappedBy = "id.proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<InversorProyecto> getProyectoInversores() {
		return proyectoInversores;
	}

	public void setProyectoInversores(Set<InversorProyecto> proyectoInversores) {
		this.proyectoInversores = proyectoInversores;
	}

    public void addProyectoInversor(InversorProyecto proyectoInversor) {
        this.proyectoInversores.add(proyectoInversor);
    }
}
