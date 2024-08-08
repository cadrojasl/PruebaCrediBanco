package com.prueba.credibanco.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ActivarTarjetaRequestDto {

    @NotNull(message = "Card ID no puede estar vacío")
    @Positive(message = "Card ID no puede ser negativo")
    private Long cardId;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
}
