package co.com.sofka.luchotest.dto;

import java.math.BigDecimal;


public record CuentaReporteDTO(

    Long id,
    
    String tipoCuenta,

    String numeroCuenta,

    String nombreCliente,
    
    BigDecimal saldoDisponible,

    String estado

) {}
