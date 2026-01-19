package co.com.sofka.luchotest.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import co.com.sofka.luchotest.dto.CuentaCreateDTO;
import co.com.sofka.luchotest.dto.CuentaDTO;
import co.com.sofka.luchotest.dto.CuentaReporteDTO;
import co.com.sofka.luchotest.persistence.entity.CuentaEntity;

@Mapper(componentModel = "spring")
public interface CuentaMapper {

    @Mapping(target = "numeroCuenta", source = "dto.numeroCuenta")
    @Mapping(target = "tipoCuenta", source = "dto.tipoCuenta")
    @Mapping(target = "estado", source = "dto.estado")
    @Mapping(target = "saldoInicial", source = "dto.saldoInicial")
    @Mapping(target = "cliente.id", source = "dto.clienteId")
    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    CuentaEntity toEntity(CuentaCreateDTO dto);

    @Mapping(target = "numeroCuenta", source = "entity.numeroCuenta")
    @Mapping(target = "tipoCuenta", source = "entity.tipoCuenta")
    @Mapping(target = "estado", source = "entity.estado")
    @Mapping(target = "saldoDisponible", source = "entity.saldoDisponible")
    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    CuentaReporteDTO toReporteDTO(CuentaEntity entity, String nombreCliente);

    @Mapping(target = "clienteId", source = "entity.cliente.id")
    CuentaDTO toDTO(CuentaEntity entity);
}
