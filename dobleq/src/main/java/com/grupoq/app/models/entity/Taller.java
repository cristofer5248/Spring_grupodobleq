package com.grupoq.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "talleres")
public class Taller implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idt")
	private Long id;

	@NotEmpty
	private String descripcion;	

	@Temporal(TemporalType.DATE)
	@Column(name = "create_att")
	private Date createAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_tallerid")
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "delegador")
	private Usuario delegador;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nombre_tallerid")
	private NombreTaller nombreTaller;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "condicion_id")
	private Condicion condicion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehiculo_id")
	private Vehiculo vehiculo;

	@OneToMany(mappedBy = "taller", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Factura> factura;
	
	//en caso de error
	@OneToMany(mappedBy = "taller", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Flujo> flujo;
	//solo comentarear
	private Boolean emitidoa = true;
	
	private Boolean activof = true;
	
	private Boolean texpress = false;

	public Taller() {
		factura = new ArrayList<Factura>();		
		
	}

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void addFactura(Factura factura) {
		this.factura.add(factura);
	}


	public Usuario getDelegador() {
		return delegador;
	}

	public void setDelegador(Usuario delegador) {
		this.delegador = delegador;
	}

	public NombreTaller getNombreTaller() {
		return nombreTaller;
	}

	public void setNombreTaller(NombreTaller nombreTaller) {
		this.nombreTaller = nombreTaller;
	}

	public Condicion getCondicion() {
		return condicion;
	}

	public void setCondicion(Condicion condicion) {
		this.condicion = condicion;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public List<Factura> getFactura() {
		return factura;
	}

	public void setFactura(List<Factura> factura) {
		this.factura = factura;
	}
	

	public Boolean getEmitidoa() {
		return emitidoa;
	}

	public void setEmitidoa(Boolean emitidoa) {
		this.emitidoa = emitidoa;
	}


	public Boolean getActivof() {
		return activof;
	}

	public void setActivof(Boolean activof) {
		this.activof = activof;
	}

//en caso de error
	public List<Flujo> getFlujo() {
		return flujo;
	}

	public void setFlujo(List<Flujo> flujo) {
		this.flujo = flujo;
	}
	

public Boolean getTexpress() {
		return texpress;
	}

	public void setTexpress(Boolean texpress) {
		this.texpress = texpress;
	}


	//solo comentarear
	private static final long serialVersionUID = 1L;

}
