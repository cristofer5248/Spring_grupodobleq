package com.grupoq.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.grupoq.app.models.entity.Taller;

public interface ITallerDao extends PagingAndSortingRepository<Taller, Long> {
//tabla ver clientes
	//@Query("select f from Factura f join fetch f.taller t right join fetch t.cliente c where c.id=?1 and t.emitidoa=true and activof=false")
	@Query("select t from Taller t join fetch t.factura f right join fetch t.cliente c where c.id=?1 and t.emitidoa=true and activof=false")
	public Taller findByIdTallerWithClienteWithFactura(Long id);
	
	//este de arriba deberia estar en facturasDao por cuestion de orden y mvc pero dahh

	//para asignacion
	@Query("select t from Taller t  join t.cliente c where c.id=?1 and t.emitidoa=true and t.activof=true")
	public Taller findByIdWithActivo(Long param, Boolean param2);
//paara factura
	@Query("select t from Taller t  join fetch t.cliente c where t.id=?1 and t.emitidoa=true and t.activof=false")
	public Taller findByIdWithEmitido(Long param);
	
	//buscar asignacion true por native query
	//solo activof factura este verdadera
//	@Query(value ="select * from talleres t inner join clientes c on c.id=t.cliente_id where c.id=:param and t.emitidoa=true and t.activof=true", nativeQuery=true)
	@Query(value ="select * from talleres t inner join clientes c on c.id=t.cliente_tallerid where c.id=:param and t.emitidoa=true and t.activof=true limit 1", nativeQuery=true)
	public List<Taller> BuscarTrueasignacionNative(@Param("param")Long id);
	
	//segunda final si es false false
	@Query(value ="select * from talleres t inner join clientes c on c.id=t.cliente_tallerid where c.id=:param and t.emitidoa=false and t.activof=false limit 1", nativeQuery=true)
	public List<Taller> Buscarfalseandfalse(@Param("param")Long id);	
	
	@Query(value ="update set t inner join clientes c on c.id=t.cliente_tallerid where c.id=:param and t.emitidoa=true and t.activof=false", nativeQuery=true)
	public List<Taller> fin(@Param("param")Long id);
	
	@Query(value ="select * from talleres t inner join clientes c on c.id=t.cliente_tallerid where c.id=:param", nativeQuery=true)
	public List<?> nohaynadanativo(@Param("param")Long id);
	
	@Query(value="select * from talleres t inner join clientes c on c.id=t.cliente_tallerid where c.id=:param order by t.idt desc limit 1", nativeQuery = true)
	public Taller findTopByCliente_IdWithOrderByIdtDsc(@Param("param")Long id);

}
