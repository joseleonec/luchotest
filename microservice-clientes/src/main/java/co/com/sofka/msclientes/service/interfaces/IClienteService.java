package co.com.sofka.msclientes.service.interfaces;

import co.com.sofka.msclientes.persistence.entity.ClienteEntity;

public interface IClienteService {

    ClienteEntity crearCliente(ClienteEntity clienteEntity);

    ClienteEntity updateCliente(ClienteEntity clienteEntity);

    ClienteEntity getClienteById(Long id);

    void deleteCliente(Long id);
}
