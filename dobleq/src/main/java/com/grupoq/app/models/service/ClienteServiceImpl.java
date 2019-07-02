package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.grupoq.app.models.dao.IClienteDao;
import com.grupoq.app.models.dao.IFacturaDao;
import com.grupoq.app.models.dao.IServiciosDao;
import com.grupoq.app.models.dao.ITallerDao;
import com.grupoq.app.models.entity.Cliente;
import com.grupoq.app.models.entity.Factura;
import com.grupoq.app.models.entity.Servicios;
import com.grupoq.app.models.entity.Taller;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;

	@Autowired
	private IServiciosDao serviciosDao;

	@Autowired
	private IFacturaDao facturaDao;

	@Autowired
	private ITallerDao tallerDao;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Servicios> findByNombre(String term) {
		return serviciosDao.findByNombreServicioLikeIgnoreCase("%" + term + "%");
	}

	@Override
	@Transactional
	public void saveFactura(Factura factura) {
		facturaDao.save(factura);
	}

	@Override
	@Transactional(readOnly = true)
	public Servicios findProductoById(Long id) {
		return serviciosDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Factura findFacturaById(Long id) {
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteFactura(Long id) {
		facturaDao.deleteById(id); // facturaDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Factura fetchFacturaByIdWithClienteWhithItemFacturaWithProducto(Long id) {

		return facturaDao.fetchByIdWithClienteWhithItemFacturaWithProducto(id);
	}

	@Override
	public Cliente fetchByIdWithTallerWithFactura(Long id) {

		return clienteDao.fetchByIdWithTallerWithFactura(id);
	}

	@Override
	public Taller findByIdTallerWithClienteWithFactura(Long id) {
		// mio
		return tallerDao.findByIdTallerWithClienteWithFactura(id);
	}

}
