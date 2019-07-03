package com.grupoq.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.grupoq.app.models.entity.Cliente;


public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long> {

	@Query("select c from Cliente c left join fetch c.taller t where c.id=?1")
	public Cliente fetchByIdWithTallerWithFactura(Long id);
	
	public Cliente findByEmail(String correo);
}