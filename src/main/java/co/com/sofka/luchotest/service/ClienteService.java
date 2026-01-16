package co.com.sofka.luchotest.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import co.com.sofka.luchotest.persistence.entity.ClienteEntity;
import co.com.sofka.luchotest.persistence.repositroy.ClienteRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteEntity crearCliente(ClienteEntity clienteEntity) {
        return clienteRepository.save(clienteEntity);
    }

    public ClienteEntity updateCliente(ClienteEntity clienteEntity) {
        // Here we are updating by internal ID (PK Long id), distinct from business ID (String clienteId)
        if (!clienteRepository.existsById(clienteEntity.getId())) {
            throw new NoSuchElementException("Cliente no encontrado con id: " + clienteEntity.getId());
        }
        return clienteRepository.save(clienteEntity);
    }

    public ClienteEntity getClienteById(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Cliente no encontrado con id: " + id)
        );
    }

    public void deleteCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new NoSuchElementException("Cliente no encontrado con id: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
