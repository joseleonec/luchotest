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

import co.com.sofka.luchotest.controller.dto.CuentaCreateDTO;
import co.com.sofka.luchotest.controller.mapper.CuentaMapper;
import co.com.sofka.luchotest.persistence.entity.CuentaEntity;
import co.com.sofka.luchotest.service.CuentaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaMapper cuentaMapper;

    private final CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<CuentaEntity> crearCuenta(@Valid @RequestBody CuentaCreateDTO cuenta) {

        CuentaEntity cuentaEntity = cuentaMapper.toEntity(cuenta);

        CuentaEntity cuentaCreada = cuentaService.crearCuenta(cuentaEntity);

        return ResponseEntity.ok(cuentaCreada);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaEntity> obtenerCuentaPorId(@PathVariable Long id) {
        CuentaEntity cuentaEntity = cuentaService.getCuentaById(id);
        return ResponseEntity.ok(cuentaEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaEntity> actualizarCuenta(@PathVariable Long id, @Valid @RequestBody CuentaCreateDTO cuenta) {

        CuentaEntity cuentaActualizada = cuentaMapper.toEntity(cuenta);
        cuentaActualizada.setId(id);

        CuentaEntity cuentaGuardada = cuentaService.updateCuenta(cuentaActualizada);

        return ResponseEntity.ok(cuentaGuardada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long id) {
        cuentaService.deleteCuenta(id);
        return ResponseEntity.noContent().build();
    }

}
