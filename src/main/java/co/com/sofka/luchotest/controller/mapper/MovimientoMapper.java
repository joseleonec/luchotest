package co.com.sofka.luchotest.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.sofka.luchotest.controller.dto.MovimientoCreateDTO;
import co.com.sofka.luchotest.persistence.entity.MovimientoEntity;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "saldo", ignore = true)
    @Mapping(target = "cuenta", ignore = true)
    @Mapping(target = "fecha", source = "dto.fecha")
    @Mapping(target = "tipoMovimiento", source = "dto.tipoMovimiento")
    @Mapping(target = "valor", source = "dto.valor")
    MovimientoEntity toEntity(MovimientoCreateDTO dto);
}
