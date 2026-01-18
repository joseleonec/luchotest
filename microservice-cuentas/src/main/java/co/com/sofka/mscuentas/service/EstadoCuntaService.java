package co.com.sofka.mscuentas.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.com.sofka.mscuentas.dto.EstadoCuentaDTO;
import co.com.sofka.mscuentas.mapper.CuentaMapper;
import co.com.sofka.mscuentas.mapper.MovimientoMapper;
import co.com.sofka.mscuentas.persistence.entity.CuentaEntity;
import co.com.sofka.mscuentas.service.interfaces.ICuentaService;
import co.com.sofka.mscuentas.service.interfaces.IEstadoCuentaService;
import co.com.sofka.mscuentas.service.interfaces.IMovimientoService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EstadoCuntaService implements IEstadoCuentaService {

    private final ICuentaService cuentaService;
    private final IMovimientoService movimientoService;
    private final CuentaMapper cuentaMapper;
    private final MovimientoMapper movimientoMapper;

    @Override
    public List<EstadoCuentaDTO> generarEstadoCuenta(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin) {

        List<CuentaEntity> cuentasCliente = cuentaService.getCuentasByClienteId(clienteId);
        var estadosCuentaDTO = new ArrayList<EstadoCuentaDTO>();

        for (CuentaEntity cuenta : cuentasCliente) {

            var fechaInicioDateTime = fechaInicio.atStartOfDay();
            var fechaFinDateTime = fechaFin.atTime(23, 59, 59);

            var estadoCuentaDTO = new EstadoCuentaDTO();
            var cuentaDTO = cuentaMapper.toReporteDTO(cuenta);
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
