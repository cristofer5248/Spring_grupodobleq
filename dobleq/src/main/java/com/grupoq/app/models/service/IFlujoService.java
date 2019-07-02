package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grupoq.app.models.entity.Estado;
import com.grupoq.app.models.entity.Flujo;

public interface IFlujoService {

	public Page<Flujo> findAll(Pageable page);
	
	public void save(Flujo flujo);
	
	public Estado findEstado(Long id);
	
	public List<Flujo> findByTaller_Id(Long id);
		
	public List<Flujo> findByTallerCliente(Long id);
}
