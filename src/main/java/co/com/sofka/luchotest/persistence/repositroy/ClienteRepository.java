package co.com.sofka.luchotest.persistence.repositroy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.sofka.luchotest.persistence.entity.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByClienteId(String clienteId);

}
