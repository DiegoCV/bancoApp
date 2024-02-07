package co.com.banco.cuentas.model.movimiento.gateways;

import co.com.banco.cuentas.model.cuenta.Cuenta;
import co.com.banco.cuentas.model.movimiento.Movimiento;

import java.util.List;
import java.util.UUID;

public interface MovimientoRepository {
    Movimiento save(Movimiento movimiento);
    Movimiento findById(UUID movimientoId);
    List<Movimiento> findByCuenta(Cuenta cuenta);
}
