package co.com.sofka.luchotest.infrastructure.adapter.out.service.interfaces;

import java.util.List;

import co.com.sofka.luchotest.infrastructure.adapter.out.persistence.persistence.entity.ClienteEntity;

public interface IClienteService {

    List<ClienteEntity> getAllClientes();

    ClienteEntity crearCliente(ClienteEntity clienteEntity);

    ClienteEntity updateCliente(ClienteEntity clienteEntity);

    ClienteEntity getClienteById(Long id);

    void deleteCliente(Long id);

    public String obtenerNombreClientePorId(Long clienteId);
}
