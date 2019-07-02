package com.grupoq.app.models.service;

import java.util.List;

import com.grupoq.app.models.entity.Vehiculo;;

public interface IAutomovilService {
	
	public void saveFactura(Vehiculo vehiculo);
	
	public List<Vehiculo> findAll();

}
