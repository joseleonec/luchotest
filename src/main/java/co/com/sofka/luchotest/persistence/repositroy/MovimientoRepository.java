package co.com.sofka.luchotest.persistence.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.sofka.luchotest.persistence.entity.MovimientoEntity;

public interface MovimientoRepository extends JpaRepository<MovimientoEntity, Long> {

}
