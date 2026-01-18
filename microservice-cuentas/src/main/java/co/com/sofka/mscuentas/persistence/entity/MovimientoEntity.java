package co.com.sofka.mscuentas.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Entity
@Table(name = "movimiento")
public class MovimientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha;

    private String tipoMovimiento;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(name = "saldo_inicial", nullable = false)
    private BigDecimal saldoInicial;

    @PositiveOrZero
    @Column(nullable = false)
    private BigDecimal saldo;

    @ManyToOne
    @JoinColumn(name = "cuenta_id", nullable = false)
    private CuentaEntity cuenta;

}
