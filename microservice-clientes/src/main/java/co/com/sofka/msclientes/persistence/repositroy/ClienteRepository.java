package co.com.sofka.msclientes.persistence.repositroy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.sofka.msclientes.persistence.entity.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByClienteId(String clienteId);

    boolean existsByClienteId(String clienteId);

}
