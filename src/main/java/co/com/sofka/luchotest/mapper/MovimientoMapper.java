package co.com.sofka.luchotest.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import co.com.sofka.luchotest.dto.MovimientoCreateDTO;
import co.com.sofka.luchotest.dto.MovimientoDTO;
import co.com.sofka.luchotest.persistence.entity.MovimientoEntity;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "saldo", ignore = true)
    @Mapping(target = "cuenta.id", source = "dto.cuentaId")
    @Mapping(target = "fecha", source = "dto.fecha")
    @Mapping(target = "tipoMovimiento", source = "dto.tipoMovimiento")
    @Mapping(target = "valor", source = "dto.valor")
    MovimientoEntity toEntity(MovimientoCreateDTO dto);
    
    @Mapping(target = "cuentaId", source = "entity.cuenta.id")
    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    MovimientoDTO toDTO(MovimientoEntity entity);

}
