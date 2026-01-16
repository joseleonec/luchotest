package co.com.sofka.luchotest.persistence.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.sofka.luchotest.persistence.entity.CuentaEntity;

public interface CuentaRepository extends JpaRepository<CuentaEntity, Long> {

}
