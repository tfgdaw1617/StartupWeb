package com.startupweb.entities;

import java.util.Date;

public class FiltroBusqueda {
	private String nombre;
	private Long sector;
	private Integer cantidad;
	private Date fechaDesde;
	private Date fechaHasta;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getSector() {
		return sector;
	}
	public void setSector(Long sector) {
		this.sector = sector;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
}
