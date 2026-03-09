package co.com.sofka.luchotest.domain.port.out;

import co.com.sofka.luchotest.domain.model.Cuenta;

import java.util.List;
import java.util.Optional;

/**
 * Output port for Cuenta repository operations.
 * This interface defines the contract for persistence operations
 * without depending on any specific implementation.
 */
public interface CuentaRepositoryPort {
    
    /**
     * Find all accounts
     */
    List<Cuenta> findAll();
    
    /**
     * Find account by ID
     */
    Optional<Cuenta> findById(Long id);
    
    /**
     * Find account by account number
     */
    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
    
    /**
     * Find all accounts by client ID
     */
    List<Cuenta> findByClienteId(Long clienteId);
    
    /**
     * Check if account exists by ID
     */
    boolean existsById(Long id);
    
    /**
     * Check if account exists by account number
     */
    boolean existsByNumeroCuenta(String numeroCuenta);
    
    /**
     * Save an account (create or update)
     */
    Cuenta save(Cuenta cuenta);
    
    /**
     * Delete account by ID
     */
    void deleteById(Long id);
}
