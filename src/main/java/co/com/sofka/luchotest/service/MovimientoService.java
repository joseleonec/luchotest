package co.com.sofka.luchotest.service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.sofka.luchotest.exceptions.SaldoInsuficienteException;
import co.com.sofka.luchotest.persistence.entity.MovimientoEntity;
import co.com.sofka.luchotest.persistence.repositroy.MovimientoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;

    private final CuentaService cuentaService;

    @Transactional
    public MovimientoEntity crearMovimiento(MovimientoEntity movimientoEntity) {

        var cuentaId = movimientoEntity.getCuenta().getId();

        var saldoActual = cuentaService.getCuentaById(cuentaId).getSaldoInicial();

        var valorMovimiento = movimientoEntity.getValor();

        var nuevoSaldo = saldoActual.add(valorMovimiento);

        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }

        cuentaService.updateCuentaSaldo(cuentaId, nuevoSaldo);

        movimientoEntity.setSaldo(nuevoSaldo);

        return movimientoRepository.save(movimientoEntity);
    }

    public MovimientoEntity updateMovimiento(MovimientoEntity movimientoEntity) {
        if (!movimientoRepository.existsById(movimientoEntity.getId())) {
            throw new NoSuchElementException("Movimiento no encontrado con id: " + movimientoEntity.getId());
        }
        return movimientoRepository.save(movimientoEntity);
    }

    public MovimientoEntity getMovimientoById(Long id) {
        return movimientoRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Movimiento no encontrado con id: " + id)
        );
    }

    public void deleteMovimiento(Long id) {
        if (!movimientoRepository.existsById(id)) {
            throw new NoSuchElementException("Movimiento no encontrado con id: " + id);
        }
        movimientoRepository.deleteById(id);
    }
}
