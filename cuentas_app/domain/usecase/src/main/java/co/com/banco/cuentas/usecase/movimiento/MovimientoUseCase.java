package co.com.banco.cuentas.usecase.movimiento;

import co.com.banco.cuentas.model.cuenta.Cuenta;
import co.com.banco.cuentas.model.movimiento.Movimiento;

import java.util.List;
import java.util.UUID;

public interface MovimientoUseCase {
    Movimiento crearMovimiento(Movimiento movimiento);
    Movimiento findById(UUID movimientoId);
    List<Movimiento> findByCuenta(Cuenta cuenta);
}
