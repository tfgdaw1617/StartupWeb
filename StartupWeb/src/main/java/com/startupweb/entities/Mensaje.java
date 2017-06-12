package com.startupweb.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MENSAJE")
public class Mensaje {

	private long id;
	private Conversacion conversacion;
	private User userTo;
	private User userFrom;
	private String mensaje;
	private Date fecha;
	private Integer estado;

	public Mensaje() {
	}

	
	
    public Mensaje(Conversacion conversacion, User userTo, User userFrom, String mensaje, Date fecha, Integer estado) {
		super();
		this.conversacion = conversacion;
		this.userTo = userTo;
		this.userFrom = userFrom;
		this.mensaje = mensaje;
		this.setFecha(fecha);
		this.estado = estado;
	}



	@Id
    @GeneratedValue
	@Column(name = "MENSAJE_ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne
    @JoinColumn(name = "CONVERSACION_ID")
	public Conversacion getConversacion() {
		return conversacion;
	}


	public void setConversacion(Conversacion conversacion) {
		this.conversacion = conversacion;
	}

	@ManyToOne
    @JoinColumn(name = "USER_FROM_ID")
	public User getUserFrom() {
		return userFrom;
	}


	public void setUserFrom(User userFrom) {
		this.userFrom = userFrom;
	}


	@ManyToOne
    @JoinColumn(name = "USER_TO_ID")
	public User getUserTo() {
		return userTo;
	}



	public void setUserTo(User userTo) {
		this.userTo = userTo;
	}



	public String getMensaje() {
		return mensaje;
	}



	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public Integer getEstado() {
		return estado;
	}



	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}
