package com.startupweb.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.startupweb.entities.Conversacion;
import com.startupweb.entities.Mensaje;
import com.startupweb.entities.User;

public interface ConversacionRepository extends JpaRepository<Conversacion, Long> {
	@Query("Select c from Conversacion c where :users = c.users")
	Conversacion findByUsers(Set<User> users);
	@Query("Select m from Mensaje m where :id = m.conversacion.id order by fecha ASC")
	List<Mensaje> findMensajesById(@Param("id") Long id);
	
}