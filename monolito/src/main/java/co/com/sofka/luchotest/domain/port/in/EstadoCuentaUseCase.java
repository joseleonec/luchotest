package co.com.sofka.luchotest.domain.port.in;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Input port (use case) for Estado de Cuenta (Account Statement) operations.
 * This interface defines the business operations for generating account statements.
 */
public interface EstadoCuentaUseCase {
    
    /**
     * Generate account statement for a client within a date range
     * 
     * @param clienteId Client ID
     * @param startDate Start date
     * @param endDate End date
     * @return Account statement data
     */
    <T> List<T> generarEstadoCuenta(Long clienteId, LocalDateTime startDate, LocalDateTime endDate);
}
