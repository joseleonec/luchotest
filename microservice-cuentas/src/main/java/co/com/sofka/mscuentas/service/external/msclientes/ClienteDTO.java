package co.com.sofka.mscuentas.service.external.msclientes;

public record ClienteDTO(
    Long id,
    String nombre,
    String identificacion,
    String direccion,
    String telefono,
    Integer edad,
    String genero,
    Boolean estado
) {

}
