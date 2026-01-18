package co.com.sofka.luchotest.service.interfaces;

import java.time.LocalDate;
import java.util.List;

import co.com.sofka.luchotest.dto.EstadoCuentaDTO;

public interface IEstadoCuentaService {

    List<EstadoCuentaDTO> generarEstadoCuenta(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin);
}
