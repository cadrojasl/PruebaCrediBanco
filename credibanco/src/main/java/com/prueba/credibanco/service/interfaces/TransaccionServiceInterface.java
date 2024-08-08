package com.prueba.credibanco.service.interfaces;

import java.math.BigDecimal;
import java.util.Optional;

import com.prueba.credibanco.entity.Transacciones;

public interface TransaccionServiceInterface {
 public Optional<Transacciones> crearTransaccion(Long cardId, BigDecimal price);
 public Optional<Transacciones> getTransaccion(Long transactionId);
 public Optional<Transacciones> anularTransaccion(Long transactionId, Long cardId);
}
