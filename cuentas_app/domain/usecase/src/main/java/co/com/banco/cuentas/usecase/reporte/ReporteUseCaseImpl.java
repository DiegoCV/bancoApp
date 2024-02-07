package co.com.banco.cuentas.usecase.reporte;

import co.com.banco.cuentas.model.cliente.ClienteReply;
import co.com.banco.cuentas.model.cliente.gateways.ClienteRabbitRepository;
import co.com.banco.cuentas.model.common.ReporteException;
import co.com.banco.cuentas.model.reporte.Reporte;
import co.com.banco.cuentas.model.reporte.ReporteConsulta;
import co.com.banco.cuentas.usecase.cuenta.CuentaUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static co.com.banco.cuentas.model.common.CommonExceptionMessages.CLIENTE_NO_EXISTE;

@RequiredArgsConstructor
public class ReporteUseCaseImpl implements ReporteUseCase {

    private final ClienteRabbitRepository clienteRabbitRepository;
    private final CuentaUseCase cuentaUseCase;

    @Override
    public List<Reporte> generarReportesPorRangoFechas(ReporteConsulta reporteConsulta) {
        ClienteReply clienteReply = clienteRabbitRepository.buscarClienteById(reporteConsulta.getClienteID());
        if(!clienteReply.isValid()){
            throw new ReporteException(CLIENTE_NO_EXISTE);
        }
        List<Reporte> reportes = cuentaUseCase.generateReporte(reporteConsulta);

        return reportes.stream().map(reporte -> {
            reporte.setCliente(clienteReply.getNombre());
            return reporte;
        }).collect(Collectors.toList());
    }

}
