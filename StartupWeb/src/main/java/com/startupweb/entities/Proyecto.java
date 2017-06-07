package com.startupweb.entities;

//import java.util.List;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PROYECTO")
public class Proyecto {

	private long id;
    private String titulo;
    @Column(length = 100000000)
    private String descripcion;
	private Long importe;
	private Long importInicial;
	private Set<InversorProyecto> proyectoInversores = new HashSet<>();
	private Empresa empresa;

    public Proyecto() {
    }

    public Proyecto(String titulo, String descripcion, Long importe, Empresa empresa, Long importeInicial) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.importe = importe;
		this.empresa = empresa;
		this.importInicial = importeInicial;
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
    
    @ManyToOne
    @JoinColumn(name = "EMPRESA_ID")
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Long getImportInicial() {
		return importInicial;
	}

	public void setImportInicial(Long importInicial) {
		this.importInicial = importInicial;
	}
}
