package co.com.sofka.luchotest.infrastructure.adapter.out.persistence.mapper;

import co.com.sofka.luchotest.domain.model.Cuenta;
import co.com.sofka.luchotest.infrastructure.adapter.out.persistence.persistence.entity.ClienteEntity;
import co.com.sofka.luchotest.infrastructure.adapter.out.persistence.persistence.entity.CuentaEntity;

import org.springframework.stereotype.Component;

/**
 * Mapper between Cuenta domain model and CuentaEntity (JPA entity).
 * This mapper is part of the persistence adapter.
 */
@Component
public class CuentaPersistenceMapper {

    public Cuenta toDomain(CuentaEntity entity) {
        if (entity == null) {
            return null;
        }

        return Cuenta.builder()
                .id(entity.getId())
                .numeroCuenta(entity.getNumeroCuenta())
                .tipoCuenta(entity.getTipoCuenta())
                .saldoInicial(entity.getSaldoInicial())
                .saldoDisponible(entity.getSaldoDisponible())
                .estado(entity.getEstado())
                .clienteId(entity.getCliente() != null ? entity.getCliente().getId() : null)
                .build();
    }

    public CuentaEntity toEntity(Cuenta domain) {
        if (domain == null) {
            return null;
        }

        CuentaEntity entity = new CuentaEntity();
        entity.setId(domain.getId());
        entity.setNumeroCuenta(domain.getNumeroCuenta());
        entity.setTipoCuenta(domain.getTipoCuenta());
        entity.setSaldoInicial(domain.getSaldoInicial());
        entity.setSaldoDisponible(domain.getSaldoDisponible());
        entity.setEstado(domain.getEstado());

        // Set cliente reference if clienteId is provided
        if (domain.getClienteId() != null) {
            ClienteEntity cliente = new ClienteEntity();
            cliente.setId(domain.getClienteId());
            entity.setCliente(cliente);
        }

        return entity;
    }
}
