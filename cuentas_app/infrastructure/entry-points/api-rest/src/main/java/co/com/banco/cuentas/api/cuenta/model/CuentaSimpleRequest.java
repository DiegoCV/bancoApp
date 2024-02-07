package co.com.banco.cuentas.api.cuenta.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CuentaSimpleRequest {
    private Long numero;
}
