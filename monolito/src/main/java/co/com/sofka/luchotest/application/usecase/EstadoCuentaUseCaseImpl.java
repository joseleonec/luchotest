package co.com.sofka.luchotest.application.usecase;

import co.com.sofka.luchotest.domain.model.Cliente;
import co.com.sofka.luchotest.domain.model.Cuenta;
import co.com.sofka.luchotest.domain.model.Movimiento;
import co.com.sofka.luchotest.domain.port.in.EstadoCuentaUseCase;
import co.com.sofka.luchotest.domain.port.out.ClienteRepositoryPort;
import co.com.sofka.luchotest.domain.port.out.CuentaRepositoryPort;
import co.com.sofka.luchotest.domain.port.out.MovimientoRepositoryPort;
import co.com.sofka.luchotest.domain.service.EstadoCuentaDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of EstadoCuenta use case.
 * This class orchestrates the business logic for generating account statements.
 */
@Service
@RequiredArgsConstructor
public class EstadoCuentaUseCaseImpl implements EstadoCuentaUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;
    private final CuentaRepositoryPort cuentaRepositoryPort;
    private final MovimientoRepositoryPort movimientoRepositoryPort;
    private final EstadoCuentaDomainService estadoCuentaDomainService;

    @Override
    public <T> List<T> generarEstadoCuenta(Long clienteId, LocalDateTime startDate, LocalDateTime endDate) {
        // Validate date range
        estadoCuentaDomainService.validarRangoFechas(startDate, endDate);
        
        // Get client
        Cliente cliente = clienteRepositoryPort.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado: " + clienteId));
        
        // Validate client
        estadoCuentaDomainService.validarClienteParaReporte(cliente);
        
        // Get client accounts
        List<Cuenta> cuentas = cuentaRepositoryPort.findByClienteId(clienteId);
        
        if (cuentas.isEmpty()) {
            throw new IllegalArgumentException("El cliente no tiene cuentas registradas");
        }
        
        // For now, return movements from the first account
        // This could be extended to handle multiple accounts
        Cuenta cuenta = cuentas.get(0);
        estadoCuentaDomainService.validarCuentaParaReporte(cuenta, clienteId);
        
        // Get movements for the account within date range
        List<Movimiento> movimientos = movimientoRepositoryPort
                .findByCuentaIdAndFechaBetween(cuenta.getId(), startDate, endDate);
        
        // Filter movements by date range (additional safety check)
        movimientos = estadoCuentaDomainService.filtrarMovimientosPorRangoFechas(movimientos, startDate, endDate);
        
        // Check if there's activity in the range
        if (!estadoCuentaDomainService.tieneActividadEnRango(movimientos, startDate, endDate)) {
            throw new IllegalArgumentException("No se encontraron movimientos en el rango de fechas especificado");
        }
        
        // Generate account summary
        EstadoCuentaDomainService.ResumenCuenta resumen = 
                estadoCuentaDomainService.calcularResumenCuenta(cuenta, movimientos);
        
        // For simplicity, return the movements as T
        // In a real implementation, this would return a proper EstadoCuentaDTO
        return (List<T>) movimientos;
    }
    
    /**
     * Helper method to get account summary
     */
    public EstadoCuentaDomainService.ResumenCuenta getResumenCuenta(Long clienteId, Long cuentaId, 
                                                                   LocalDateTime startDate, LocalDateTime endDate) {
        // Validate date range
        estadoCuentaDomainService.validarRangoFechas(startDate, endDate);
        
        // Get client and account
        Cliente cliente = clienteRepositoryPort.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado: " + clienteId));
        
        Cuenta cuenta = cuentaRepositoryPort.findById(cuentaId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + cuentaId));
        
        // Validate
        estadoCuentaDomainService.validarClienteParaReporte(cliente);
        estadoCuentaDomainService.validarCuentaParaReporte(cuenta, clienteId);
        
        // Get movements
        List<Movimiento> movimientos = movimientoRepositoryPort
                .findByCuentaIdAndFechaBetween(cuentaId, startDate, endDate);
        
        movimientos = estadoCuentaDomainService.filtrarMovimientosPorRangoFechas(movimientos, startDate, endDate);
        
        return estadoCuentaDomainService.calcularResumenCuenta(cuenta, movimientos);
    }
}
