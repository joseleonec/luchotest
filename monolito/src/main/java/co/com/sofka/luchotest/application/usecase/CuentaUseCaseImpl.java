package co.com.sofka.luchotest.application.usecase;

import co.com.sofka.luchotest.domain.exception.ResourceNotFoundException;
import co.com.sofka.luchotest.domain.model.Cuenta;
import co.com.sofka.luchotest.domain.port.in.CuentaUseCase;
import co.com.sofka.luchotest.domain.port.out.CuentaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of Cuenta use case.
 * This class orchestrates the business logic for account operations.
 */
@Service
@RequiredArgsConstructor
public class CuentaUseCaseImpl implements CuentaUseCase {

    private final CuentaRepositoryPort cuentaRepositoryPort;

    @Override
    public List<Cuenta> getAllCuentas() {
        return cuentaRepositoryPort.findAll();
    }

    @Override
    public Cuenta getCuentaById(Long id) {
        return cuentaRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id: " + id));
    }

    @Override
    public Cuenta getCuentaByNumeroCuenta(String numeroCuenta) {
        return cuentaRepositoryPort.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con número: " + numeroCuenta));
    }

    @Override
    public List<Cuenta> getCuentasByClienteId(Long clienteId) {
        return cuentaRepositoryPort.findByClienteId(clienteId);
    }

    @Override
    public Cuenta crearCuenta(Cuenta cuenta) {
        // Initialize available balance equal to initial balance if not set
        if (cuenta.getSaldoDisponible() == null) {
            cuenta.setSaldoDisponible(cuenta.getSaldoInicial());
        }
        return cuentaRepositoryPort.save(cuenta);
    }

    @Override
    public Cuenta updateCuenta(Cuenta cuenta) {
        if (!cuentaRepositoryPort.existsById(cuenta.getId())) {
            throw new ResourceNotFoundException("Cuenta no encontrada con id: " + cuenta.getId());
        }
        return cuentaRepositoryPort.save(cuenta);
    }

    @Override
    public void deleteCuenta(Long id) {
        if (!cuentaRepositoryPort.existsById(id)) {
            throw new ResourceNotFoundException("Cuenta no encontrada con id: " + id);
        }
        cuentaRepositoryPort.deleteById(id);
    }
}
