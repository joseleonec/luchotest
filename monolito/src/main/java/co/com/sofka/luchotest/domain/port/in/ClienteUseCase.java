package co.com.sofka.luchotest.domain.port.in;

import co.com.sofka.luchotest.domain.model.Cliente;

import java.util.List;

/**
 * Input port (use case) for Cliente operations.
 * This interface defines the business operations available for clients.
 */
public interface ClienteUseCase {
    
    /**
     * Get all clients
     */
    List<Cliente> getAllClientes();
    
    /**
     * Get client by ID
     */
    Cliente getClienteById(Long id);
    
    /**
     * Create a new client
     */
    Cliente crearCliente(Cliente cliente);
    
    /**
     * Update an existing client
     */
    Cliente updateCliente(Cliente cliente);
    
    /**
     * Delete a client by ID
     */
    void deleteCliente(Long id);
    
    /**
     * Get client name by ID
     */
    String obtenerNombreClientePorId(Long clienteId);
}
