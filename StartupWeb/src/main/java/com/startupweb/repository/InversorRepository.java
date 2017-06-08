package com.startupweb.repository;

import com.startupweb.entities.Inversor;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface InversorRepository extends CrudRepository<Inversor, Long> {
	@Query("SELECT i FROM Inversor i WHERE (LOWER(:nombre) = '' or LOWER(i.user.nombre) = LOWER(:nombre)) and (:importe is null or :importe >= i.importe)")
    public List<Inversor> findByFiltro(@Param("nombre") String nombre, @Param("importe") Long importe);
}
