package co.com.sofka.luchotest.domain.service;

import co.com.sofka.luchotest.domain.model.Movimiento;
import co.com.sofka.luchotest.domain.model.Cuenta;
import co.com.sofka.luchotest.domain.exception.SaldoInsuficienteException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * Domain service for Movimiento business logic.
 * Contains business rules that don't depend on external infrastructure.
 */
public class MovimientoDomainService {
    
    private static final BigDecimal LIMITE_DIARIO_RETIRO = new BigDecimal("1000000");
    private static final BigDecimal MONTO_MINIMO_RETIRO = new BigDecimal("1");
    
    /**
     * Validates movement data according to business rules
     */
    public void validarMovimiento(Movimiento movimiento) {
        if (movimiento.getFecha() == null) {
            throw new IllegalArgumentException("La fecha del movimiento es obligatoria");
        }
        
        if (movimiento.getTipoMovimiento() == null || movimiento.getTipoMovimiento().trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de movimiento es obligatorio");
        }
        
        if (movimiento.getValor() == null) {
            throw new IllegalArgumentException("El valor del movimiento es obligatorio");
        }
        
        if (movimiento.getValor().compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("El valor del movimiento no puede ser cero");
        }
        
        if (movimiento.getCuentaId() == null) {
            throw new IllegalArgumentException("El ID de la cuenta es obligatorio");
        }
        
        if (!isValidTipoMovimiento(movimiento.getTipoMovimiento())) {
            throw new IllegalArgumentException("Tipo de movimiento no válido. Debe ser RETIRO o DEPOSITO");
        }
    }
    
    /**
     * Business rule: Process withdrawal movement
     */
    public void procesarRetiro(Movimiento movimiento, Cuenta cuenta, BigDecimal totalRetirosDiario) {
        validarMovimiento(movimiento);
        
        if (!"RETIRO".equals(movimiento.getTipoMovimiento())) {
            throw new IllegalArgumentException("El movimiento no es de tipo retiro");
        }
        
        BigDecimal valorAbsoluto = movimiento.getValor().abs();
        
        if (valorAbsoluto.compareTo(MONTO_MINIMO_RETIRO) < 0) {
            throw new IllegalArgumentException("El monto mínimo de retiro es " + MONTO_MINIMO_RETIRO);
        }
        
        // Check daily withdrawal limit
        BigDecimal nuevoTotalDiario = totalRetirosDiario.add(valorAbsoluto);
        if (nuevoTotalDiario.compareTo(LIMITE_DIARIO_RETIRO) > 0) {
            throw new SaldoInsuficienteException("Cupo diario excedido. Límite diario: " + LIMITE_DIARIO_RETIRO);
        }
        
        // Check sufficient balance
        if (!cuenta.tieneSaldoSuficiente(valorAbsoluto)) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar el retiro");
        }
        
        // Process the withdrawal
        cuenta.debitar(valorAbsoluto);
        movimiento.setSaldo(cuenta.getSaldoDisponible());
    }
    
    /**
     * Business rule: Process deposit movement
     */
    public void procesarDeposito(Movimiento movimiento, Cuenta cuenta) {
        validarMovimiento(movimiento);
        
        if (!"DEPOSITO".equals(movimiento.getTipoMovimiento())) {
            throw new IllegalArgumentException("El movimiento no es de tipo depósito");
        }
        
        BigDecimal valorAbsoluto = movimiento.getValor().abs();
        
        // Process the deposit
        cuenta.acreditar(valorAbsoluto);
        movimiento.setSaldo(cuenta.getSaldoDisponible());
    }
    
    /**
     * Business rule: Check if movement can be created
     */
    public void puedeCrearMovimiento(Movimiento movimiento, boolean cuentaExiste, boolean cuentaActiva) {
        validarMovimiento(movimiento);
        
        if (!cuentaExiste) {
            throw new IllegalArgumentException("La cuenta asociada no existe");
        }
        
        if (!cuentaActiva) {
            throw new IllegalArgumentException("No se pueden realizar movimientos en cuentas inactivas");
        }
        
        // Set movement date if not provided
        if (movimiento.getFecha() == null) {
            movimiento.setFecha(LocalDateTime.now());
        }
        
        // Set initial balance (will be updated during processing)
        movimiento.setSaldo(BigDecimal.ZERO);
    }
    
    /**
     * Business rule: Calculate daily withdrawal total for an account
     */
    public BigDecimal calcularTotalRetirosDiario(BigDecimal totalRetirosActual, BigDecimal nuevoRetiro) {
        return totalRetirosActual.add(nuevoRetiro.abs());
    }
    
    /**
     * Business rule: Check if daily withdrawal limit would be exceeded
     */
    public boolean excedeLimiteDiarioRetiro(BigDecimal totalRetirosActual, BigDecimal nuevoRetiro) {
        BigDecimal nuevoTotal = totalRetirosActual.add(nuevoRetiro.abs());
        return nuevoTotal.compareTo(LIMITE_DIARIO_RETIRO) > 0;
    }
    
    /**
     * Business rule: Get remaining daily withdrawal limit
     */
    public BigDecimal getLimiteDiarioRestante(BigDecimal totalRetirosActual) {
        BigDecimal restante = LIMITE_DIARIO_RETIRO.subtract(totalRetirosActual);
        return restante.compareTo(BigDecimal.ZERO) > 0 ? restante : BigDecimal.ZERO;
    }
    
    /**
     * Business rule: Validate movement date for reporting
     */
    public void validarFechaParaReporte(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha es obligatoria para el reporte");
        }
        
        if (fecha.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser futura");
        }
    }
    
    /**
     * Business rule: Check if movement type is valid
     */
    private boolean isValidTipoMovimiento(String tipoMovimiento) {
        return "RETIRO".equals(tipoMovimiento) || "DEPOSITO".equals(tipoMovimiento);
    }
    
    /**
     * Business rule: Generate movement description
     */
    public String generarDescripcionMovimiento(String tipoMovimiento, BigDecimal valor) {
        String valorStr = "$" + valor.abs().toString();
        
        switch (tipoMovimiento) {
            case "RETIRO":
                return "Retiro de " + valorStr;
            case "DEPOSITO":
                return "Depósito de " + valorStr;
            default:
                return "Movimiento de " + valorStr;
        }
    }
}
