package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grupoq.app.models.entity.Estado;
import com.grupoq.app.models.entity.Flujo;

public interface IFlujoService {
	
	public List<Estado> findAllEstado();
	
	public Flujo findByOne(Long id);
	
	public List<Flujo> findByTaller_Cliente_Id(String id);

	public Page<Flujo> findAll(Pageable page);
	public Page<Flujo> findAllByOrderByIdDesc(Pageable page);
	
	public void save(Flujo flujo);
	
	public Estado findEstado(Long id);
	
	public List<Flujo> findByTaller_Id(Long id);
		
	public List<Flujo> findByTallerCliente(Long id);
	
	public Page<Flujo> findAllPage(Pageable page);
}
