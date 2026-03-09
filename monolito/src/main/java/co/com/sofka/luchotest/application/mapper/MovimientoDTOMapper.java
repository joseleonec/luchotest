package co.com.sofka.luchotest.application.mapper;

import co.com.sofka.luchotest.domain.model.Movimiento;
import co.com.sofka.luchotest.infrastructure.adapter.in.dto.MovimientoCreateDTO;
import co.com.sofka.luchotest.infrastructure.adapter.in.dto.MovimientoDTO;
import co.com.sofka.luchotest.infrastructure.adapter.in.dto.enums.TipoMovimientoEnum;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper between Movimiento domain model and DTOs.
 * This mapper is part of the application layer.
 */
@Component
public class MovimientoDTOMapper {

    public Movimiento toDomain(MovimientoCreateDTO dto) {
        if (dto == null) {
            return null;
        }

        return Movimiento.builder()
                .tipoMovimiento(dto.tipoMovimiento().name())
                .valor(dto.valor())
                .cuentaId(dto.cuentaId())
                .build();
    }

    public MovimientoDTO toDTO(Movimiento domain) {
        if (domain == null) {
            return null;
        }

        return new MovimientoDTO(
                domain.getId(),
                domain.getFecha(),
                TipoMovimientoEnum.valueOf(domain.getTipoMovimiento()),
                domain.getValor(),
                domain.getSaldoInicial(),
                domain.getSaldo(),
                domain.getCuentaId()
        );
    }

    public List<MovimientoDTO> toDTOList(List<Movimiento> domains) {
        if (domains == null) {
            return null;
        }

        return domains.stream()
                .map(this::toDTO)
                .toList();
    }
}
