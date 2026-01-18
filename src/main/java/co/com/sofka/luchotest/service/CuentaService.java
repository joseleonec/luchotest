package co.com.sofka.luchotest.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import co.com.sofka.luchotest.persistence.entity.CuentaEntity;
import co.com.sofka.luchotest.persistence.repositroy.CuentaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CuentaService {

    private final CuentaRepository cuentaRepository;

    public CuentaEntity crearCuenta(CuentaEntity cuentaEntity) {
        // Inicializar saldo disponible igual al saldo inicial si no está establecido
        if (cuentaEntity.getSaldoDisponible() == null) {
            cuentaEntity.setSaldoDisponible(cuentaEntity.getSaldoInicial());
        }
        return cuentaRepository.save(cuentaEntity);
    }

    public CuentaEntity updateCuenta(CuentaEntity cuentaEntity) {
        if (!cuentaRepository.existsById(cuentaEntity.getId())) {
            throw new NoSuchElementException("Cuenta no encontrada con id: " + cuentaEntity.getId());
        }
        return cuentaRepository.save(cuentaEntity);
    }

    public CuentaEntity getCuentaById(Long id) {
        return cuentaRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Cuenta no encontrada con id: " + id)
        );
    }

    public void deleteCuenta(Long id) {
        if (!cuentaRepository.existsById(id)) {
            throw new NoSuchElementException("Cuenta no encontrada con id: " + id);
        }
        cuentaRepository.deleteById(id);
    }

    public CuentaEntity updateCuentaSaldo(Long cuentaId, BigDecimal nuevoSaldoDisponible) {

        if (!cuentaRepository.existsById(cuentaId)) {
            throw new NoSuchElementException("Cuenta no encontrada con id: " + cuentaId);
        }

        var cuentaEntity = getCuentaById(cuentaId);

        cuentaEntity.setSaldoDisponible(nuevoSaldoDisponible);

        return cuentaRepository.save(cuentaEntity);
    }

    public List<CuentaEntity> getCuentasByClienteId(Long clienteId) {
        if (clienteId == null) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo");
        }

        return cuentaRepository.findByClienteId(clienteId);
    }

}
