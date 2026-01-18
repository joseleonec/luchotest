package co.com.sofka.luchotest.dto;

import java.math.BigDecimal;

import co.com.sofka.luchotest.dto.enums.TipoMovimientoEnum;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record MovimientoCreateDTO(
    
    @NotNull
    @Pattern(regexp = "DEPOSITO|RETIRO", message = "Tipo de movimiento inválido. Debe ser DEPOSITO o RETIRO")
    TipoMovimientoEnum tipoMovimiento,

    @NotNull
    @Positive
    BigDecimal valor,

    @NotNull
    Long cuentaId
) {

    @AssertTrue(message = "El valor no puede ser cero")
    public boolean isValorNotZero() {
        return valor != null && valor.compareTo(BigDecimal.ZERO) != 0;
    }

}
