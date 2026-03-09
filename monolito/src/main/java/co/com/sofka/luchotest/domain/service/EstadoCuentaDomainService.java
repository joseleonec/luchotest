package co.com.sofka.luchotest.domain.service;

import co.com.sofka.luchotest.domain.model.Cliente;
import co.com.sofka.luchotest.domain.model.Cuenta;
import co.com.sofka.luchotest.domain.model.Movimiento;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Domain service for EstadoCuenta (Account Statement) business logic.
 * Contains business rules that don't depend on external infrastructure.
 */
public class EstadoCuentaDomainService {
    
    /**
     * Business rule: Validate date range for account statement
     */
    public void validarRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Las fechas de inicio y fin son obligatorias");
        }
        
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }
        
        if (fechaFin.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser futura");
        }
        
        // Limit date range to 6 months for performance
        LocalDateTime limiteInferior = LocalDateTime.now().minusMonths(6);
        if (fechaInicio.isBefore(limiteInferior)) {
            throw new IllegalArgumentException("El rango de fechas no puede superar los 6 meses");
        }
    }
    
    /**
     * Business rule: Validate client for account statement
     */
    public void validarClienteParaReporte(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente es obligatorio para generar el estado de cuenta");
        }
        
        if (cliente.getId() == null) {
            throw new IllegalArgumentException("El ID del cliente es obligatorio");
        }
    }
    
    /**
     * Business rule: Validate account for account statement
     */
    public void validarCuentaParaReporte(Cuenta cuenta, Long clienteId) {
        if (cuenta == null) {
            throw new IllegalArgumentException("La cuenta es obligatoria para generar el estado de cuenta");
        }
        
        if (!cuenta.getClienteId().equals(clienteId)) {
            throw new IllegalArgumentException("La cuenta no pertenece al cliente especificado");
        }
        
        if (cuenta.getId() == null) {
            throw new IllegalArgumentException("El ID de la cuenta es obligatorio");
        }
    }
    
    /**
     * Business rule: Calculate account summary
     */
    public ResumenCuenta calcularResumenCuenta(Cuenta cuenta, List<Movimiento> movimientos) {
        BigDecimal totalDepositos = BigDecimal.ZERO;
        BigDecimal totalRetiros = BigDecimal.ZERO;
        int cantidadDepositos = 0;
        int cantidadRetiros = 0;
        
        for (Movimiento movimiento : movimientos) {
            if ("DEPOSITO".equals(movimiento.getTipoMovimiento())) {
                totalDepositos = totalDepositos.add(movimiento.getValor().abs());
                cantidadDepositos++;
            } else if ("RETIRO".equals(movimiento.getTipoMovimiento())) {
                totalRetiros = totalRetiros.add(movimiento.getValor().abs());
                cantidadRetiros++;
            }
        }
        
        return ResumenCuenta.builder()
                .saldoInicial(cuenta.getSaldoInicial())
                .saldoActual(cuenta.getSaldoDisponible())
                .totalDepositos(totalDepositos)
                .totalRetiros(totalRetiros)
                .cantidadDepositos(cantidadDepositos)
                .cantidadRetiros(cantidadRetiros)
                .build();
    }
    
    /**
     * Business rule: Filter movements by date range
     */
    public List<Movimiento> filtrarMovimientosPorRangoFechas(List<Movimiento> movimientos, 
                                                             LocalDateTime fechaInicio, 
                                                             LocalDateTime fechaFin) {
        return movimientos.stream()
                .filter(m -> !m.getFecha().isBefore(fechaInicio) && !m.getFecha().isAfter(fechaFin))
                .sorted((m1, m2) -> m2.getFecha().compareTo(m1.getFecha())) // Most recent first
                .toList();
    }
    
    /**
     * Business rule: Check if account has activity in date range
     */
    public boolean tieneActividadEnRango(List<Movimiento> movimientos, 
                                       LocalDateTime fechaInicio, 
                                       LocalDateTime fechaFin) {
        return movimientos.stream()
                .anyMatch(m -> !m.getFecha().isBefore(fechaInicio) && !m.getFecha().isAfter(fechaFin));
    }
    
    /**
     * Business rule: Generate account statement title
     */
    public String generarTituloReporte(Cliente cliente, Cuenta cuenta, 
                                      LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return String.format("Estado de Cuenta - %s - Cuenta %s (%s) - Del %s al %s",
                cliente.getNombre(),
                cuenta.getNumeroCuenta(),
                cuenta.getTipoCuenta(),
                fechaInicio.toLocalDate(),
                fechaFin.toLocalDate());
    }
    
    /**
     * Business rule: Calculate average daily balance
     */
    public BigDecimal calcularSaldoPromedioDiario(List<Movimiento> movimientos, 
                                                  BigDecimal saldoInicial,
                                                  LocalDateTime fechaInicio,
                                                  LocalDateTime fechaFin) {
        if (movimientos.isEmpty()) {
            return saldoInicial;
        }
        
        // Simplified calculation: average of all movement balances
        BigDecimal totalSaldos = BigDecimal.ZERO;
        int count = 0;
        
        for (Movimiento movimiento : movimientos) {
            if (movimiento.getSaldo() != null) {
                totalSaldos = totalSaldos.add(movimiento.getSaldo());
                count++;
            }
        }
        
        return count > 0 ? totalSaldos.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP) : saldoInicial;
    }
    
    /**
     * Inner class for account summary
     */
    @lombok.Data
    @lombok.Builder
    @lombok.AllArgsConstructor
    @lombok.NoArgsConstructor
    public static class ResumenCuenta {
        private BigDecimal saldoInicial;
        private BigDecimal saldoActual;
        private BigDecimal totalDepositos;
        private BigDecimal totalRetiros;
        private int cantidadDepositos;
        private int cantidadRetiros;
        
        /**
         * Business rule: Calculate net change
         */
        public BigDecimal getCambioNeto() {
            return totalDepositos.subtract(totalRetiros);
        }
        
        /**
         * Business rule: Calculate total transactions
         */
        public int getTotalTransacciones() {
            return cantidadDepositos + cantidadRetiros;
        }
    }
}
