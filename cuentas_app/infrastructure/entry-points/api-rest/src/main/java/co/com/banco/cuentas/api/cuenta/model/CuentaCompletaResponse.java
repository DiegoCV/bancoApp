package co.com.banco.cuentas.api.cuenta.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class CuentaCompletaResponse {
    private Long numero;
    private String tipo;
    private Boolean estado;
    private Double saldo;
    private UUID clienteId;
}
