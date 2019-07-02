package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grupoq.app.models.entity.NombreTaller;
import com.grupoq.app.models.entity.Taller;
import com.grupoq.app.models.entity.Usuario;
import com.grupoq.app.models.entity.Vehiculo;


public interface ITallerServices {
	
	public void save(Taller factura);
	public List<Taller> findAll();
	
	public List<NombreTaller> findAllNombreTaller();
	
	public List<Usuario> findByOrderByUsernameAscByRoles_Authority(String param);
	
	
	public void delete(Long id);
	public List<Vehiculo> findByPlacasLikeIgnoreCase(String term);
	
	public List<Usuario> findByUsername(String term);
	
	public List<NombreTaller> findNombreTallernombre(String term);
	
	public List<Vehiculo> findByPlacas(String term);
	
	public List<NombreTaller> findNombreTallernombreNative(String nombre);
	
	public Taller findByOne(Long id);
	
	public Taller findByActivo(Long param, Boolean param2);
	
	public Page<Taller> findAll(Pageable pageable);
	
	public List<Taller> BuscarTrueasignacionNative(Long id);
	//solo si no hay nada
	public List<?> nohaynadanativo(Long id);
	//segundo bloque si es false and true
	public List<Taller> buscarfalsefalse(Long id);
	
	public Taller findTopByOrderByIdtDscByCliente_Id(Long id);

}
