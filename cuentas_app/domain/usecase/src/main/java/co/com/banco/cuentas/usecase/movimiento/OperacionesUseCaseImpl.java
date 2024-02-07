package co.com.banco.cuentas.usecase.movimiento;

import co.com.banco.cuentas.model.common.MovimientoConSaldoNoDisponibleException;
import co.com.banco.cuentas.model.common.OperarSaldoException;
import co.com.banco.cuentas.model.cuenta.Cuenta;
import co.com.banco.cuentas.model.movimiento.Movimiento;

import static co.com.banco.cuentas.model.common.CommonExceptionMessages.TIPO_MOVIMIENTO_NO_VALIDO;
import static co.com.banco.cuentas.model.movimiento.common.MovimientoConstans.DEPOSITO;
import static co.com.banco.cuentas.model.movimiento.common.MovimientoConstans.RETIRO;
import static co.com.banco.cuentas.model.movimiento.common.MovimientoConstans.VALOR_MINIMO_CUENTAS;

public class OperacionesUseCaseImpl implements OperacionesUseCase{

    @Override
    public Double operarSaldo(Cuenta cuenta, Movimiento movimiento) {
        Double saldoFinal = 0.0d;
        if(movimiento.getTipo().equalsIgnoreCase(DEPOSITO)){
            saldoFinal = operarSaldoDeposito(cuenta.getSaldo(), movimiento.getValor());
        }else if(movimiento.getTipo().equalsIgnoreCase(RETIRO)){
            saldoFinal = operarSaldoRetiro(cuenta.getSaldo(), movimiento.getValor());
        }else{
            throw new OperarSaldoException(TIPO_MOVIMIENTO_NO_VALIDO);
        }

        return saldoFinal;
    }

    private Double operarSaldoDeposito(Double saldoInicial, Double valorMovimiento){
        return  saldoInicial + valorMovimiento;
    }

    private Double operarSaldoRetiro(Double saldoInicial, Double valorMovimiento){
        Double saldoFinal = saldoInicial - valorMovimiento;
        if(saldoFinal < VALOR_MINIMO_CUENTAS){
            throw new MovimientoConSaldoNoDisponibleException();
        }

        return saldoFinal;
    }
}
