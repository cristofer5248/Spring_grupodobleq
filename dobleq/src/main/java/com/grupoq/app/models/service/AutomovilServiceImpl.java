package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupoq.app.models.dao.IAutomovilDao;
import com.grupoq.app.models.entity.Vehiculo;

@Service
public class AutomovilServiceImpl implements IAutomovilService {

	@Autowired
	private IAutomovilDao automovilDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Vehiculo> findAll() {
		// TODO Auto-generated method stub
		return (List<Vehiculo>) automovilDao.findAll();
	}


	@Override
	public void saveFactura(Vehiculo vehiculo) {
		automovilDao.save(vehiculo);		
	}
}
