package co.com.banco.cuentas.api.cuenta.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class CuentaResponse {
    private UUID id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean estado;
}
