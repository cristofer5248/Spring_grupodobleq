package com.grupoq.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.grupoq.app.models.entity.Vehiculo;

public interface IAutomovilDao extends PagingAndSortingRepository<Vehiculo, Long> {

	public List<Vehiculo> findByPlacasLikeIgnoreCase(String term);
	
	@Query("select v from Vehiculo v where v.placas like %?1%")
	public List<Vehiculo> findByPlacas(String term);
}


