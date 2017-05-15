package com.startupweb.entities;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "INVERSOR_PROYECTO")
@AssociationOverrides({
    @AssociationOverride(name = "id.inversor",
        joinColumns = @JoinColumn(name = "INVERSOR_ID")),
    @AssociationOverride(name = "id.proyecto",
        joinColumns = @JoinColumn(name = "PROYECTO_ID")) })
public class InversorProyecto {

	private InversorProyectoId id = new InversorProyectoId();
    private Date fechaRegistro = new Date();
    private Long importe;

    @EmbeddedId
	public InversorProyectoId getId() {
		return id;
	}

	public void setId(InversorProyectoId id) {
		this.id = id;
	}

    @Transient
    public Inversor getInversor() {
        return getId().getInversor();
    }
 
    public void setInversor(Inversor inversor) {
        getId().setInversor(inversor);
    }
 
    @Transient
    public Proyecto getProyecto() {
        return getId().getProyecto();
    }
 
    public void setProyecto(Proyecto proyecto) {
        getId().setProyecto(proyecto);
    }

    @Column(name = "IMPORTE")
    public Long getImporte() {
        return importe;
    }

    public void setImporte(Long importe) {
        this.importe = importe;
    }

    @Column(name = "FECHA_REGISTRO")
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

}
