package co.com.sofka.luchotest.domain.port.out;

import co.com.sofka.luchotest.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

/**
 * Output port for Cliente repository operations.
 * This interface defines the contract for persistence operations
 * without depending on any specific implementation.
 */
public interface ClienteRepositoryPort {
    
    /**
     * Find all clients
     */
    List<Cliente> findAll();
    
    /**
     * Find client by ID
     */
    Optional<Cliente> findById(Long id);
    
    /**
     * Find client by client ID (business identifier)
     */
    Optional<Cliente> findByClienteId(String clienteId);
    
    /**
     * Check if client exists by ID
     */
    boolean existsById(Long id);
    
    /**
     * Check if client exists by client ID
     */
    boolean existsByClienteId(String clienteId);
    
    /**
     * Save a client (create or update)
     */
    Cliente save(Cliente cliente);
    
    /**
     * Delete client by ID
     */
    void deleteById(Long id);
}
