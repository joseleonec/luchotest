package co.com.sofka.luchotest.infrastructure.adapter.in.dto;

import java.math.BigDecimal;

public record CuentaDTO(
    Long id,
    String tipoCuenta,
    String numeroCuenta,
    BigDecimal saldoInicial,
    BigDecimal saldoDisponible,
    String estado,
    Long clienteId
) {
    
}
