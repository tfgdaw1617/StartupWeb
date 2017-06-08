package com.startupweb.entities;

import java.util.Date;
//import java.util.List;
//import java.util.ArrayList;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TOQUE")
public class Toque {

	private long id;
	private Inversor inversor;
	private Empresa empresa;
	private Date fecha;
	@Column(length = 10000)
	private String mensaje;
	
	public Toque() {
	}

	public Toque(Inversor inversor, Empresa empresa, Date fecha, String mensaje) {
		this.inversor = inversor;
		this.fecha = fecha;
		this.empresa = empresa;
		this.mensaje = mensaje;
	}

    @Id
    @GeneratedValue
	@Column(name = "TOQUE_ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	@ManyToOne
    @JoinColumn(name = "INVERSOR_ID")
	public Inversor getInversor() {
		return inversor;
	}

	public void setInversor(Inversor inversor) {
		this.inversor = inversor;
	}
	@ManyToOne
    @JoinColumn(name = "EMPRESA_ID")
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
