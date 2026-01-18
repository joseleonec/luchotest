package co.com.sofka.mscuentas.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import co.com.sofka.mscuentas.dto.enums.TipoMovimientoEnum;


public record MovimientoDTO (
    
    Long id,

    LocalDateTime fecha,

    TipoMovimientoEnum tipoMovimiento,

    BigDecimal valor,

    BigDecimal saldoInicial,

    BigDecimal saldo,

    Long cuentaId
) {

}
