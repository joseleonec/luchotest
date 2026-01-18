package co.com.sofka.mscuentas.service.interfaces;

import java.time.LocalDate;
import java.util.List;

import co.com.sofka.mscuentas.dto.EstadoCuentaDTO;

public interface IEstadoCuentaService {

    List<EstadoCuentaDTO> generarEstadoCuenta(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin);
}
