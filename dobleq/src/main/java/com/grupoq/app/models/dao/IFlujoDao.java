package com.grupoq.app.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.grupoq.app.models.entity.Flujo;

public interface IFlujoDao extends PagingAndSortingRepository<Flujo, Long> {

	@Query("select f from Flujo f inner join f.taller t inner join t.cliente c where c.id=?1")
	public List<Flujo> findByTallerCliente(Long id);
	
	public List<Flujo> findByTaller_Id(Long id);
	
	public Page<Flujo> findAllByOrderByIdDesc(Pageable page);
	
	public List<Flujo> findByTaller_Cliente_Email(String id);
	
}
