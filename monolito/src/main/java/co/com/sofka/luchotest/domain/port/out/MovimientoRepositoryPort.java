package co.com.sofka.luchotest.domain.port.out;

import co.com.sofka.luchotest.domain.model.Movimiento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Output port for Movimiento repository operations.
 * This interface defines the contract for persistence operations
 * without depending on any specific implementation.
 */
public interface MovimientoRepositoryPort {
    
    /**
     * Find all movements
     */
    List<Movimiento> findAll();
    
    /**
     * Find movement by ID
     */
    Optional<Movimiento> findById(Long id);
    
    /**
     * Find all movements by account ID
     */
    List<Movimiento> findByCuentaId(Long cuentaId);
    
    /**
     * Find movements by account ID and date range
     */
    List<Movimiento> findByCuentaIdAndFechaBetween(Long cuentaId, LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Find movements by client ID and date range
     */
    List<Movimiento> findByClienteIdAndFechaBetween(Long clienteId, LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Save a movement (create or update)
     */
    Movimiento save(Movimiento movimiento);
    
    /**
     * Delete movement by ID
     */
    void deleteById(Long id);
}
