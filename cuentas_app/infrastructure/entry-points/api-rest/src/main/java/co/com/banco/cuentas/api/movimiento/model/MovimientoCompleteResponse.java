package co.com.banco.cuentas.api.movimiento.model;

import co.com.banco.cuentas.api.cuenta.model.CuentaSimpleResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MovimientoCompleteResponse {
    private String tipo;
    private Double valor;
    private CuentaSimpleResponse cuenta;
}
