package co.com.sofka.mscuentas.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import co.com.sofka.mscuentas.persistence.entity.CuentaEntity;
import co.com.sofka.mscuentas.persistence.repositroy.CuentaRepository;
import co.com.sofka.mscuentas.service.interfaces.ICuentaService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CuentaService implements ICuentaService {

    private final CuentaRepository cuentaRepository;

    @Override
    public CuentaEntity crearCuenta(CuentaEntity cuentaEntity) {
        // Inicializar saldo disponible igual al saldo inicial si no está establecido
        if (cuentaEntity.getSaldoDisponible() == null) {
            cuentaEntity.setSaldoDisponible(cuentaEntity.getSaldoInicial());
        }
        return cuentaRepository.save(cuentaEntity);
    }

    @Override
    public CuentaEntity updateCuenta(CuentaEntity cuentaEntity) {
        if (!cuentaRepository.existsById(cuentaEntity.getId())) {
            throw new NoSuchElementException("Cuenta no encontrada con id: " + cuentaEntity.getId());
        }
        return cuentaRepository.save(cuentaEntity);
    }

    @Override

    public CuentaEntity getCuentaById(Long id) {
        return cuentaRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Cuenta no encontrada con id: " + id)
        );
    }

    @Override

    public void deleteCuenta(Long id) {
        if (!cuentaRepository.existsById(id)) {
            throw new NoSuchElementException("Cuenta no encontrada con id: " + id);
        }
        cuentaRepository.deleteById(id);
    }

    @Override
    public CuentaEntity updateCuentaSaldo(Long cuentaId, BigDecimal nuevoSaldoDisponible) {

        if (!cuentaRepository.existsById(cuentaId)) {
            throw new NoSuchElementException("Cuenta no encontrada con id: " + cuentaId);
        }

        var cuentaEntity = getCuentaById(cuentaId);

        cuentaEntity.setSaldoDisponible(nuevoSaldoDisponible);

        return cuentaRepository.save(cuentaEntity);
    }

    @Override
    public List<CuentaEntity> getCuentasByClienteId(Long clienteId) {
        if (clienteId == null) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo");
        }

        return cuentaRepository.findByClienteId(clienteId);
    }

}
