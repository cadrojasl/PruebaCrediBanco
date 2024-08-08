package com.prueba.credibanco.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Transacciones {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //transactionid
    @Column(name = "card_id")
    private Long cardId;
    @Column(name = "monto")
    private BigDecimal monto;
    @Column(name = "fecha_transaccion")
    private LocalDateTime fechaTransaccion;
    @Column(name = "estado_anulado")
    private boolean estadoAnulado;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCardId() {
        return cardId;
    }
    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
    public BigDecimal getMonto() {
        return monto;
    }
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
    public LocalDateTime getFechaTransaccion() {
        return fechaTransaccion;
    }
    public void setFechaTransaccion(LocalDateTime fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }
    public boolean isEstadoAnulado() {
        return estadoAnulado;
    }
    public void setEstadoAnulado(boolean estadoAnulado) {
        this.estadoAnulado = estadoAnulado;
    }

}
