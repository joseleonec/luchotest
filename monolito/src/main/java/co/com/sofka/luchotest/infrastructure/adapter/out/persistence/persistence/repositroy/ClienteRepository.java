package co.com.sofka.luchotest.infrastructure.adapter.out.persistence.persistence.repositroy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.sofka.luchotest.infrastructure.adapter.out.persistence.persistence.entity.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByClienteId(String clienteId);

    boolean existsByClienteId(String clienteId);

}
