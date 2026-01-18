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

import co.com.sofka.luchotest.dto.CuentaCreateDTO;
import co.com.sofka.luchotest.dto.CuentaDTO;
import co.com.sofka.luchotest.mapper.CuentaMapper;
import co.com.sofka.luchotest.service.interfaces.ICuentaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaMapper cuentaMapper;

    private final ICuentaService cuentaService;

    @PostMapping
    public ResponseEntity<CuentaDTO> crearCuenta(@Valid @RequestBody CuentaCreateDTO cuenta) {

        var cuentaEntity = cuentaMapper.toEntity(cuenta);

        var cuentaCreada = cuentaService.crearCuenta(cuentaEntity);

        var cuentaDTO = cuentaMapper.toDTO(cuentaCreada);

        return ResponseEntity.ok(cuentaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> obtenerCuentaPorId(@PathVariable Long id) {

        var cuentaEntity = cuentaService.getCuentaById(id);

        var cuentaDTO = cuentaMapper.toDTO(cuentaEntity);

        return ResponseEntity.ok(cuentaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaDTO> actualizarCuenta(@PathVariable Long id, @Valid @RequestBody CuentaCreateDTO cuenta) {

        var cuentaActualizada = cuentaMapper.toEntity(cuenta);
        cuentaActualizada.setId(id);

        var cuentaGuardada = cuentaService.updateCuenta(cuentaActualizada);

        var cuentaDTO = cuentaMapper.toDTO(cuentaGuardada);

        return ResponseEntity.ok(cuentaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long id) {

        cuentaService.deleteCuenta(id);

        return ResponseEntity.noContent().build();
    }

}
