package co.com.sofka.luchotest.infrastructure.adapter.out.persistence.mapper;

import co.com.sofka.luchotest.domain.model.Cliente;
import co.com.sofka.luchotest.infrastructure.adapter.out.persistence.persistence.entity.ClienteEntity;

import org.springframework.stereotype.Component;

/**
 * Mapper between Cliente domain model and ClienteEntity (JPA entity).
 * This mapper is part of the persistence adapter.
 */
@Component
public class ClientePersistenceMapper {

    public Cliente toDomain(ClienteEntity entity) {
        if (entity == null) {
            return null;
        }

        Cliente cliente = new Cliente();
        cliente.setId(entity.getId());
        cliente.setClienteId(entity.getClienteId());
        cliente.setContrasena(entity.getContrasena());
        cliente.setEstado(entity.getEstado());
        cliente.setNombre(entity.getNombre());
        cliente.setGenero(entity.getGenero());
        cliente.setEdad(entity.getEdad());
        cliente.setIdentificacion(entity.getIdentificacion());
        cliente.setDireccion(entity.getDireccion());
        cliente.setTelefono(entity.getTelefono());

        return cliente;
    }

    public ClienteEntity toEntity(Cliente domain) {
        if (domain == null) {
            return null;
        }

        ClienteEntity entity = new ClienteEntity();
        entity.setId(domain.getId());
        entity.setClienteId(domain.getClienteId());
        entity.setContrasena(domain.getContrasena());
        entity.setEstado(domain.getEstado());
        entity.setNombre(domain.getNombre());
        entity.setGenero(domain.getGenero());
        entity.setEdad(domain.getEdad());
        entity.setIdentificacion(domain.getIdentificacion());
        entity.setDireccion(domain.getDireccion());
        entity.setTelefono(domain.getTelefono());

        return entity;
    }
}
