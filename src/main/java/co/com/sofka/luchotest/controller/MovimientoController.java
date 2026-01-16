package co.com.sofka.luchotest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.sofka.luchotest.controller.dto.MovimientoCreateDTO;
import co.com.sofka.luchotest.controller.mapper.MovimientoMapper;
import co.com.sofka.luchotest.persistence.entity.MovimientoEntity;
import co.com.sofka.luchotest.service.MovimientoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoMapper movimientoMapper;

    private final MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<MovimientoEntity> crearMovimiento(@Valid @RequestBody MovimientoCreateDTO movimiento) {

        MovimientoEntity movimientoEntity = movimientoMapper.toEntity(movimiento);

        MovimientoEntity movimientoCreado = movimientoService.crearMovimiento(movimientoEntity);

        return ResponseEntity.ok(movimientoCreado);

    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoEntity> obtenerMovimientoPorId(@PathVariable Long id) {
        MovimientoEntity movimientoEntity = movimientoService.getMovimientoById(id);
        return ResponseEntity.ok(movimientoEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoEntity> actualizarMovimiento(@PathVariable Long id, @Valid @RequestBody MovimientoCreateDTO movimiento) {

        MovimientoEntity movimientoActualizado = movimientoMapper.toEntity(movimiento);
        movimientoActualizado.setId(id);

        MovimientoEntity movimientoGuardado = movimientoService.updateMovimiento(movimientoActualizado);

        return ResponseEntity.ok(movimientoGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMovimiento(@PathVariable Long id) {
        movimientoService.deleteMovimiento(id);
        return ResponseEntity.noContent().build();
    }

}
