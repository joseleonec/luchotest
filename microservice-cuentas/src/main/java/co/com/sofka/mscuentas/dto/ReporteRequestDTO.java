package co.com.sofka.mscuentas.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record ReporteRequestDTO(
    @NotNull Long clienteId,
    @NotNull LocalDate fechaInicio,
    @NotNull LocalDate fechaFin
) {}
