package co.com.sofka.mscuentas.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CuentaCreateDTO(
    
    @NotBlank
    String tipoCuenta,

    @NotBlank
    String numeroCuenta,
    
    @NotBlank
    String estado,

    @PositiveOrZero
    BigDecimal saldoInicial,

    @NotNull
    Long clienteId

) {}
