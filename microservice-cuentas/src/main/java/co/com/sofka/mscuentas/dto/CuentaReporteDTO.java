package co.com.sofka.mscuentas.dto;

import java.math.BigDecimal;


public record CuentaReporteDTO(

    Long id,
    
    String tipoCuenta,

    String numeroCuenta,
    
    BigDecimal saldoDisponible,

    String estado

) {}
