package com.prueba.credibanco.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.credibanco.dto.AnulacionTransaccionRequestDto;
import com.prueba.credibanco.dto.TransaccionRequestDto;
import com.prueba.credibanco.entity.Transacciones;
import com.prueba.credibanco.service.interfaces.TransaccionServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/transaction")
public class TransaccionController {
   @Autowired
    private TransaccionServiceInterface transaccionService;

    @PostMapping("/purchase")
    public ResponseEntity<?>  crearTransaccion(@Valid @RequestBody TransaccionRequestDto request) {
         Optional<Transacciones> transaccionOpt = transaccionService.crearTransaccion(request.getCardId(), request.getPrice());
            if (transaccionOpt.isPresent()) {
                return new ResponseEntity<>(transaccionOpt.get(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Transacción no válida", HttpStatus.BAD_REQUEST);
            }
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<?> getTransaction( @PathVariable Long transactionId) {
        Optional<Transacciones> transaccionOpt = transaccionService.getTransaccion(transactionId);

        if (transaccionOpt.isPresent()) {
            return new ResponseEntity<>(transaccionOpt.get(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Transacción no encontrada",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/anulation")
     public ResponseEntity<?> anulateTransaction(@Valid @RequestBody AnulacionTransaccionRequestDto request) {
     
            Optional<Transacciones> transaccionOpt = transaccionService.anularTransaccion(request.getTransactionId(), request.getCardId());
            if (transaccionOpt.isPresent()) {
                return new ResponseEntity<>(transaccionOpt.get(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Transacción no encontrada o anualción no valida",HttpStatus.NOT_FOUND);
            }
     
    }
}
