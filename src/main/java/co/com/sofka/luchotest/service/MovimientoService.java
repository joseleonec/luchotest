package co.com.sofka.luchotest.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.sofka.luchotest.dto.enums.TipoMovimientoEnum;
import co.com.sofka.luchotest.exceptions.SaldoInsuficienteException;
import co.com.sofka.luchotest.persistence.entity.MovimientoEntity;
import co.com.sofka.luchotest.persistence.repositroy.MovimientoRepository;
import co.com.sofka.luchotest.service.interfaces.ICuentaService;
import co.com.sofka.luchotest.service.interfaces.IMovimientoService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MovimientoService implements IMovimientoService {

    private final MovimientoRepository movimientoRepository;

    private final ICuentaService cuentaService;

    @Override
    @Transactional
    public MovimientoEntity crearMovimiento(MovimientoEntity movimientoEntity) {

        var cuentaId = movimientoEntity.getCuenta().getId();

        var cuentaActual = cuentaService.getCuentaById(cuentaId);
        var saldoAnterior = cuentaActual.getSaldoDisponible();

        var valorMovimiento = movimientoEntity.getValor();

        if (movimientoEntity.getTipoMovimiento().equals(TipoMovimientoEnum.RETIRO.name())) {
            valorMovimiento = valorMovimiento.negate();
            movimientoEntity.setValor(valorMovimiento);
        }

        var nuevoSaldo = saldoAnterior.add(valorMovimiento);

        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }

        movimientoEntity.setSaldoInicial(saldoAnterior);

        movimientoEntity.setSaldo(nuevoSaldo);

        cuentaService.updateCuentaSaldo(cuentaId, nuevoSaldo);

        return movimientoRepository.save(movimientoEntity);
    }

    @Override
    public MovimientoEntity updateMovimiento(MovimientoEntity movimientoEntity) {
        if (!movimientoRepository.existsById(movimientoEntity.getId())) {
            throw new NoSuchElementException("Movimiento no encontrado con id: " + movimientoEntity.getId());
        }
        return movimientoRepository.save(movimientoEntity);
    }

    @Override

    public MovimientoEntity getMovimientoById(Long id) {
        return movimientoRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Movimiento no encontrado con id: " + id)
        );
    }

    @Override

    public void deleteMovimiento(Long id) {
        if (!movimientoRepository.existsById(id)) {
            throw new NoSuchElementException("Movimiento no encontrado con id: " + id);
        }
        movimientoRepository.deleteById(id);
    }

    @Override
    public List<MovimientoEntity> getMovimientosByCuentaId(Long cuentaId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {

        return movimientoRepository.findByCuentaIdAndFechaBetween(cuentaId, fechaInicio, fechaFin);
    }
}
