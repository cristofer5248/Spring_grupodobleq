package com.grupoq.app.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupoq.app.models.dao.IFacturaDao;
import com.grupoq.app.models.dao.IServiciosDao;
import com.grupoq.app.models.dao.ITallerDao;
import com.grupoq.app.models.entity.Factura;
import com.grupoq.app.models.entity.Servicios;
import com.grupoq.app.models.entity.Taller;

@Service
public class FacturaServiceImp implements IFacturaService {

	@Autowired
	private IFacturaDao facturaDao;

	@Autowired
	private IServiciosDao serviciosDao;

	@Autowired
	private ITallerDao tallerDao;

	@Override
	public void save(Factura factura) {
		facturaDao.save(factura);
	}

	@Override
	public List<Factura> findAll() {

		return (List<Factura>) facturaDao.findAll();
	}

	@Override
	public void delete(Long id) {
		facturaDao.deleteById(id);

	}

	@Override
	public void edit(Factura factura) {
		// ya el update es en el save

	}

	@Override
	public List<Servicios> findByNombreServicio(String term) {
		return serviciosDao.findByNombreServicio(term);
	}

	@Override
	public Taller findByIdWithEmitido(Long param) {
		return tallerDao.findByIdWithEmitido(param);
	}

	@Override
	public List<Taller> BuscarTrueemitidoNative(Long param) {
		return facturaDao.BuscarTrueemitidoNative(param);
	}

	@Override
	public List<?> probando(Long param) {
		return facturaDao.probando(param);
	}

	@Override
	public Optional<Factura> findById(Long id) {
		return facturaDao.findById(id);
	}

	@Override
	public List<?> fin(Long param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Servicios findByidServicios(Long id) {
		return serviciosDao.findById(id).orElse(null);
	}

}
