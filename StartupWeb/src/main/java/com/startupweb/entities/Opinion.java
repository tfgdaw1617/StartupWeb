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
@Table(name = "OPINION")
public class Opinion {

	private long id;
	private User userTo;
	private User userFrom;
	private String mensaje;
	private Date fecha;

	public Opinion() {
	}

	
	
    public Opinion( User userTo, User userFrom, String mensaje, Date fecha) {
		super();
		this.userTo = userTo;
		this.userFrom = userFrom;
		this.mensaje = mensaje;
		this.fecha = fecha;
	}



	@Id
    @GeneratedValue
	@Column(name = "OPINION_ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

}
