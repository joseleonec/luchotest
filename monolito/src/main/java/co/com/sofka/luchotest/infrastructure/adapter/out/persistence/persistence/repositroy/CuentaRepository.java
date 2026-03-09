package co.com.sofka.luchotest.infrastructure.adapter.out.persistence.persistence.repositroy;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.sofka.luchotest.infrastructure.adapter.out.persistence.persistence.entity.CuentaEntity;

public interface CuentaRepository extends JpaRepository<CuentaEntity, Long> {

    List<CuentaEntity> findByClienteId(Long clienteId);
    
    Optional<CuentaEntity> findByNumeroCuenta(String numeroCuenta);
    
    boolean existsByNumeroCuenta(String numeroCuenta);

}
