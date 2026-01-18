package co.com.sofka.msclientes.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import co.com.sofka.msclientes.exceptions.ResourceAlreadyExistsException;
import co.com.sofka.msclientes.persistence.entity.ClienteEntity;
import co.com.sofka.msclientes.persistence.repositroy.ClienteRepository;
import co.com.sofka.msclientes.service.interfaces.IClienteService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteService implements IClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public ClienteEntity crearCliente(ClienteEntity clienteEntity) {

        if (clienteRepository.existsByClienteId(clienteEntity.getClienteId())) {
            throw new ResourceAlreadyExistsException("Cliente ya existe con clienteId: " + clienteEntity.getClienteId());
        }

        return clienteRepository.save(clienteEntity);
    }

    @Override
    public ClienteEntity updateCliente(ClienteEntity clienteEntity) {
        // Here we are updating by internal ID (PK Long id), distinct from business ID (String clienteId)
        if (!clienteRepository.existsById(clienteEntity.getId())) {
            throw new NoSuchElementException("Cliente no encontrado con id: " + clienteEntity.getId());
        }
        return clienteRepository.save(clienteEntity);
    }
@Override
    
    public ClienteEntity getClienteById(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Cliente no encontrado con id: " + id)
        );
    }
@Override
    
    public void deleteCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new NoSuchElementException("Cliente no encontrado con id: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
