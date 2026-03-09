package co.com.sofka.luchotest.infrastructure.adapter.out.persistence.persistence.repositroy;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.sofka.luchotest.infrastructure.adapter.out.persistence.persistence.entity.MovimientoEntity;

public interface MovimientoRepository extends JpaRepository<MovimientoEntity, Long> {

    List<MovimientoEntity> findByCuentaIdAndFechaBetween(Long cuentaId, LocalDateTime fechaInicio, LocalDateTime fechaFin);
    
    List<MovimientoEntity> findByCuentaId(Long cuentaId);
    
    @Query("SELECT m FROM MovimientoEntity m WHERE m.cuenta.cliente.id = :clienteId AND m.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<MovimientoEntity> findByClienteIdAndFechaBetween(
        @Param("clienteId") Long clienteId, 
        @Param("fechaInicio") LocalDateTime fechaInicio, 
        @Param("fechaFin") LocalDateTime fechaFin
    );

}
