package com.coop.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coop.model.Producto;

// DAO ¬
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	
}
