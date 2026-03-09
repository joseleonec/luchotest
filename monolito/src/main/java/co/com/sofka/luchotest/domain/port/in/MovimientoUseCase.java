package co.com.sofka.luchotest.domain.port.in;

import co.com.sofka.luchotest.domain.model.Movimiento;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Input port (use case) for Movimiento operations.
 * This interface defines the business operations available for movements/transactions.
 */
public interface MovimientoUseCase {
    
    /**
     * Get all movements
     */
    List<Movimiento> getAllMovimientos();
    
    /**
     * Get movement by ID
     */
    Movimiento getMovimientoById(Long id);
    
    /**
     * Get all movements by account ID
     */
    List<Movimiento> getMovimientosByCuentaId(Long cuentaId);
    
    /**
     * Get movements by account ID and date range
     */
    List<Movimiento> getMovimientosByCuentaIdAndFechaBetween(Long cuentaId, LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Create a new movement (transaction)
     */
    Movimiento crearMovimiento(Movimiento movimiento);
    
    /**
     * Update an existing movement
     */
    Movimiento updateMovimiento(Movimiento movimiento);
    
    /**
     * Delete a movement by ID
     */
    void deleteMovimiento(Long id);
}
