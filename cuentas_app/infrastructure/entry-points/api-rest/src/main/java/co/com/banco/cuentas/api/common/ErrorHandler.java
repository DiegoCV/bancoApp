package co.com.banco.cuentas.api.common;

import co.com.banco.cuentas.model.common.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(CuentaException.class)
    public ResponseEntity<ErrorInfo> cuentaException(HttpServletRequest request, CuentaException e) {
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MovimientoConSaldoNoDisponibleException.class)
    public ResponseEntity<ErrorInfo> saldoNoDisponibleException(HttpServletRequest request,
                                                                MovimientoConSaldoNoDisponibleException e) {
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FormatoFechasExepcion.class)
    public ResponseEntity<ErrorInfo> formatoFechasExepcion(HttpServletRequest request, FormatoFechasExepcion e) {
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MovimientoException.class)
    public ResponseEntity<ErrorInfo> movimientoException(HttpServletRequest request, MovimientoException e) {
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OperarSaldoException.class)
    public ResponseEntity<ErrorInfo> operarSaldoException(HttpServletRequest request, OperarSaldoException e) {
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReporteException.class)
    public ResponseEntity<ErrorInfo> reporteException(HttpServletRequest request, ReporteException e) {
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
}
