package com.grupoq.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.grupoq.app.models.entity.NombreTaller;

public interface INombreTaller extends CrudRepository<NombreTaller, Long> {

	@Query("select n from NombreTaller n where n.nombre like %?1%")
//	@Query("select n from nombre_taller n where n.nombre like %?1%")
	public List<NombreTaller> findNombreTallernombre(String term);
	
	@Query(value ="select n.nombre, n.id from nombre_taller n where n.nombre like %:nombre%", nativeQuery=true)
	public List<NombreTaller> findNombreTallernombreNative(@Param("nombre")String nombre);
	
//	@Query(value= "select n.id, n.nombre from NombreTaller n")
//	public List<NombreTaller> findJustNombreTaller();
}
