package co.com.banco.cuentas.usecase.movimiento;

import co.com.banco.cuentas.model.common.MovimientoException;
import co.com.banco.cuentas.model.cuenta.Cuenta;
import co.com.banco.cuentas.model.movimiento.Movimiento;
import co.com.banco.cuentas.model.movimiento.gateways.MovimientoRepository;
import co.com.banco.cuentas.usecase.cuenta.CuentaUseCase;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static co.com.banco.cuentas.model.common.CommonExceptionMessages.MOVIMIENTO_NO_DISPONIBLE;

@RequiredArgsConstructor
public class MovimientoUseCaseImpl implements MovimientoUseCase{

    private final CuentaUseCase cuentaUseCase;
    private final OperacionesUseCase operacionesUseCase;
    private final MovimientoRepository movimientoRepository;

    @Override
    public Movimiento crearMovimiento(Movimiento movimiento) {
        Cuenta cuenta = cuentaUseCase.findByNumero(movimiento.getCuenta().getNumero());
        movimiento.getCuenta().setId(cuenta.getId());
        movimiento.setSaldo(cuenta.getSaldo());
        Double saldoFinal = operacionesUseCase.operarSaldo(cuenta, movimiento);
        movimiento.setFecha(new Date());
        Movimiento nuevoMovimiento = movimientoRepository.save(movimiento);
        cuenta.setSaldo(saldoFinal);
        cuentaUseCase.actualizarCuenta(cuenta);
        
        return nuevoMovimiento;
    }

    @Override
    public Movimiento findById(UUID movimientoId) {
        Movimiento movimiento = movimientoRepository.findById(movimientoId);
        if(movimiento == null){
            throw new MovimientoException(MOVIMIENTO_NO_DISPONIBLE);
        }

        return movimiento;
    }

    @Override
    public List<Movimiento> findByCuenta(Cuenta cuenta) {
        return movimientoRepository.findByCuenta(cuenta);
    }
}
