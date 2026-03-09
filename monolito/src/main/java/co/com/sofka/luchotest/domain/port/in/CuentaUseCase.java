package co.com.sofka.luchotest.domain.port.in;

import co.com.sofka.luchotest.domain.model.Cuenta;

import java.util.List;

/**
 * Input port (use case) for Cuenta operations.
 * This interface defines the business operations available for accounts.
 */
public interface CuentaUseCase {
    
    /**
     * Get all accounts
     */
    List<Cuenta> getAllCuentas();
    
    /**
     * Get account by ID
     */
    Cuenta getCuentaById(Long id);
    
    /**
     * Get account by account number
     */
    Cuenta getCuentaByNumeroCuenta(String numeroCuenta);
    
    /**
     * Get all accounts by client ID
     */
    List<Cuenta> getCuentasByClienteId(Long clienteId);
    
    /**
     * Create a new account
     */
    Cuenta crearCuenta(Cuenta cuenta);
    
    /**
     * Update an existing account
     */
    Cuenta updateCuenta(Cuenta cuenta);
    
    /**
     * Delete an account by ID
     */
    void deleteCuenta(Long id);
}
