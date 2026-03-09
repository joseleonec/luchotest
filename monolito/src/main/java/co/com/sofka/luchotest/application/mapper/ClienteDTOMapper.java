package co.com.sofka.luchotest.application.mapper;

import co.com.sofka.luchotest.domain.model.Cliente;
import co.com.sofka.luchotest.infrastructure.adapter.in.dto.ClienteCreateDTO;
import co.com.sofka.luchotest.infrastructure.adapter.in.dto.ClienteResponseDTO;

import org.springframework.stereotype.Component;

/**
 * Mapper between Cliente domain model and DTOs.
 * This mapper is part of the application layer.
 */
@Component
public class ClienteDTOMapper {

    public Cliente toDomain(ClienteCreateDTO dto) {
        if (dto == null) {
            return null;
        }

        Cliente cliente = new Cliente();
        cliente.setNombre(dto.nombre());
        cliente.setGenero(dto.genero());
        cliente.setEdad(dto.edad());
        cliente.setIdentificacion(dto.identificacion());
        cliente.setDireccion(dto.direccion());
        cliente.setTelefono(dto.telefono());
        cliente.setClienteId(dto.clienteId());
        cliente.setContrasena(dto.contrasena());
        cliente.setEstado(dto.estado());

        return cliente;
    }

    public ClienteResponseDTO toDTO(Cliente domain) {
        if (domain == null) {
            return null;
        }

        return new ClienteResponseDTO(
                domain.getId(),
                domain.getNombre(),
                domain.getGenero(),
                domain.getEdad(),
                domain.getIdentificacion(),
                domain.getDireccion(),
                domain.getTelefono(),
                domain.getClienteId(),
                domain.getEstado()
        );
    }
}
