package com.prueba.credibanco.dto;




import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;


public class GenerarTarjetaRequestDto {
    @NotNull
    @Positive(message = "Product ID debe ser un valor positivo")
    private Long productId;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "El nombre no debe contener números")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "El apellido no debe contener números")
    private String apellido;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

}
