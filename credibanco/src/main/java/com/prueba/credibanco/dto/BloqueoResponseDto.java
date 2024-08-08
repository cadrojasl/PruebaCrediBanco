package com.prueba.credibanco.dto;

public class BloqueoResponseDto {
        private Long cardId;
        private String nombre;
        private String apellido;
        private boolean bloqueo;
    
        // Getters y setters
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
    
        public boolean isBloqueo() {
            return bloqueo;
        }
    
        public void setBloqueo(boolean bloqueo) {
            this.bloqueo = bloqueo;
        }

}
