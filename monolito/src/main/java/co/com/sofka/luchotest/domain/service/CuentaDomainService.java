package co.com.sofka.luchotest.domain.service;

import co.com.sofka.luchotest.domain.model.Cuenta;
import co.com.sofka.luchotest.domain.exception.SaldoInsuficienteException;
import co.com.sofka.luchotest.domain.exception.ResourceAlreadyExistsException;

import java.math.BigDecimal;

/**
 * Domain service for Cuenta business logic.
 * Contains business rules that don't depend on external infrastructure.
 */
public class CuentaDomainService {
    
    private static final BigDecimal SALDO_MINIMO = BigDecimal.ZERO;
    private static final BigDecimal SALDO_INICIAL_MAXIMO = new BigDecimal("1000000");
    
    /**
     * Validates account data according to business rules
     */
    public void validarCuenta(Cuenta cuenta) {
        if (cuenta.getNumeroCuenta() == null || cuenta.getNumeroCuenta().trim().isEmpty()) {
            throw new IllegalArgumentException("El número de cuenta es obligatorio");
        }
        
        if (cuenta.getTipoCuenta() == null || cuenta.getTipoCuenta().trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de cuenta es obligatorio");
        }
        
        if (cuenta.getClienteId() == null) {
            throw new IllegalArgumentException("El cliente ID es obligatorio");
        }
        
        if (cuenta.getSaldoInicial() == null || cuenta.getSaldoInicial().compareTo(SALDO_MINIMO) < 0) {
            throw new IllegalArgumentException("El saldo inicial debe ser mayor o igual a cero");
        }
        
        if (cuenta.getSaldoInicial().compareTo(SALDO_INICIAL_MAXIMO) > 0) {
            throw new IllegalArgumentException("El saldo inicial excede el máximo permitido");
        }
        
        if (!isValidTipoCuenta(cuenta.getTipoCuenta())) {
            throw new IllegalArgumentException("Tipo de cuenta no válido. Debe ser AHORROS o CORRIENTE");
        }
    }
    
    /**
     * Business rule: Check if account can be created
     */
    public void puedeCrearCuenta(Cuenta cuenta, boolean existePorNumero, boolean clienteExiste) {
        validarCuenta(cuenta);
        
        if (!clienteExiste) {
            throw new IllegalArgumentException("El cliente asociado no existe");
        }
        
        if (existePorNumero) {
            throw new ResourceAlreadyExistsException("Ya existe una cuenta con el número: " + cuenta.getNumeroCuenta());
        }
        
        // Set initial available balance
        cuenta.setSaldoDisponible(cuenta.getSaldoInicial());
        cuenta.setEstado("ACTIVA");
    }
    
    /**
     * Business rule: Check if account can be updated
     */
    public void puedeActualizarCuenta(Cuenta cuenta, boolean existe) {
        validarCuenta(cuenta);
        
        if (!existe) {
            throw new IllegalArgumentException("No se puede actualizar una cuenta que no existe");
        }
    }
    
    /**
     * Business rule: Check if account can be deleted
     */
    public void puedeEliminarCuenta(boolean existe, boolean tieneMovimientos) {
        if (!existe) {
            throw new IllegalArgumentException("No se puede eliminar una cuenta que no existe");
        }
        
        if (tieneMovimientos) {
            throw new IllegalArgumentException("No se puede eliminar una cuenta con movimientos");
        }
    }
    
    /**
     * Business rule: Process withdrawal from account
     */
    public void procesarRetiro(Cuenta cuenta, BigDecimal monto) {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del retiro debe ser mayor a cero");
        }
        
        if (!cuenta.tieneSaldoSuficiente(monto)) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar el retiro");
        }
        
        if (!"ACTIVA".equals(cuenta.getEstado())) {
            throw new IllegalArgumentException("Solo se pueden realizar retiros en cuentas activas");
        }
        
        cuenta.debitar(monto);
    }
    
    /**
     * Business rule: Process deposit to account
     */
    public void procesarDeposito(Cuenta cuenta, BigDecimal monto) {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del depósito debe ser mayor a cero");
        }
        
        if (!"ACTIVA".equals(cuenta.getEstado())) {
            throw new IllegalArgumentException("Solo se pueden realizar depósitos en cuentas activas");
        }
        
        cuenta.acreditar(monto);
    }
    
    /**
     * Business rule: Check if account type is valid
     */
    private boolean isValidTipoCuenta(String tipoCuenta) {
        return "AHORROS".equals(tipoCuenta) || "CORRIENTE".equals(tipoCuenta);
    }
    
    /**
     * Business rule: Generate account number based on business logic
     */
    public String generarNumeroCuenta(String tipoCuenta, Long clienteId) {
        String prefix = "AH".equals(tipoCuenta) ? "001" : "002";
        return prefix + String.format("%08d", clienteId) + 
               String.format("%03d", System.currentTimeMillis() % 1000);
    }
    
    /**
     * Business rule: Calculate account maintenance fee
     */
    public BigDecimal calcularMantenimientoMensual(Cuenta cuenta) {
        if ("AHORROS".equals(cuenta.getTipoCuenta())) {
            // Savings accounts have no maintenance fee
            return BigDecimal.ZERO;
        } else {
            // Current accounts have maintenance fee if balance is below threshold
            BigDecimal threshold = new BigDecimal("100000");
            if (cuenta.getSaldoDisponible().compareTo(threshold) < 0) {
                return new BigDecimal("5000");
            }
            return BigDecimal.ZERO;
        }
    }
}
