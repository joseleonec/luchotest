package co.com.sofka.luchotest.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import co.com.sofka.luchotest.persistence.entity.MovimientoEntity;
import co.com.sofka.luchotest.persistence.repositroy.MovimientoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;

    public MovimientoEntity crearMovimiento(MovimientoEntity movimientoEntity) {
        return movimientoRepository.save(movimientoEntity);
    }

    public MovimientoEntity updateMovimiento(MovimientoEntity movimientoEntity) {
        if (!movimientoRepository.existsById(movimientoEntity.getId())) {
            throw new NoSuchElementException("Movimiento no encontrado con id: " + movimientoEntity.getId());
        }
        return movimientoRepository.save(movimientoEntity);
    }

    public MovimientoEntity getMovimientoById(Long id) {
        return movimientoRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Movimiento no encontrado con id: " + id)
        );
    }

    public void deleteMovimiento(Long id) {
        if (!movimientoRepository.existsById(id)) {
            throw new NoSuchElementException("Movimiento no encontrado con id: " + id);
        }
        movimientoRepository.deleteById(id);
    }
}
