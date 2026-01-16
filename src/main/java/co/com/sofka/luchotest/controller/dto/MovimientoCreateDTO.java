package co.com.sofka.luchotest.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovimientoCreateDTO(
    
    @NotNull
    LocalDateTime fecha,

    @NotBlank
    String tipoMovimiento,

    @NotNull
    BigDecimal valor,

    @NotNull
    BigDecimal saldo
) {
}
