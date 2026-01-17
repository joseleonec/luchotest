package co.com.sofka.luchotest.dto;

public record CuentaDTO(
    Long id,
    String tipoCuenta,
    Double saldoInicial,
    Boolean estado,
    Long clienteId
) {
    
}
