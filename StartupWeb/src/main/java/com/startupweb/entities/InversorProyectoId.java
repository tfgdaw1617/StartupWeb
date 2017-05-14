package com.startupweb.entities;

import java.io.Serializable;
 
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
 
@Embeddable
public class InversorProyectoId implements Serializable {

	private Inversor inversor;
	private Proyecto proyecto;

    @ManyToOne(cascade = CascadeType.ALL)
	public Inversor getInversor() {
		return inversor;
	}

	public void setInversor(Inversor inversor) {
		this.inversor = inversor;
	}

    @ManyToOne(cascade = CascadeType.ALL)
	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

}