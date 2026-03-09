package co.com.sofka.luchotest.infrastructure.adapter.in.dto;

import java.math.BigDecimal;


public record CuentaReporteDTO(

    Long id,
    
    String tipoCuenta,

    String numeroCuenta,

    String nombreCliente,
    
    BigDecimal saldoDisponible,

    String estado

) {}
