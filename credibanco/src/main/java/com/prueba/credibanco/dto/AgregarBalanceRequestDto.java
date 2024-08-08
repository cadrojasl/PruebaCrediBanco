package com.prueba.credibanco.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AgregarBalanceRequestDto {
    @NotNull(message = "Card ID no puede estar vacío")
    @Positive(message = "Card ID no puede ser negativo")
    private Long cardId;
    @NotNull(message = "Balance no puede estar vacío")
    @Positive(message = "Balance debe ser un valor positivo")
    private BigDecimal balance;
    public Long getCardId() {
        return cardId;
    }
    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
}
