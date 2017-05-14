package com.startupweb.repository;

import com.startupweb.entities.Proyecto;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProyectoRepository extends CrudRepository<Proyecto, Long> {
	public List<Proyecto> findByTitulo(String titulo);
}
