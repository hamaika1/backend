package com.coop.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coop.model.Rubro;

// DAO ¬
@Repository
public interface RubroRepository extends JpaRepository<Rubro, Long> {

}