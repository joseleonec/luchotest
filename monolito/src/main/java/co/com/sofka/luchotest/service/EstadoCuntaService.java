package co.com.sofka.luchotest.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.com.sofka.luchotest.dto.EstadoCuentaDTO;
import co.com.sofka.luchotest.mapper.CuentaMapper;
import co.com.sofka.luchotest.mapper.MovimientoMapper;
import co.com.sofka.luchotest.persistence.entity.CuentaEntity;
import co.com.sofka.luchotest.service.interfaces.IClienteService;
import co.com.sofka.luchotest.service.interfaces.ICuentaService;
import co.com.sofka.luchotest.service.interfaces.IEstadoCuentaService;
import co.com.sofka.luchotest.service.interfaces.IMovimientoService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EstadoCuntaService implements IEstadoCuentaService {

    private final ICuentaService cuentaService;
    private final IMovimientoService movimientoService;
    private final IClienteService clienteService;
    private final CuentaMapper cuentaMapper;
    private final MovimientoMapper movimientoMapper;

    @Override
    public List<EstadoCuentaDTO> generarEstadoCuenta(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin) {

        var nombreCliente = clienteService.obtenerNombreClientePorId(clienteId);

        List<CuentaEntity> cuentasCliente = cuentaService.getCuentasByClienteId(clienteId);
        var estadosCuentaDTO = new ArrayList<EstadoCuentaDTO>();

        for (CuentaEntity cuenta : cuentasCliente) {

            var fechaInicioDateTime = fechaInicio.atStartOfDay();
            var fechaFinDateTime = fechaFin.atTime(23, 59, 59);

            var estadoCuentaDTO = new EstadoCuentaDTO();
            var cuentaDTO = cuentaMapper.toReporteDTO(cuenta, nombreCliente);
            estadoCuentaDTO.setCuenta(cuentaDTO);

            var movimientos = movimientoService.getMovimientosByCuentaId(
                    cuenta.getId(), fechaInicioDateTime, fechaFinDateTime);
            if (!movimientos.isEmpty()) {

                var movimientosDTO = movimientos.stream()
                        .map(movimientoMapper::toDTO)
                        .toList();

                estadoCuentaDTO.setMovimientos(movimientosDTO);
            }
            estadosCuentaDTO.add(estadoCuentaDTO);

        }

        return estadosCuentaDTO;
    }
}
