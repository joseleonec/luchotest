package co.com.sofka.mscuentas.persistence.repositroy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.sofka.mscuentas.persistence.entity.CuentaEntity;

public interface CuentaRepository extends JpaRepository<CuentaEntity, Long> {

    List<CuentaEntity> findByClienteId(Long clienteId);

}
