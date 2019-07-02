package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.grupoq.app.models.dao.IAutomovilDao;
import com.grupoq.app.models.dao.INombreTaller;
import com.grupoq.app.models.dao.ITallerDao;
import com.grupoq.app.models.dao.IUsuarioDao;
import com.grupoq.app.models.entity.NombreTaller;
import com.grupoq.app.models.entity.Taller;
import com.grupoq.app.models.entity.Usuario;
import com.grupoq.app.models.entity.Vehiculo;

@Service
public class TallerServiceImpl implements ITallerServices {

	@Autowired
	private ITallerDao tallerDao;

	@Autowired
	private IAutomovilDao automovilDao;

	@Autowired
	private IUsuarioDao usuarioDao;

	@Autowired
	private INombreTaller nombreTallerDao;

	@Override
	public void save(Taller factura) {
		tallerDao.save(factura);

	}

	@Override
	public List<Taller> findAll() {
		return (List<Taller>) tallerDao.findAll();
	}

	@Override
	public void delete(Long id) {
		tallerDao.deleteById(id);

	}

	@Override
	public List<Vehiculo> findByPlacasLikeIgnoreCase(String term) {
		return automovilDao.findByPlacasLikeIgnoreCase(term);
	}

	@Override
	public List<Vehiculo> findByPlacas(String term) {
		return automovilDao.findByPlacas(term);

	}

	@Override
	public List<Usuario> findByUsername(String term) {
		return usuarioDao.findByUsernameLike(term);
	}

	@Override
	public List<NombreTaller> findNombreTallernombre(String term) {
		return nombreTallerDao.findNombreTallernombre(term);
	}

	@Override
	public List<NombreTaller> findNombreTallernombreNative(String nombre) {

//		NombreTaller obj = 
//		
//		int a = 1;
//		long b = a;
//		ta.setId(b);
//		ta.setNombre("prueba");
//		List<NombreTaller> list1 = new ArrayList<NombreTaller>();
//		list1.add(ta);
		return nombreTallerDao.findNombreTallernombreNative(nombre);
	}

	@Override
	public Taller findByOne(Long id) {
		return tallerDao.findById(id).orElse(null);

	}

	@Override
	public Taller findByActivo(Long param, Boolean param2) {
		return tallerDao.findByIdWithActivo(param, param2);
	}

	@Override
	public Page<Taller> findAll(Pageable pageable) {
		return tallerDao.findAll(pageable);
	}

	@Override
	public List<Taller> BuscarTrueasignacionNative(Long id) {
		return tallerDao.BuscarTrueasignacionNative(id);
	}

	@Override
	public List<?> nohaynadanativo(Long id) {
		return tallerDao.nohaynadanativo(id);
	}

	@Override
	public List<Taller> buscarfalsefalse(Long id) {
		return tallerDao.Buscarfalseandfalse(id);
	}

	@Override
	public Taller findTopByOrderByIdtDscByCliente_Id(Long id) {
		return tallerDao.findTopByCliente_IdWithOrderByIdtDsc(id);
	}

	@Override
	public List<NombreTaller> findAllNombreTaller() {
		return (List<NombreTaller>) nombreTallerDao.findAll();
//		return nombreTallerDao.findJustNombreTaller();
	}

	@Override
	public List<Usuario> findByOrderByUsernameAscByRoles_Authority(String param) {
		return usuarioDao.findByRoles_AuthorityOrderByUsernameAsc(param);
	}

}
