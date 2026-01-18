package co.com.sofka.msclientes.dto;

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
