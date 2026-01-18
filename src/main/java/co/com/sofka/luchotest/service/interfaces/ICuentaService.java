package co.com.sofka.luchotest.service.interfaces;

import java.math.BigDecimal;
import java.util.List;

import co.com.sofka.luchotest.persistence.entity.CuentaEntity;

public interface ICuentaService {

    CuentaEntity crearCuenta(CuentaEntity cuentaEntity);

    CuentaEntity updateCuenta(CuentaEntity cuentaEntity);

    CuentaEntity getCuentaById(Long id);

    void deleteCuenta(Long id);

    CuentaEntity updateCuentaSaldo(Long cuentaId, BigDecimal nuevoSaldoDisponible);

    List<CuentaEntity> getCuentasByClienteId(Long clienteId);
}
