package com.grupoq.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.grupoq.app.models.dao.IRolesDao;
import com.grupoq.app.models.dao.IUsuarioDao;
import com.grupoq.app.models.entity.Role;
import com.grupoq.app.models.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDao usuarioDao;

	@Autowired
	private IRolesDao rolesDao;

	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioDao.findAll(pageable);
	}

	@Override
	public void delete(Long id) {
		usuarioDao.deleteById(id);

	}

	@Override
	public Usuario findOne(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	public void save(Usuario user) {
		usuarioDao.save(user);

	}

	@Override
	public Page<Usuario> findByIdNot(Long id, Pageable pageable) {
		return usuarioDao.findByIduNotAndCliNot(id, pageable,true);
	}

	@Override
	public Usuario findByUsername(String user) {
		return usuarioDao.findByUsername(user);
	}

	@Override
	public Page<Usuario> findByRoles_Authority(String param, Pageable pageable) {
		return usuarioDao.findByRoles_AuthorityAndCliNot("ROLE_USER", pageable,true);
	}

	@Override
	public List<Role> findAllRole() {
		return (List<Role>) rolesDao.findAll();
	}

	@Override
	public void saveRol(Role param) {
		rolesDao.save(param);

	}

	@Override
	public void saveRolNative(Long id, String rol) {
		usuarioDao.saveRolNative(id, rol);
		
	}

	@Override
	public Usuario findByUsernameByRecoverypass(String user, String code) {
		return usuarioDao.findByUsernameAndRecoverypass(user, code);
	}

	@Override
	public Usuario findByidu(Long id) {
		return usuarioDao.findByidu(id);
	}

	@Override
	public Usuario findByUsernameAndCliNot(String user, Boolean noes) {
		return usuarioDao.findByUsername(user);
	}

}
