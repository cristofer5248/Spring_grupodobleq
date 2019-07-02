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


@Entity
@Table(name = "condiciones")
public class Condicion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String estadoCondicion;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="condicion_id")
	private List<Taller> taller;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEstadoCondicion() {
		return estadoCondicion;
	}

	public void setEstadoCondicion(String estadoCondicion) {
		this.estadoCondicion = estadoCondicion;
	}

	public List<Taller> getTaller() {
		return taller;
	}



	public void setTaller(List<Taller> taller) {
		this.taller = taller;
	}



	private static final long serialVersionUID = 1L;

	
}
