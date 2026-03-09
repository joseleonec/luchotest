package co.com.sofka.luchotest.domain.service;

import co.com.sofka.luchotest.domain.model.Cliente;
import co.com.sofka.luchotest.domain.exception.ResourceAlreadyExistsException;

import java.util.regex.Pattern;

/**
 * Domain service for Cliente business logic.
 * Contains business rules that don't depend on external infrastructure.
 */
public class ClienteDomainService {
    
    private static final Pattern PASSWORD_PATTERN = 
        Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    
    /**
     * Validates client data according to business rules
     */
    public void validarCliente(Cliente cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente es obligatorio");
        }
        
        if (cliente.getIdentificacion() == null || cliente.getIdentificacion().trim().isEmpty()) {
            throw new IllegalArgumentException("La identificación del cliente es obligatoria");
        }
        
        if (cliente.getClienteId() == null || cliente.getClienteId().trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del cliente es obligatorio");
        }
        
        if (cliente.getContrasena() != null && !isPasswordValid(cliente.getContrasena())) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres, incluyendo mayúsculas, minúsculas, números y caracteres especiales");
        }
    }
    
    /**
     * Business rule: Check if client can be created
     */
    public void puedeCrearCliente(Cliente cliente, boolean existePorId, boolean existePorClienteId) {
        validarCliente(cliente);
        
        if (existePorId) {
            throw new ResourceAlreadyExistsException("Ya existe un cliente con el ID: " + cliente.getId());
        }
        
        if (existePorClienteId) {
            throw new ResourceAlreadyExistsException("Ya existe un cliente con el clienteId: " + cliente.getClienteId());
        }
    }
    
    /**
     * Business rule: Check if client can be updated
     */
    public void puedeActualizarCliente(Cliente cliente, boolean existe) {
        validarCliente(cliente);
        
        if (!existe) {
            throw new IllegalArgumentException("No se puede actualizar un cliente que no existe");
        }
    }
    
    /**
     * Business rule: Check if client can be deleted
     */
    public void puedeEliminarCliente(boolean existe, boolean tieneCuentasActivas) {
        if (!existe) {
            throw new IllegalArgumentException("No se puede eliminar un cliente que no existe");
        }
        
        if (tieneCuentasActivas) {
            throw new IllegalArgumentException("No se puede eliminar un cliente con cuentas activas");
        }
    }
    
    /**
     * Validates password strength
     */
    private boolean isPasswordValid(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }
    
    /**
     * Business rule: Generate client ID based on business logic
     */
    public String generarClienteId(String identificacion) {
        if (identificacion == null || identificacion.trim().isEmpty()) {
            throw new IllegalArgumentException("La identificación es requerida para generar el cliente ID");
        }
        
        // Simple business rule: Take first 3 chars of identificacion + timestamp suffix
        return "CLI-" + identificacion.substring(0, Math.min(3, identificacion.length())).toUpperCase() + 
               "-" + System.currentTimeMillis() % 10000;
    }
}
