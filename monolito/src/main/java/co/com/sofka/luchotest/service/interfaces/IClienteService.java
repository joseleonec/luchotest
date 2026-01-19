package co.com.sofka.luchotest.service.interfaces;

import co.com.sofka.luchotest.persistence.entity.ClienteEntity;

public interface IClienteService {

    ClienteEntity crearCliente(ClienteEntity clienteEntity);

    ClienteEntity updateCliente(ClienteEntity clienteEntity);

    ClienteEntity getClienteById(Long id);

    void deleteCliente(Long id);

    public String obtenerNombreClientePorId(Long clienteId);
}
