package co.com.sofka.luchotest.infrastructure.adapter.out.service.interfaces;

import java.time.LocalDate;
import java.util.List;

import co.com.sofka.luchotest.infrastructure.adapter.in.dto.EstadoCuentaDTO;

public interface IEstadoCuentaService {

    List<EstadoCuentaDTO> generarEstadoCuenta(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin);
}
