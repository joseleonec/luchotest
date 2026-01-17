package co.com.sofka.luchotest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.sofka.luchotest.dto.CuentaCreateDTO;
import co.com.sofka.luchotest.persistence.entity.CuentaEntity;

@Mapper(componentModel = "spring")
public interface CuentaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "numeroCuenta", source = "dto.numeroCuenta")
    @Mapping(target = "tipoCuenta", source = "dto.tipoCuenta")
    @Mapping(target = "estado", source = "dto.estado")
    @Mapping(target = "saldoInicial", source = "dto.saldoInicial")
    CuentaEntity toEntity(CuentaCreateDTO dto);
}
