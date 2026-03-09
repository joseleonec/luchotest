package co.com.sofka.luchotest.infrastructure.adapter.out.persistence.adapter;

import co.com.sofka.luchotest.domain.model.Cliente;
import co.com.sofka.luchotest.domain.port.out.ClienteRepositoryPort;
import co.com.sofka.luchotest.infrastructure.adapter.out.persistence.mapper.ClientePersistenceMapper;
import co.com.sofka.luchotest.infrastructure.adapter.out.persistence.persistence.repositroy.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adapter that implements ClienteRepositoryPort using JPA.
 * This is the outbound adapter for persistence.
 */
@Component
@RequiredArgsConstructor
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {

    private final ClienteRepository clienteRepository;
    private final ClientePersistenceMapper mapper;

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Cliente> findByClienteId(String clienteId) {
        return clienteRepository.findByClienteId(clienteId)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsById(Long id) {
        return clienteRepository.existsById(id);
    }

    @Override
    public boolean existsByClienteId(String clienteId) {
        return clienteRepository.existsByClienteId(clienteId);
    }

    @Override
    public Cliente save(Cliente cliente) {
        var entity = mapper.toEntity(cliente);
        var savedEntity = clienteRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
}
