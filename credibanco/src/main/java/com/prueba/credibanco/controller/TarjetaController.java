package com.prueba.credibanco.controller;

import com.prueba.credibanco.dto.TarjetaResponseDto;
import com.prueba.credibanco.entity.Tarjeta;
import com.prueba.credibanco.service.interfaces.TarjetaServiceInterface;
import jakarta.validation.Valid;
import com.prueba.credibanco.dto.ActivarTarjetaRequestDto;
import com.prueba.credibanco.dto.AgregarBalanceRequestDto;
import com.prueba.credibanco.dto.BalanceResponseDto;
import com.prueba.credibanco.dto.BloqueoResponseDto;
import com.prueba.credibanco.dto.GenerarTarjetaRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("api/card")
public class TarjetaController {

    @Autowired
    private TarjetaServiceInterface tarjetaServ;

    @PostMapping("/generate") // cambie a Post porque es una inserción a la base de datos con informacion
                              // sensible
    public ResponseEntity<Tarjeta> generarNumeroDeTarjeta(@Valid @RequestBody GenerarTarjetaRequestDto request) {
        Tarjeta response = tarjetaServ.generarNumeroDeTarjeta(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/enroll") // cambie a Put porque es una actualización
    public ResponseEntity<?> activarTarjeta(@Valid @RequestBody ActivarTarjetaRequestDto request) {
        Optional<TarjetaResponseDto> response = tarjetaServ.activarTarjeta(request.getCardId());
        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("La tarjeta no existe", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{cardId}/block") // cambie a Put porque es una actualización
    public ResponseEntity<?> bloquearTarjeta(@PathVariable Long cardId) {
        Optional<BloqueoResponseDto> response = tarjetaServ.bloquearTarjeta(cardId);
        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("La tarjeta no existe", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/balance")
    public ResponseEntity<?> agregarBalance(@Valid @RequestBody AgregarBalanceRequestDto request) {
        Optional<BalanceResponseDto> response = tarjetaServ.agregarBalance(request.getCardId(), request.getBalance());
        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("La tarjeta no existe", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/balance/{cardId}")
    public ResponseEntity<?> getBalance(@PathVariable Long cardId) {
        Optional<BalanceResponseDto> response = tarjetaServ.getBalance(cardId);
        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("La tarjeta no existe", HttpStatus.NOT_FOUND);
        }
    }
}
