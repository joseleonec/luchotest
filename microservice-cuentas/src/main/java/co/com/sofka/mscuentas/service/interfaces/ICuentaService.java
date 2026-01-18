package co.com.sofka.mscuentas.service.interfaces;

import java.math.BigDecimal;
import java.util.List;

import co.com.sofka.mscuentas.persistence.entity.CuentaEntity;

public interface ICuentaService {

    CuentaEntity crearCuenta(CuentaEntity cuentaEntity);

    CuentaEntity updateCuenta(CuentaEntity cuentaEntity);

    CuentaEntity getCuentaById(Long id);

    void deleteCuenta(Long id);

    CuentaEntity updateCuentaSaldo(Long cuentaId, BigDecimal nuevoSaldoDisponible);

    List<CuentaEntity> getCuentasByClienteId(Long clienteId);
}
