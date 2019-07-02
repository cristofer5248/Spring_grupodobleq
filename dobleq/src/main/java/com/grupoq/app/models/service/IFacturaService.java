package com.grupoq.app.models.service;

import java.util.List;
import java.util.Optional;

import com.grupoq.app.models.entity.Factura;
import com.grupoq.app.models.entity.Servicios;
import com.grupoq.app.models.entity.Taller;

public interface IFacturaService {
	
	public void save(Factura factura);
	public List<Factura> findAll();
	
	public void delete(Long id);
	
	public Optional<Factura> findById(Long id);
	
	public void edit (Factura factura);
	
	public Servicios findByidServicios(Long id);
	
	public List<Servicios> findByNombreServicio(String term);
	
	public Taller findByIdWithEmitido(Long param);
	
	public List<Taller> BuscarTrueemitidoNative(Long param);
	
	public List<?> probando(Long param);
	
	public List<?> fin(Long param); 
		
}