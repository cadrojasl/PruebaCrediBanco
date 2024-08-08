package com.prueba.credibanco.service.interfaces;

import com.prueba.credibanco.dto.BalanceResponseDto;
import com.prueba.credibanco.dto.BloqueoResponseDto;
import com.prueba.credibanco.dto.GenerarTarjetaRequestDto;
import com.prueba.credibanco.dto.TarjetaResponseDto;
import com.prueba.credibanco.entity.Tarjeta;

import java.math.BigDecimal;
import java.util.Optional;

public interface TarjetaServiceInterface {
    Tarjeta generarNumeroDeTarjeta( GenerarTarjetaRequestDto request);
    Optional<TarjetaResponseDto> activarTarjeta(Long cardId);
    Optional<BloqueoResponseDto> bloquearTarjeta(Long cardId);
    Optional<BalanceResponseDto> agregarBalance(Long cardId, BigDecimal balance);
    Optional<BalanceResponseDto> getBalance(Long cardId);
}