package com.grupoq.app.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="nombre_taller")
public class NombreTaller implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="nombre_tallerid")
	private List<Taller> taller;
	
	
	



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public List<Taller> getTaller() {
		return taller;
	}


	public void setTaller(List<Taller> taller) {
		this.taller = taller;
	}



	private static final long serialVersionUID = 1L;

}
