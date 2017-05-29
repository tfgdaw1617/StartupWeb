package com.startupweb.repository;

import com.startupweb.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolRepository extends JpaRepository<Rol, Long> {
	 Rol findByDescripcion(String descripcion);
}
