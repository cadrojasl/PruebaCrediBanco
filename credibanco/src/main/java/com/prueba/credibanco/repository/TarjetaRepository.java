package com.prueba.credibanco.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.credibanco.entity.Tarjeta;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
    Optional<Tarjeta> findByCardId(Long cardId);
}