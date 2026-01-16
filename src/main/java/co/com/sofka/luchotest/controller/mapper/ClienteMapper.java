package co.com.sofka.luchotest.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.sofka.luchotest.controller.dto.ClienteCreateDTO;
import co.com.sofka.luchotest.persistence.entity.ClienteEntity;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nombre", source = "dto.nombre")
    @Mapping(target = "genero", source = "dto.genero")
    @Mapping(target = "edad", source = "dto.edad")
    @Mapping(target = "identificacion", source = "dto.identificacion")
    @Mapping(target = "direccion", source = "dto.direccion")
    @Mapping(target = "telefono", source = "dto.telefono")
    @Mapping(target = "clienteId", source = "dto.clienteId")
    @Mapping(target = "contrasena", source = "dto.contrasena")
    @Mapping(target = "estado", source = "dto.estado")
    ClienteEntity toEntity(ClienteCreateDTO dto);
}
