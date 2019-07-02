package com.grupoq.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.grupoq.app.models.entity.Estado;

public interface IEstadoDao extends CrudRepository<Estado, Long> {

}
