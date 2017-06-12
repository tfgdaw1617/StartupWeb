package com.startupweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.startupweb.entities.Toque;

public interface ToqueRepository extends JpaRepository<Toque, Long> {
	@Query("Select u.inversor.toques from User u where :id = u.id order by fecha DESC")
	List<Toque> findToquesByUser(@Param("id") Long id);
}
