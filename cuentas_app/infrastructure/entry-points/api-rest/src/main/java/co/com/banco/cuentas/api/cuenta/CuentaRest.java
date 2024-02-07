package co.com.banco.cuentas.api.cuenta;
import co.com.banco.cuentas.api.cuenta.model.CuentaCompletaResponse;
import co.com.banco.cuentas.api.cuenta.model.CuentaRequest;
import co.com.banco.cuentas.api.cuenta.model.CuentaResponse;
import co.com.banco.cuentas.model.cuenta.Cuenta;
import co.com.banco.cuentas.usecase.cuenta.CuentaUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/cuentas", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CuentaRest {

    private final CuentaUseCase cuentaUseCase;

    @PostMapping
    public ResponseEntity<CuentaResponse> crearCuenta(@RequestBody CuentaRequest cuentaRequest) {
        Cuenta cuenta = cuentaUseCase.crearCuenta(cuentaFromRequest(cuentaRequest));
        return new ResponseEntity<CuentaResponse>(CuentaResponse.builder().id(cuenta.getId())
                .estado(cuenta.getEstado()).build(), HttpStatus.OK);
    }

    @GetMapping(value = "{cuentaId}")
    public ResponseEntity<CuentaCompletaResponse> consultarCuenta(@PathVariable("cuentaId") UUID cuentaId) {
        Cuenta cuenta = cuentaUseCase.findById(cuentaId);
        return new ResponseEntity<CuentaCompletaResponse>(toCuentaCompletaResponse(cuenta), HttpStatus.OK);
    }

    @PutMapping(value = "{cuentaId}")
    public ResponseEntity<CuentaResponse> actualizarCuenta(@PathVariable("cuentaId") UUID cuentaId,
                                                           @RequestBody CuentaRequest cuentaRequest) {
        Cuenta cuenta = cuentaFromRequest(cuentaRequest);
        cuenta.setId(cuentaId);
        Cuenta cuentaActualizada = cuentaUseCase.actualizarCuenta(cuenta);
        return new ResponseEntity<>(CuentaResponse.builder().id(cuentaActualizada.getId())
                .estado(cuentaActualizada.getEstado()).build(), HttpStatus.OK);
    }

    @DeleteMapping(value = "{cuentaId}")
    public ResponseEntity eliminarCuenta(@PathVariable("cuentaId") UUID cuentaId) {
        cuentaUseCase.eliminarCuenta(Cuenta.builder().id(cuentaId).build());

        return new ResponseEntity(HttpStatus.OK);
    }

    private Cuenta cuentaFromRequest(CuentaRequest cuentaRequest){
        return Cuenta.builder().numero(cuentaRequest.getNumero()).tipo(cuentaRequest.getTipo())
                .saldo(cuentaRequest.getSaldo()).estado(cuentaRequest.getEstado())
                .clienteID(cuentaRequest.getClienteId()).build();
    }

    private CuentaCompletaResponse toCuentaCompletaResponse(Cuenta cuenta){
        return CuentaCompletaResponse.builder().numero(cuenta.getNumero()).tipo(cuenta.getTipo())
                .estado(cuenta.getEstado()).saldo(cuenta.getSaldo()).clienteId(cuenta.getClienteID()).build();
    }
}
