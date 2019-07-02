package com.grupoq.app.models.service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grupoq.app.models.entity.Cliente;
import com.grupoq.app.models.entity.Factura;
import com.grupoq.app.models.entity.Servicios;
import com.grupoq.app.models.entity.Taller;



public interface IClienteService {

	public List<Cliente> findAll();
	
	public Page<Cliente> findAll(Pageable pageable);

	public void save(Cliente cliente);
	
	public Cliente findOne(Long id);
	
	public Cliente fetchByIdWithTallerWithFactura(Long id);
	
	public void delete(Long id);
	
	//mio
	public Taller findByIdTallerWithClienteWithFactura(Long id);
	
	public List<Servicios> findByNombre(String term);
	
	public void saveFactura(Factura factura);
	
	public Servicios findProductoById(Long id);
	
	public Factura findFacturaById(Long id);
	
	public void deleteFactura(Long id);
	
	public Factura fetchFacturaByIdWithClienteWhithItemFacturaWithProducto(Long id);
}