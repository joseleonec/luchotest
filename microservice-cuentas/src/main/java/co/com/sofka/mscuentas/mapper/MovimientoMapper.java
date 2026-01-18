package co.com.sofka.mscuentas.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import co.com.sofka.mscuentas.dto.MovimientoCreateDTO;
import co.com.sofka.mscuentas.dto.MovimientoDTO;
import co.com.sofka.mscuentas.persistence.entity.MovimientoEntity;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {

    @Mapping(target = "cuenta.id", source = "dto.cuentaId")
    @Mapping(target = "fecha", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "tipoMovimiento", source = "dto.tipoMovimiento")
    @Mapping(target = "valor", source = "dto.valor")
    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    MovimientoEntity toEntity(MovimientoCreateDTO dto);

    @Mapping(target = "cuentaId", source = "entity.cuenta.id")
    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "fecha", source = "entity.fecha")
    @Mapping(target = "tipoMovimiento", source = "entity.tipoMovimiento")
    @Mapping(target = "valor", source = "entity.valor")
    @Mapping(target = "saldoInicial", source = "entity.saldoInicial")
    @Mapping(target = "saldo", source = "entity.saldo")
    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    MovimientoDTO toDTO(MovimientoEntity entity);

}
