package co.com.sofka.mscuentas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.sofka.mscuentas.dto.MovimientoCreateDTO;
import co.com.sofka.mscuentas.dto.MovimientoDTO;
import co.com.sofka.mscuentas.mapper.MovimientoMapper;
import co.com.sofka.mscuentas.service.interfaces.IMovimientoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoMapper movimientoMapper;

    private final IMovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<MovimientoDTO> crearMovimiento(@Valid @RequestBody MovimientoCreateDTO movimiento) {

        var movimientoEntity = movimientoMapper.toEntity(movimiento);

        var movimientoCreado = movimientoService.crearMovimiento(movimientoEntity);

        var movimientoDTO = movimientoMapper.toDTO(movimientoCreado);

        return ResponseEntity.ok(movimientoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoDTO> obtenerMovimientoPorId(@PathVariable Long id) {

        var movimientoEntity = movimientoService.getMovimientoById(id);

        var movimientoDTO = movimientoMapper.toDTO(movimientoEntity);

        return ResponseEntity.ok(movimientoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoDTO> actualizarMovimiento(@PathVariable Long id, @Valid @RequestBody MovimientoCreateDTO movimiento) {

        var movimientoActualizado = movimientoMapper.toEntity(movimiento);
        movimientoActualizado.setId(id);

        var movimientoGuardado = movimientoService.updateMovimiento(movimientoActualizado);

        var movimientoDTO = movimientoMapper.toDTO(movimientoGuardado);

        return ResponseEntity.ok(movimientoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMovimiento(@PathVariable Long id) {

        movimientoService.deleteMovimiento(id);

        return ResponseEntity.noContent().build();
    }

}
