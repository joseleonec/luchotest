package co.com.sofka.luchotest.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ClienteCreateDTO(
    
    @NotBlank
    String nombre,

    @NotBlank
    String genero,

    @NotNull
    @Positive
    Integer edad,

    @NotBlank
    String identificacion,

    @NotBlank
    String direccion,

    @NotBlank
    String telefono,

    @NotBlank
    String clienteId,

    @NotBlank
    String contrasena,

    @NotBlank
    String estado
) {
}
