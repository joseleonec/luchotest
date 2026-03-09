package co.com.sofka.luchotest.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Domain model representing a Bank Account.
 * This is a pure POJO without any framework dependencies.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {
    
    private Long id;
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldoInicial;
    private BigDecimal saldoDisponible;
    private String estado;
    private Long clienteId;
    
    /**
     * Business logic: Check if account has sufficient balance
     */
    public boolean tieneSaldoSuficiente(BigDecimal monto) {
        return saldoDisponible.compareTo(monto) >= 0;
    }
    
    /**
     * Business logic: Debit amount from account
     */
    public void debitar(BigDecimal monto) {
        this.saldoDisponible = this.saldoDisponible.subtract(monto);
    }
    
    /**
     * Business logic: Credit amount to account
     */
    public void acreditar(BigDecimal monto) {
        this.saldoDisponible = this.saldoDisponible.add(monto);
    }
}
