package com.startupweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.startupweb.entities.Mensaje;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
	@Query("Select m from Mensaje m where :id = m.conversacion.id order by fecha ASC")
	List<Mensaje> findMensajesById(@Param("id") Long id);
	@Query("Select m from Mensaje m where :userid = m.userTo.id and estado = 0 order by fecha ASC")
	List<Mensaje> findMensajesNoLeidos(@Param("userid") Long userid);
}