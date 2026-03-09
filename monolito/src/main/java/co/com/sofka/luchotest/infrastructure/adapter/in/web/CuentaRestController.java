package co.com.sofka.luchotest.infrastructure.adapter.in.web;

import co.com.sofka.luchotest.application.mapper.CuentaDTOMapper;
import co.com.sofka.luchotest.domain.model.Cuenta;
import co.com.sofka.luchotest.domain.port.in.CuentaUseCase;
import co.com.sofka.luchotest.infrastructure.adapter.in.dto.CuentaCreateDTO;
import co.com.sofka.luchotest.infrastructure.adapter.in.dto.CuentaDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Cuenta operations (Inbound Adapter).
 * This controller uses the hexagonal architecture pattern.
 */
@RestController
@RequestMapping("/api/cuentas")
@RequiredArgsConstructor
public class CuentaRestController {

    private final CuentaUseCase cuentaUseCase;
    private final CuentaDTOMapper cuentaDTOMapper;

    @PostMapping
    public ResponseEntity<CuentaDTO> crearCuenta(@Valid @RequestBody CuentaCreateDTO cuentaDTO) {
        Cuenta cuenta = cuentaDTOMapper.toDomain(cuentaDTO);
        Cuenta cuentaCreada = cuentaUseCase.crearCuenta(cuenta);
        return ResponseEntity.ok(cuentaDTOMapper.toDTO(cuentaCreada));
    }

    @GetMapping
    public ResponseEntity<List<CuentaDTO>> obtenerTodasLasCuentas() {
        List<Cuenta> cuentas = cuentaUseCase.getAllCuentas();
        return ResponseEntity.ok(cuentaDTOMapper.toDTOList(cuentas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> obtenerCuentaPorId(@PathVariable Long id) {
        Cuenta cuenta = cuentaUseCase.getCuentaById(id);
        return ResponseEntity.ok(cuentaDTOMapper.toDTO(cuenta));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<CuentaDTO>> obtenerCuentasPorClienteId(@PathVariable Long clienteId) {
        List<Cuenta> cuentas = cuentaUseCase.getCuentasByClienteId(clienteId);
        return ResponseEntity.ok(cuentaDTOMapper.toDTOList(cuentas));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaDTO> actualizarCuenta(
            @PathVariable Long id,
            @Valid @RequestBody CuentaCreateDTO cuentaDTO) {
        Cuenta cuenta = cuentaDTOMapper.toDomain(cuentaDTO);
        cuenta.setId(id);
        Cuenta cuentaActualizada = cuentaUseCase.updateCuenta(cuenta);
        return ResponseEntity.ok(cuentaDTOMapper.toDTO(cuentaActualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long id) {
        cuentaUseCase.deleteCuenta(id);
        return ResponseEntity.noContent().build();
    }
}
