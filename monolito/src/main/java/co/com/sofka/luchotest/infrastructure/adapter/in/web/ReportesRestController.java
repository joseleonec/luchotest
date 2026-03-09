package co.com.sofka.luchotest.infrastructure.adapter.in.web;

import co.com.sofka.luchotest.domain.port.in.EstadoCuentaUseCase;
import co.com.sofka.luchotest.infrastructure.adapter.in.dto.MovimientoDTO;
import co.com.sofka.luchotest.infrastructure.adapter.in.dto.ReporteRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * REST Controller for Reportes operations (Inbound Adapter).
 * This controller uses the hexagonal architecture pattern.
 */
@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReportesRestController {

    private final EstadoCuentaUseCase estadoCuentaUseCase;

    @GetMapping("/estado-cuenta")
    public ResponseEntity<List<MovimientoDTO>> generarEstadoCuenta(
            @RequestParam Long clienteId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
        
        List<MovimientoDTO> estadoCuenta = estadoCuentaUseCase
                .generarEstadoCuenta(clienteId, fechaInicio, fechaFin);
        
        return ResponseEntity.ok(estadoCuenta);
    }

    @PostMapping("/estado-cuenta")
    public ResponseEntity<List<MovimientoDTO>> generarEstadoCuentaConDTO(
            @Valid @RequestBody ReporteRequestDTO reporteRequest) {
        
        // Convert LocalDate to LocalDateTime
        LocalDateTime fechaInicio = reporteRequest.fechaInicio().atStartOfDay();
        LocalDateTime fechaFin = reporteRequest.fechaFin().atTime(23, 59, 59);
        
        List<MovimientoDTO> estadoCuenta = estadoCuentaUseCase
                .generarEstadoCuenta(
                        reporteRequest.clienteId(), 
                        fechaInicio, 
                        fechaFin
                );
        
        return ResponseEntity.ok(estadoCuenta);
    }

    @GetMapping("/estado-cuenta/cliente/{clienteId}")
    public ResponseEntity<List<MovimientoDTO>> generarEstadoCuentaPorCliente(
            @PathVariable Long clienteId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
        
        // If dates are not provided, use default values (last month)
        if (fechaInicio == null) {
            fechaInicio = LocalDateTime.now().minusMonths(1);
        }
        if (fechaFin == null) {
            fechaFin = LocalDateTime.now();
        }
        
        List<MovimientoDTO> estadoCuenta = estadoCuentaUseCase
                .generarEstadoCuenta(clienteId, fechaInicio, fechaFin);
        
        return ResponseEntity.ok(estadoCuenta);
    }
}
