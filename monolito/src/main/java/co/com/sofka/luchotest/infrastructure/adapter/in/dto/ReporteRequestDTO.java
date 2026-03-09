package co.com.sofka.luchotest.infrastructure.adapter.in.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record ReporteRequestDTO(
    @NotNull Long clienteId,
    @NotNull LocalDate fechaInicio,
    @NotNull LocalDate fechaFin
) {}
