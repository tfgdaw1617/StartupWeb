package com.startupweb.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "USER")
public class User {

	private long id;
	
	private String nombre;
	
	@Column(name = "password") 
	@Length(min = 5, message = "*Your passwordword must have at least 5 characters")
	@NotEmpty(message = "*Please provide your passwordword")
	private String password;
	private String telefono;
	@Column(name = "email")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	private Rol rol;
	private Inversor inversor;
	private Empresa empresa;
	private Set<Conversacion> conversaciones;
	private Set<Mensaje> mensajesFrom;
	private Set<Mensaje> mensajesTo;
	@Column(name = "active")
	private int active;
	private byte[] imagen;
	private String direccion;
	private Long visitas;
	private Set<Opinion> opinionTo;
	private Set<Opinion> opinionFrom;
	
	public User() {
		super();
	}
	public User(String email, String password, int active){
		super();
		this.email = email;
		this.password = password;
		this.active = active;
	}
	public User(String nombre, String password, String telefono, String email, Rol rol, Inversor inversor, Empresa empresa, Set<Conversacion> conversaciones, Set<Mensaje> mensajesFrom,Set<Mensaje> mensajesTo, int active, byte[] imagen, String direccion, Long visitas) {
		super();
		this.nombre = nombre;
		this.password = password;
		this.telefono = telefono;
		this.email = email;
		this.rol = rol;
		this.inversor = inversor;
		this.empresa = empresa;
		this.conversaciones = conversaciones;
		this.mensajesFrom = mensajesFrom;
		this.setMensajesTo(mensajesTo);
		this.active = active;
		this.imagen = imagen;
		this.setDireccion(direccion);
		this.visitas = visitas;
	}
	
	@Id
    @GeneratedValue
	@Column(name = "USER_ID")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@ManyToOne
    @JoinColumn(name = "ROL_ID")
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "INVERSOR_ID")
	public Inversor getInversor() {
		return inversor;
	}

	public void setInversor(Inversor inversor) {
		this.inversor = inversor;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPRESA_ID")
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_conversaciones", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "conversacion_id", referencedColumnName = "CONVERSACION_ID"))
	public Set<Conversacion> getConversaciones() {
		return conversaciones;
	}

	public void setConversaciones(Set<Conversacion> conversaciones) {
		this.conversaciones = conversaciones;
	}
	
	@OneToMany(mappedBy = "userFrom", cascade = CascadeType.ALL)
	public Set<Mensaje> getMensajesFrom() {
		return mensajesFrom;
	}

	public void setMensajesFrom(Set<Mensaje> mensajesFrom) {
		this.mensajesFrom =mensajesFrom;
	}

	@OneToMany(mappedBy = "userTo", cascade = CascadeType.ALL)
	public Set<Mensaje> getMensajesTo() {
		return mensajesTo;
	}

	public void setMensajesTo(Set<Mensaje> mensajesTo) {
		this.mensajesTo = mensajesTo;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	public byte[] getImagen() {
		return imagen;
	}
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Long getVisitas() {
		return visitas;
	}
	public void setVisitas(Long visitas) {
		this.visitas = visitas;
	}
	@OneToMany(mappedBy = "userTo", cascade = CascadeType.ALL)
	public Set<Opinion> getOpinionTo() {
		return opinionTo;
	}
	public void setOpinionTo(Set<Opinion> opinionTo) {
		this.opinionTo = opinionTo;
	}
	@OneToMany(mappedBy = "userFrom", cascade = CascadeType.ALL)
	public Set<Opinion> getOpinionFrom() {
		return opinionFrom;
	}
	public void setOpinionFrom(Set<Opinion> opinionFrom) {
		this.opinionFrom = opinionFrom;
	}

}
