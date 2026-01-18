package co.com.sofka.luchotest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClienteResponseDTO(
    Long id,
    String nombre,
    String genero,
    Integer edad,
    String identificacion,
    String direccion,
    String telefono,
    String clienteId,
    String estado
) {
}
