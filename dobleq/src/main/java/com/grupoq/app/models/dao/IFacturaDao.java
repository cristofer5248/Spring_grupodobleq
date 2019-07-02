package com.grupoq.app.models.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.grupoq.app.models.entity.Factura;
import com.grupoq.app.models.entity.Taller;

public interface IFacturaDao extends CrudRepository<Factura, Long>{

	@Query("select f from Factura f join fetch f.taller t join fetch t.cliente join fetch f.servicios s where f.id=?1")
	public Factura fetchByIdWithClienteWhithItemFacturaWithProducto(Long id);
	
	
	//buscar emitido true por native query
	//solo activof factura este verdadera
	@Query(value ="select t.idt, t.activof,t.create_att,t.descripcion,t.emitidoa from talleres t inner join clientes c on c.id=t.cliente_tallerid where c.id=:param and t.emitidoa=true and t.activof=false", nativeQuery=true)

	public List<Taller> BuscarTrueemitidoNative(@Param("param")Long id);
	
	@Query(value ="select t.idt, t.activof,t.create_att,t.descripcion,t.emitidoa from talleres t inner join clientes c on c.id=t.cliente_tallerid where c.id=:param and t.emitidoa=true and t.activof=false", nativeQuery=true)
	public List<?> probando(@Param("param")Long id);
	
	public Optional<Factura> findById(Long id);
	
}
