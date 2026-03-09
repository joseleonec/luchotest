package co.com.sofka.luchotest.application.usecase;

import co.com.sofka.luchotest.domain.exception.ResourceNotFoundException;
import co.com.sofka.luchotest.domain.exception.SaldoInsuficienteException;
import co.com.sofka.luchotest.domain.model.Cuenta;
import co.com.sofka.luchotest.domain.model.Movimiento;
import co.com.sofka.luchotest.domain.port.in.MovimientoUseCase;
import co.com.sofka.luchotest.domain.port.out.CuentaRepositoryPort;
import co.com.sofka.luchotest.domain.port.out.MovimientoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of Movimiento use case.
 * This class orchestrates the business logic for movement/transaction operations.
 */
@Service
@RequiredArgsConstructor
public class MovimientoUseCaseImpl implements MovimientoUseCase {

    private final MovimientoRepositoryPort movimientoRepositoryPort;
    private final CuentaRepositoryPort cuentaRepositoryPort;

    @Override
    public List<Movimiento> getAllMovimientos() {
        return movimientoRepositoryPort.findAll();
    }

    @Override
    public Movimiento getMovimientoById(Long id) {
        return movimientoRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado con id: " + id));
    }

    @Override
    public List<Movimiento> getMovimientosByCuentaId(Long cuentaId) {
        return movimientoRepositoryPort.findByCuentaId(cuentaId);
    }

    @Override
    public List<Movimiento> getMovimientosByCuentaIdAndFechaBetween(Long cuentaId, LocalDateTime startDate, LocalDateTime endDate) {
        return movimientoRepositoryPort.findByCuentaIdAndFechaBetween(cuentaId, startDate, endDate);
    }

    @Override
    @Transactional
    public Movimiento crearMovimiento(Movimiento movimiento) {
        // Get current account
        Cuenta cuenta = cuentaRepositoryPort.findById(movimiento.getCuentaId())
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id: " + movimiento.getCuentaId()));

        BigDecimal saldoAnterior = cuenta.getSaldoDisponible();
        BigDecimal valorMovimiento = movimiento.getValor();

        // If it's a withdrawal, negate the value
        if ("RETIRO".equals(movimiento.getTipoMovimiento())) {
            valorMovimiento = valorMovimiento.negate();
            movimiento.setValor(valorMovimiento);
        }

        BigDecimal nuevoSaldo = saldoAnterior.add(valorMovimiento);

        // Validate sufficient balance
        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }

        // Set movement balances
        movimiento.setSaldoInicial(saldoAnterior);
        movimiento.setSaldo(nuevoSaldo);

        // Update account balance using domain logic
        cuenta.setSaldoDisponible(nuevoSaldo);
        cuentaRepositoryPort.save(cuenta);

        // Save movement
        return movimientoRepositoryPort.save(movimiento);
    }

    @Override
    public Movimiento updateMovimiento(Movimiento movimiento) {
        if (!movimientoRepositoryPort.findById(movimiento.getId()).isPresent()) {
            throw new ResourceNotFoundException("Movimiento no encontrado con id: " + movimiento.getId());
        }
        return movimientoRepositoryPort.save(movimiento);
    }

    @Override
    public void deleteMovimiento(Long id) {
        if (!movimientoRepositoryPort.findById(id).isPresent()) {
            throw new ResourceNotFoundException("Movimiento no encontrado con id: " + id);
        }
        movimientoRepositoryPort.deleteById(id);
    }
}
