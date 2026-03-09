package co.com.sofka.luchotest.infrastructure.adapter.in.web;

import co.com.sofka.luchotest.application.mapper.MovimientoDTOMapper;
import co.com.sofka.luchotest.domain.model.Movimiento;
import co.com.sofka.luchotest.domain.port.in.MovimientoUseCase;
import co.com.sofka.luchotest.infrastructure.adapter.in.dto.MovimientoCreateDTO;
import co.com.sofka.luchotest.infrastructure.adapter.in.dto.MovimientoDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Movimiento operations (Inbound Adapter).
 * This controller uses the hexagonal architecture pattern.
 */
@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
public class MovimientoRestController {

    private final MovimientoUseCase movimientoUseCase;
    private final MovimientoDTOMapper movimientoDTOMapper;

    @PostMapping
    public ResponseEntity<MovimientoDTO> crearMovimiento(@Valid @RequestBody MovimientoCreateDTO movimientoDTO) {
        Movimiento movimiento = movimientoDTOMapper.toDomain(movimientoDTO);
        Movimiento movimientoCreado = movimientoUseCase.crearMovimiento(movimiento);
        return ResponseEntity.ok(movimientoDTOMapper.toDTO(movimientoCreado));
    }

    @GetMapping
    public ResponseEntity<List<MovimientoDTO>> obtenerTodosLosMovimientos() {
        List<Movimiento> movimientos = movimientoUseCase.getAllMovimientos();
        return ResponseEntity.ok(movimientoDTOMapper.toDTOList(movimientos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoDTO> obtenerMovimientoPorId(@PathVariable Long id) {
        Movimiento movimiento = movimientoUseCase.getMovimientoById(id);
        return ResponseEntity.ok(movimientoDTOMapper.toDTO(movimiento));
    }

    @GetMapping("/cuenta/{cuentaId}")
    public ResponseEntity<List<MovimientoDTO>> obtenerMovimientosPorCuentaId(@PathVariable Long cuentaId) {
        List<Movimiento> movimientos = movimientoUseCase.getMovimientosByCuentaId(cuentaId);
        return ResponseEntity.ok(movimientoDTOMapper.toDTOList(movimientos));
    }

    @GetMapping("/cuenta/{cuentaId}/fechas")
    public ResponseEntity<List<MovimientoDTO>> obtenerMovimientosPorCuentaYFechas(
            @PathVariable Long cuentaId,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {
        
        // Parse dates - this is simplified, in a real app you'd use proper date parsing
        java.time.LocalDateTime startDate = java.time.LocalDateTime.parse(fechaInicio);
        java.time.LocalDateTime endDate = java.time.LocalDateTime.parse(fechaFin);
        
        List<Movimiento> movimientos = movimientoUseCase.getMovimientosByCuentaIdAndFechaBetween(cuentaId, startDate, endDate);
        return ResponseEntity.ok(movimientoDTOMapper.toDTOList(movimientos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMovimiento(@PathVariable Long id) {
        movimientoUseCase.deleteMovimiento(id);
        return ResponseEntity.noContent().build();
    }
}
