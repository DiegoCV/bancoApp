package co.com.banco.cuentas.usecase.cuenta;

import co.com.banco.cuentas.model.cliente.ClienteReply;
import co.com.banco.cuentas.model.cliente.gateways.ClienteRabbitRepository;
import co.com.banco.cuentas.model.common.CuentaException;
import co.com.banco.cuentas.model.cuenta.Cuenta;
import co.com.banco.cuentas.model.cuenta.gateways.CuentaRepository;
import co.com.banco.cuentas.model.reporte.Reporte;
import co.com.banco.cuentas.model.reporte.ReporteConsulta;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import static co.com.banco.cuentas.model.common.CommonExceptionMessages.CLIENTE_NO_EXISTE;
import static co.com.banco.cuentas.model.common.CommonExceptionMessages.CUENTA_NO_EXISTE;
import static co.com.banco.cuentas.model.common.CommonExceptionMessages.NUMERO_DE_CUENTA_EN_USO;

@RequiredArgsConstructor
public class CuentaUseCaseImpl implements CuentaUseCase {

    private final CuentaRepository cuentaRepository;
    private final ClienteRabbitRepository clienteRabbitRepository;

    @Override
    public Cuenta crearCuenta(Cuenta cuenta) {
        validarCuentaEnUso(cuenta.getNumero());
        validarClienteExiste(cuenta.getClienteID());

        return cuentaRepository.save(cuenta);
    }

    private void validarCuentaEnUso(Long numero){
        Cuenta cuentaByNumero = cuentaRepository.findByNumero(numero);
        if(cuentaByNumero != null){
            throw new CuentaException(NUMERO_DE_CUENTA_EN_USO);
        }
    }

    private void validarClienteExiste(UUID clienteId){
        ClienteReply clienteReply = clienteRabbitRepository.buscarClienteById(clienteId);
        if(!clienteReply.isValid()){
            throw new CuentaException(CLIENTE_NO_EXISTE);
        }
    }

    @Override
    public Cuenta actualizarCuenta(Cuenta cuenta) {
        Cuenta cuentaById = findById(cuenta.getId());
        cuentaById.setSaldo(cuenta.getSaldo());
        cuentaById.setEstado(cuenta.getEstado());

        return cuentaRepository.save(cuentaById);
    }

    @Override
    public void eliminarCuenta(Cuenta cuenta) {
        cuentaRepository.delete(cuenta);
    }

    @Override
    public Cuenta findById(UUID cuentaId) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId);
        if(cuenta == null){
            throw new CuentaException(CUENTA_NO_EXISTE);
        }

        return cuenta;
    }

    @Override
    public Cuenta findByNumero(Long numero) {
        Cuenta cuenta = cuentaRepository.findByNumero(numero);
        if(cuenta == null){
            throw new CuentaException(CUENTA_NO_EXISTE);
        }

        return cuenta;
    }

    @Override
    public List<Cuenta> findByClienteId(UUID clienteId) {
        return cuentaRepository.findByClienteId(clienteId);
    }

    @Override
    public List<Reporte> generateReporte(ReporteConsulta reporteConsulta) {
        return cuentaRepository.generateReporte(reporteConsulta);
    }
}
