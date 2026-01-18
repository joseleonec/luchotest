package co.com.sofka.msclientes.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import co.com.sofka.msclientes.dto.ClienteCreateDTO;
import co.com.sofka.msclientes.dto.ClienteResponseDTO;
import co.com.sofka.msclientes.persistence.entity.ClienteEntity;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "nombre", source = "dto.nombre")
    @Mapping(target = "genero", source = "dto.genero")
    @Mapping(target = "edad", source = "dto.edad")
    @Mapping(target = "identificacion", source = "dto.identificacion")
    @Mapping(target = "direccion", source = "dto.direccion")
    @Mapping(target = "telefono", source = "dto.telefono")
    @Mapping(target = "clienteId", source = "dto.clienteId")
    @Mapping(target = "contrasena", source = "dto.contrasena")
    @Mapping(target = "estado", source = "dto.estado")
    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    ClienteEntity toEntity(ClienteCreateDTO dto);

    ClienteResponseDTO toDTO(ClienteEntity entity);

}
