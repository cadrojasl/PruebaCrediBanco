package com.prueba.credibanco.service.implement;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.credibanco.entity.Tarjeta;
import com.prueba.credibanco.entity.Transacciones;
import com.prueba.credibanco.repository.TarjetaRepository;
import com.prueba.credibanco.repository.TransaccionRepository;
import com.prueba.credibanco.service.interfaces.TransaccionServiceInterface;

@Service
public class TransaccionServiceImplement implements TransaccionServiceInterface  {
    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private TarjetaRepository tarjetaRepository;
    @Override
    public Optional<Transacciones> crearTransaccion(Long cardId, BigDecimal price) {
        validarDigitos(cardId);
        Optional<Tarjeta> tarjetaOpt = tarjetaRepository.findByCardId(cardId);
        if (tarjetaOpt.isPresent()) {
            Tarjeta tarjeta = tarjetaOpt.get();
            if (tarjeta.getBalance().compareTo(price) >= 0 && tarjeta.isEstadoActivacion() && !tarjeta.isBloqueo() && tarjeta.getExpiracion().isAfter(LocalDateTime.now().toLocalDate())) {
                tarjeta.setBalance(tarjeta.getBalance().subtract(price));
                tarjetaRepository.save(tarjeta);

                Transacciones transaccion = new Transacciones();
                transaccion.setCardId(cardId);
                transaccion.setMonto(price);
                transaccion.setFechaTransaccion(LocalDateTime.now());
                transaccion.setEstadoAnulado(false);
                return Optional.of(transaccionRepository.save(transaccion));
            } else {
                throw new IllegalArgumentException("Transacción no valida");
            }
        }
        return Optional.empty();
    }
    @Override
    public Optional<Transacciones> getTransaccion(Long transactionId) {
  
        return transaccionRepository.findById(transactionId);
    }
    @Override
    public Optional<Transacciones> anularTransaccion(Long transactionId, Long cardId) {
        validarDigitos(cardId);
        Optional<Transacciones> transaccionOpt = transaccionRepository.findById(transactionId);
        if (transaccionOpt.isPresent()) {
            Transacciones transaccion = transaccionOpt.get();
            if (transaccion.getFechaTransaccion().isAfter(LocalDateTime.now().minusHours(24)) &&
                transaccion.getCardId().equals(cardId) && transaccion.isEstadoAnulado().equals(false)) {
                transaccion.setEstadoAnulado(true);
                Tarjeta tarjeta = tarjetaRepository.findByCardId(cardId).get();
                tarjeta.setBalance(tarjeta.getBalance().add(transaccion.getMonto()));
                tarjetaRepository.save(tarjeta);
                return Optional.of(transaccionRepository.save(transaccion));
            }
        }
        return Optional.empty();
    }

    private void validarDigitos(Long cardId){
        if (cardId< 1000000000000000L  || cardId > 9999999999999999L) {
            throw new IllegalArgumentException("cardId debe tener exactamente 16 dígitos");
        }
    }
}
