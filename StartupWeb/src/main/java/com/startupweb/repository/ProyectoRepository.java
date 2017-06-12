package com.startupweb.repository;

import com.startupweb.entities.Inversor;
import com.startupweb.entities.Proyecto;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProyectoRepository extends CrudRepository<Proyecto, Long> {
	public List<Proyecto> findByTitulo(String titulo);
	
	@Query("SELECT p FROM Proyecto p WHERE (LOWER(:nombre) = '' or LOWER(p.titulo) = LOWER(:nombre)) and (:importe is null or :importe >= (p.importInicial-p.importe))")
    public List<Proyecto> findByFiltro(@Param("nombre") String nombre, @Param("importe") Long importe);
}
