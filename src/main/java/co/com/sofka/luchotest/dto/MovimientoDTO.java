package co.com.sofka.luchotest.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record MovimientoDTO (
    
    LocalDateTime fecha,

    String tipoMovimiento,

    BigDecimal valor,

    BigDecimal saldo,

    Long cuentaId
) {

}
