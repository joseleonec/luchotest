package co.com.sofka.luchotest.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovimientoCreateDTO(
    
    @NotBlank
    String tipoMovimiento,

    @NotNull
    BigDecimal valor,

    @NotNull
    Long cuentaId
) {

    @AssertTrue(message = "El valor no puede ser cero")
    public boolean isValorNotZero() {
        return valor != null && valor.compareTo(BigDecimal.ZERO) != 0;
    }

}
