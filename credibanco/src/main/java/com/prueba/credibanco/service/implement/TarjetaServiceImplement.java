package com.prueba.credibanco.service.implement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
import java.util.stream.LongStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prueba.credibanco.dto.BalanceResponseDto;
import com.prueba.credibanco.dto.BloqueoResponseDto;
import com.prueba.credibanco.dto.GenerarTarjetaRequestDto;
import com.prueba.credibanco.dto.TarjetaResponseDto;
import com.prueba.credibanco.entity.Tarjeta;
import com.prueba.credibanco.repository.TarjetaRepository;
import com.prueba.credibanco.service.interfaces.TarjetaServiceInterface;

@Service
public class TarjetaServiceImplement implements TarjetaServiceInterface {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Override
    public Tarjeta generarNumeroDeTarjeta(GenerarTarjetaRequestDto request) {
        if (request.getProductId() < 100000L || request.getProductId() > 999999) {
            throw new IllegalArgumentException("Product ID debe tener exactamente 6 dígitos");
        }
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setProductId(request.getProductId());
        tarjeta.setCardId(generarCardId(request.getProductId()));
        tarjeta.setNombre(request.getNombre());
        tarjeta.setApellido(request.getApellido());
        tarjeta.setExpiracion(LocalDate.now().plusYears(3));
        tarjeta.setBalance(BigDecimal.ZERO);
        tarjeta.setEstadoActivacion(false);
        tarjeta.setBloqueo(false);
        return tarjetaRepository.save(tarjeta);
    }
    
    private Long generarCardId(Long productId) {
    Random random = new Random();
    //Math.pow eleva a 10^i el numero aleatorio que genera random.nextint entre 0 y 9, al final todos se suman para generar los 10 digitos aparte
    long randomPart = LongStream.range(0, 10)
                                .map(i -> (long) (random.nextInt(10) * Math.pow(10, i)))
                                .sum();
    return productId * 10000000000L + randomPart;
    }

    @Override
    public Optional<TarjetaResponseDto> activarTarjeta(Long cardId) {

        validarDigitos(cardId);
        Optional<Tarjeta> tarjetaOpt = tarjetaRepository.findByCardId(cardId);
        if (tarjetaOpt.isPresent()) {
            Tarjeta tarjeta = tarjetaOpt.get();
            tarjeta.setEstadoActivacion(true);
            tarjetaRepository.save(tarjeta);

            TarjetaResponseDto response = new TarjetaResponseDto();
            response.setCardId(tarjeta.getCardId());
            response.setNombre(tarjeta.getNombre());
            response.setApellido(tarjeta.getApellido());
            response.setEstadoActivacion(tarjeta.isEstadoActivacion());

            return Optional.of(response);
        }
        return Optional.empty();
    }
    @Override
    public Optional<BloqueoResponseDto> bloquearTarjeta(Long cardId) {
        validarDigitos(cardId);
        Optional<Tarjeta> tarjetaOpt = tarjetaRepository.findByCardId(cardId);
        if (tarjetaOpt.isPresent()) {
            Tarjeta tarjeta = tarjetaOpt.get();
            tarjeta.setBloqueo(true);
            tarjetaRepository.save(tarjeta);

            BloqueoResponseDto response = new BloqueoResponseDto();
            response.setCardId(tarjeta.getCardId());
            response.setNombre(tarjeta.getNombre());
            response.setApellido(tarjeta.getApellido());
            response.setBloqueo(tarjeta.isBloqueo());

            return Optional.of(response);
        }
        return Optional.empty();
    }
    @Override
    public Optional<BalanceResponseDto> agregarBalance(Long cardId, BigDecimal balance) {
        validarDigitos(cardId);
        Optional<Tarjeta> tarjetaOpt = tarjetaRepository.findByCardId(cardId);
        if (tarjetaOpt.isPresent()) {
            Tarjeta tarjeta = tarjetaOpt.get();
            tarjeta.setBalance(tarjeta.getBalance().add(balance));
            tarjetaRepository.save(tarjeta);
            BalanceResponseDto response = new BalanceResponseDto();
            response.setCardId(tarjeta.getCardId());
            response.setNombre(tarjeta.getNombre());
            response.setApellido(tarjeta.getApellido());
            response.setBalance(tarjeta.getBalance());

            return Optional.of(response);
        }
        return Optional.empty();
    }
    @Override
    public Optional<BalanceResponseDto> getBalance(Long cardId) {
        validarDigitos(cardId);
        Optional<Tarjeta> tarjetaOpt = tarjetaRepository.findByCardId(cardId);
        if (tarjetaOpt.isPresent()) {
            Tarjeta tarjeta = tarjetaOpt.get();
            BalanceResponseDto response = new BalanceResponseDto();
            response.setCardId(tarjeta.getCardId());
            response.setNombre(tarjeta.getNombre());
            response.setApellido(tarjeta.getApellido());
            response.setBalance(tarjeta.getBalance());
            return Optional.of(response);
        }
        return Optional.empty();
    }
    private void validarDigitos(Long cardId){
        if (cardId< 1000000000000000L  || cardId > 9999999999999999L) {
            throw new IllegalArgumentException("cardId debe tener exactamente 16 dígitos");
        }
    }
}
