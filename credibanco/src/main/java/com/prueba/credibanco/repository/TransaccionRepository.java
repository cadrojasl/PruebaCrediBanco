package com.prueba.credibanco.repository;

import com.prueba.credibanco.entity.Transacciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionRepository extends JpaRepository<Transacciones, Long> {
}
