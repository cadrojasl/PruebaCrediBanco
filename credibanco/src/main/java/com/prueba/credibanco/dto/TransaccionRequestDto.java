package com.prueba.credibanco.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TransaccionRequestDto {
    @NotNull(message = "Card ID no puede estar vacío")
    @Positive(message = "Card ID no puede ser negativo")
    private Long cardId;
    @NotNull(message = "Price no puede estar vacío ")
    @Positive(message = "Price ID no puede ser negativo o 0")
    private BigDecimal price;
    public Long getCardId() {
        return cardId;
    }
    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
}
