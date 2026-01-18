package co.com.sofka.luchotest.service.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import co.com.sofka.luchotest.persistence.entity.MovimientoEntity;

public interface IMovimientoService {

    MovimientoEntity crearMovimiento(MovimientoEntity movimientoEntity);

    MovimientoEntity updateMovimiento(MovimientoEntity movimientoEntity);

    MovimientoEntity getMovimientoById(Long id);

    void deleteMovimiento(Long id);

    List<MovimientoEntity> getMovimientosByCuentaId(Long cuentaId, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
