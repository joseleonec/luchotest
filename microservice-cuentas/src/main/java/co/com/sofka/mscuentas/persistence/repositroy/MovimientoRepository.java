package co.com.sofka.mscuentas.persistence.repositroy;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.sofka.mscuentas.persistence.entity.MovimientoEntity;

public interface MovimientoRepository extends JpaRepository<MovimientoEntity, Long> {

    List<MovimientoEntity> findByCuentaIdAndFechaBetween(Long cuentaId, LocalDateTime fechaInicio, LocalDateTime fechaFin);

}
