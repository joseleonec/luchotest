package co.com.sofka.luchotest.infrastructure.adapter.out.persistence.adapter;

import co.com.sofka.luchotest.domain.model.Movimiento;
import co.com.sofka.luchotest.domain.port.out.MovimientoRepositoryPort;
import co.com.sofka.luchotest.infrastructure.adapter.out.persistence.mapper.MovimientoPersistenceMapper;
import co.com.sofka.luchotest.infrastructure.adapter.out.persistence.persistence.repositroy.MovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adapter that implements MovimientoRepositoryPort using JPA.
 * This is the outbound adapter for persistence.
 */
@Component
@RequiredArgsConstructor
public class MovimientoRepositoryAdapter implements MovimientoRepositoryPort {

    private final MovimientoRepository movimientoRepository;
    private final MovimientoPersistenceMapper mapper;

    @Override
    public List<Movimiento> findAll() {
        return movimientoRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Movimiento> findById(Long id) {
        return movimientoRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Movimiento> findByCuentaId(Long cuentaId) {
        return movimientoRepository.findByCuentaId(cuentaId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Movimiento> findByCuentaIdAndFechaBetween(Long cuentaId, LocalDateTime startDate, LocalDateTime endDate) {
        return movimientoRepository.findByCuentaIdAndFechaBetween(cuentaId, startDate, endDate).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Movimiento> findByClienteIdAndFechaBetween(Long clienteId, LocalDateTime startDate, LocalDateTime endDate) {
        return movimientoRepository.findByClienteIdAndFechaBetween(clienteId, startDate, endDate).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Movimiento save(Movimiento movimiento) {
        var entity = mapper.toEntity(movimiento);
        var savedEntity = movimientoRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        movimientoRepository.deleteById(id);
    }
}
