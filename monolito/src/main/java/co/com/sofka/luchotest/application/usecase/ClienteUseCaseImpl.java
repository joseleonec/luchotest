package co.com.sofka.luchotest.application.usecase;

import co.com.sofka.luchotest.domain.exception.ResourceAlreadyExistsException;
import co.com.sofka.luchotest.domain.exception.ResourceNotFoundException;
import co.com.sofka.luchotest.domain.model.Cliente;
import co.com.sofka.luchotest.domain.port.in.ClienteUseCase;
import co.com.sofka.luchotest.domain.port.out.ClienteRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of Cliente use case.
 * This class orchestrates the business logic for client operations.
 */
@Service
@RequiredArgsConstructor
public class ClienteUseCaseImpl implements ClienteUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepositoryPort.findAll();
    }

    @Override
    public Cliente getClienteById(Long id) {
        return clienteRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        if (clienteRepositoryPort.existsByClienteId(cliente.getClienteId())) {
            throw new ResourceAlreadyExistsException("Cliente ya existe con clienteId: " + cliente.getClienteId());
        }
        return clienteRepositoryPort.save(cliente);
    }

    @Override
    public Cliente updateCliente(Cliente cliente) {
        if (!clienteRepositoryPort.existsById(cliente.getId())) {
            throw new ResourceNotFoundException("Cliente no encontrado con id: " + cliente.getId());
        }
        return clienteRepositoryPort.save(cliente);
    }

    @Override
    public void deleteCliente(Long id) {
        if (!clienteRepositoryPort.existsById(id)) {
            throw new ResourceNotFoundException("Cliente no encontrado con id: " + id);
        }
        clienteRepositoryPort.deleteById(id);
    }

    @Override
    public String obtenerNombreClientePorId(Long clienteId) {
        Cliente cliente = clienteRepositoryPort.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + clienteId));
        return cliente.getNombre();
    }
}
