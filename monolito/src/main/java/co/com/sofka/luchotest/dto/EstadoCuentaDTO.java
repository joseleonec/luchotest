package co.com.sofka.luchotest.dto;

import java.util.List;

import lombok.Data;

@Data
public class EstadoCuentaDTO {

    private CuentaReporteDTO cuenta;
    private List<MovimientoDTO> movimientos;
}
