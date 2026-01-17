package co.com.sofka.luchotest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.sofka.luchotest.dto.EstadoCuentaDTO;
import co.com.sofka.luchotest.dto.ReporteRequestDTO;
import co.com.sofka.luchotest.service.EstadoCuntaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/reportes")
public class ReportesController {

    private final EstadoCuntaService estadoCuntaService;

    @PostMapping
    public ResponseEntity<List<EstadoCuentaDTO>> generarReporteMovimientos(@Valid @RequestBody ReporteRequestDTO reporteRequestDTO) {

        var estadosCuentas = estadoCuntaService.generarEstadoCuenta(reporteRequestDTO.clienteId(), reporteRequestDTO.fechaInicio(), reporteRequestDTO.fechaFin());

        if (estadosCuentas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(estadosCuentas);
    }
}
