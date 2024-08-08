package com.prueba.credibanco.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_id")
    private Long productId;
    @Column(unique = true, name ="card_id")
    private Long cardId;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "expiracion")
    private LocalDate expiracion;
    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "estado_activacion")
    private boolean estadoActivacion;
    @Column(name = "bloqueo")
    private boolean bloqueo;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public Long getCardId() {
        return cardId;
    }
    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public LocalDate getExpiracion() {
        return expiracion;
    }
    public void setExpiracion(LocalDate expiracion) {
        this.expiracion = expiracion;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public boolean isEstadoActivacion() {
        return estadoActivacion;
    }
    public void setEstadoActivacion(boolean estadoActivacion) {
        this.estadoActivacion = estadoActivacion;
    }
    public boolean isBloqueo() {
        return bloqueo;
    }
    public void setBloqueo(boolean bloqueo) {
        this.bloqueo = bloqueo;
    }
    

}
