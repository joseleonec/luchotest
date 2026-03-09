package co.com.sofka.luchotest.infrastructure.adapter.in.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import co.com.sofka.luchotest.infrastructure.adapter.in.dto.enums.TipoMovimientoEnum;


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
