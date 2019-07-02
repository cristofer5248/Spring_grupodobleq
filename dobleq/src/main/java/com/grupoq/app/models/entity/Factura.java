package com.grupoq.app.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "facturas")
public class Factura implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "servicios_facturaid")
	private Servicios servicios;

	@ManyToOne(fetch = FetchType.LAZY)
	private Taller taller;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "bitacora_facturaid")
	private List<Bitacora> bitacora;
	
	@Column(length = 10, nullable = true)
	private String color;

	private Integer cantidad;

	@NotEmpty
	private String descripcion;

	private String observacion;
	
	
	

	@Temporal(TemporalType.DATE)
	@Column(name = "create_at")
	private Date createAt;

	public Factura() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Taller getTaller() {
		return taller;
	}

	public void setTaller(Taller taller) {
		this.taller = taller;
	}

	public List<Bitacora> getBitacora() {
		return bitacora;
	}

	public void setBitacora(List<Bitacora> bitacora) {
		this.bitacora = bitacora;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Servicios getServicios() {
		return servicios;
	}

	public void setServicios(Servicios servicios) {
		this.servicios = servicios;
	}
	

//	public Double getTotal() {
//		Double total = 0.0;
//
//		int size = cantidad;
//
//		for (int i = 0; i < size; i++) {
//			total += servicios.getPrecio();
//		}
//		return total *cantidad;
//	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}


	private static final long serialVersionUID = 1L;



}
