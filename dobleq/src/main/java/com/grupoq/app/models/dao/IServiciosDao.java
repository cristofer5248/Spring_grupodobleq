package com.grupoq.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.grupoq.app.models.entity.Servicios;

public interface IServiciosDao extends CrudRepository<Servicios, Long>{

	@Query("select p from Servicios p where p.nombreServicio like %?1%")
	public List<Servicios> findByNombreServicio(String term);
	
	public List<Servicios> findByNombreServicioLikeIgnoreCase(String term);
}
