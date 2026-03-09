package co.com.sofka.luchotest.infrastructure.adapter.out.persistence.mapper;

import co.com.sofka.luchotest.domain.model.Movimiento;
import co.com.sofka.luchotest.infrastructure.adapter.out.persistence.persistence.entity.CuentaEntity;
import co.com.sofka.luchotest.infrastructure.adapter.out.persistence.persistence.entity.MovimientoEntity;

import org.springframework.stereotype.Component;

/**
 * Mapper between Movimiento domain model and MovimientoEntity (JPA entity).
 * This mapper is part of the persistence adapter.
 */
@Component
public class MovimientoPersistenceMapper {

    public Movimiento toDomain(MovimientoEntity entity) {
        if (entity == null) {
            return null;
        }

        return Movimiento.builder()
                .id(entity.getId())
                .fecha(entity.getFecha())
                .tipoMovimiento(entity.getTipoMovimiento())
                .valor(entity.getValor())
                .saldoInicial(entity.getSaldoInicial())
                .saldo(entity.getSaldo())
                .cuentaId(entity.getCuenta() != null ? entity.getCuenta().getId() : null)
                .build();
    }

    public MovimientoEntity toEntity(Movimiento domain) {
        if (domain == null) {
            return null;
        }

        MovimientoEntity entity = new MovimientoEntity();
        entity.setId(domain.getId());
        entity.setFecha(domain.getFecha());
        entity.setTipoMovimiento(domain.getTipoMovimiento());
        entity.setValor(domain.getValor());
        entity.setSaldoInicial(domain.getSaldoInicial());
        entity.setSaldo(domain.getSaldo());

        // Set cuenta reference if cuentaId is provided
        if (domain.getCuentaId() != null) {
            CuentaEntity cuenta = new CuentaEntity();
            cuenta.setId(domain.getCuentaId());
            entity.setCuenta(cuenta);
        }

        return entity;
    }
}
