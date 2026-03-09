package co.com.sofka.luchotest.infrastructure.adapter.out.persistence.adapter;

import co.com.sofka.luchotest.domain.model.Cuenta;
import co.com.sofka.luchotest.domain.port.out.CuentaRepositoryPort;
import co.com.sofka.luchotest.infrastructure.adapter.out.persistence.mapper.CuentaPersistenceMapper;
import co.com.sofka.luchotest.infrastructure.adapter.out.persistence.persistence.repositroy.CuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adapter that implements CuentaRepositoryPort using JPA.
 * This is the outbound adapter for persistence.
 */
@Component
@RequiredArgsConstructor
public class CuentaRepositoryAdapter implements CuentaRepositoryPort {

    private final CuentaRepository cuentaRepository;
    private final CuentaPersistenceMapper mapper;

    @Override
    public List<Cuenta> findAll() {
        return cuentaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cuenta> findById(Long id) {
        return cuentaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Cuenta> findByNumeroCuenta(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .map(mapper::toDomain);
    }

    @Override
    public List<Cuenta> findByClienteId(Long clienteId) {
        return cuentaRepository.findByClienteId(clienteId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Long id) {
        return cuentaRepository.existsById(id);
    }

    @Override
    public boolean existsByNumeroCuenta(String numeroCuenta) {
        return cuentaRepository.existsByNumeroCuenta(numeroCuenta);
    }

    @Override
    public Cuenta save(Cuenta cuenta) {
        var entity = mapper.toEntity(cuenta);
        var savedEntity = cuentaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        cuentaRepository.deleteById(id);
    }
}
