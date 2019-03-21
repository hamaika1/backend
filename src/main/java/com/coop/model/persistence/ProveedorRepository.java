package com.coop.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coop.model.Proveedor;

// DAO ¬
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

}