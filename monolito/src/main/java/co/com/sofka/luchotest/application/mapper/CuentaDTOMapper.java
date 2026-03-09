package co.com.sofka.luchotest.application.mapper;

import co.com.sofka.luchotest.domain.model.Cuenta;
import co.com.sofka.luchotest.infrastructure.adapter.in.dto.CuentaCreateDTO;
import co.com.sofka.luchotest.infrastructure.adapter.in.dto.CuentaDTO;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper between Cuenta domain model and DTOs.
 * This mapper is part of the application layer.
 */
@Component
public class CuentaDTOMapper {

    public Cuenta toDomain(CuentaCreateDTO dto) {
        if (dto == null) {
            return null;
        }

        return Cuenta.builder()
                .numeroCuenta(dto.numeroCuenta())
                .tipoCuenta(dto.tipoCuenta())
                .saldoInicial(dto.saldoInicial())
                .saldoDisponible(dto.saldoInicial()) // Initially same as initial
                .estado("ACTIVA")
                .clienteId(dto.clienteId())
                .build();
    }

    public CuentaDTO toDTO(Cuenta domain) {
        if (domain == null) {
            return null;
        }

        return new CuentaDTO(
                domain.getId(),
                domain.getNumeroCuenta(),
                domain.getTipoCuenta(),
                domain.getSaldoInicial(),
                domain.getSaldoDisponible(),
                domain.getEstado(),
                domain.getClienteId()
        );
    }

    public List<CuentaDTO> toDTOList(List<Cuenta> domains) {
        if (domains == null) {
            return null;
        }

        return domains.stream()
                .map(this::toDTO)
                .toList();
    }
}
