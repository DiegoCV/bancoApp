package co.com.banco.cuentas.usecase.cuenta;

import co.com.banco.cuentas.model.cuenta.Cuenta;
import co.com.banco.cuentas.model.reporte.Reporte;
import co.com.banco.cuentas.model.reporte.ReporteConsulta;

import java.util.List;
import java.util.UUID;

public interface CuentaUseCase {
    Cuenta crearCuenta(Cuenta cuenta);

    Cuenta actualizarCuenta(Cuenta cuenta);

    void eliminarCuenta(Cuenta cuenta);

    Cuenta findById(UUID cuentaId);

    Cuenta findByNumero(Long numero);

    List<Cuenta> findByClienteId(UUID clienteId);

    List<Reporte> generateReporte(ReporteConsulta reporteConsulta);
}