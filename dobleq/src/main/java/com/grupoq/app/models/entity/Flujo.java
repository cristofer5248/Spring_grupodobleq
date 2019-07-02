package com.grupoq.app.models.entity;

import java.io.Serializable;
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

@Entity
@Table(name = "flujos")
public class Flujo implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	@Column(length = 40)
	private String comentario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estados_id")
	private Estado estado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="servicio_flujo")
	private Servicios servicios;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="taller_flujo")
	private Taller taller;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "bitacora_id_flujo")
	private List<Bitacora> bitacora;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Servicios getServicios() {
		return servicios;
	}

	public void setServicios(Servicios servicios) {
		this.servicios = servicios;
	}

	public String getComentario() {
		return comentario;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}	



	public List<Bitacora> getBitacora() {
		return bitacora;
	}

	public void setBitacora(List<Bitacora> bitacora) {
		this.bitacora = bitacora;
	}

	//porque lo quite?? si no solo comentariar
	public Taller getTaller() {
		return taller;
	}

	public void setTaller(Taller taller) {
		this.taller = taller;
	}




	private static final long serialVersionUID = 1L;
}
