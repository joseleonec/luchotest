package co.com.sofka.luchotest.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record MovimientoDTO (
    
    Long id,

    LocalDateTime fecha,

    String tipoMovimiento,

    BigDecimal valor,

    BigDecimal saldoInicial,

    BigDecimal saldo,

    Long cuentaId
) {

}
