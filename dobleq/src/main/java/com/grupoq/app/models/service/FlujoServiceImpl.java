package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.grupoq.app.models.dao.IEstadoDao;
import com.grupoq.app.models.dao.IFlujoDao;
import com.grupoq.app.models.entity.Estado;
import com.grupoq.app.models.entity.Flujo;

@Service
public class FlujoServiceImpl implements IFlujoService {

	@Autowired
	private IFlujoDao flujoDao;

	@Autowired
	private IEstadoDao estadoDao;

	@Override
	public Page<Flujo> findAll(Pageable page) {
		return flujoDao.findAll(page);
	}

	@Override
	public void save(Flujo flujo) {
		flujoDao.save(flujo);

	}

	@Override
	public Estado findEstado(Long id) {
		return estadoDao.findById(id).orElse(null);
	}

	@Override
	public List<Flujo> findByTallerCliente(Long id) {
		return flujoDao.findByTallerCliente(id);
	}

	@Override
	public List<Flujo> findByTaller_Id(Long id) {
		return flujoDao.findByTaller_Id(id);
	}

	@Override
	public Page<Flujo> findAllPage(Pageable page) {
		return flujoDao.findAll(page);
	}

}
