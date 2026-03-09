package co.com.sofka.luchotest.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Domain model representing a Transaction/Movement.
 * This is a pure POJO without any framework dependencies.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movimiento {
    
    private Long id;
    private LocalDateTime fecha;
    private String tipoMovimiento;
    private BigDecimal valor;
    private BigDecimal saldoInicial;
    private BigDecimal saldo;
    private Long cuentaId;
}
