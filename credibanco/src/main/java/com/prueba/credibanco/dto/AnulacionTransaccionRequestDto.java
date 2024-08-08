package com.prueba.credibanco.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AnulacionTransaccionRequestDto {
    @NotNull(message = "Card ID no puede estar vacío")
    @Positive(message = "Card ID no puede ser negativo")
    private Long cardId;
    @NotNull(message = "Card ID no puede estar vacío")
    @Positive(message = "Card ID no puede ser negativo")
    private Long transactionId;
    public Long getCardId() {
        return cardId;
    }
    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
    public Long getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
    
}
