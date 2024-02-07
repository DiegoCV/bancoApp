package co.com.banco.cuentas.api.cuenta.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class CuentaSimpleResponse {
    private UUID id;
}
