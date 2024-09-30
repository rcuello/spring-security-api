package com.tecno.api_sec.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tecno.api_sec.controllers.api.dtos.ApiErrorResponse;

import java.time.LocalDateTime;

/**
 * Manejador global de excepciones para la aplicación.
 * Esta clase se encarga de capturar y manejar diferentes tipos de excepciones
 * que pueden ocurrir durante las solicitudes HTTP.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones genéricas que no han sido capturadas por otros manejadores.
     * 
     * @param exception La excepción que ha ocurrido.
     * @param request   La solicitud HTTP que causó la excepción.
     * @return Una respuesta HTTP con un objeto ApiError que contiene detalles del error.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception exception, HttpServletRequest request) {
        ApiErrorResponse error = createApiError(exception, "Error interno en el servidor, vuelva a intentarlo", HttpStatus.INTERNAL_SERVER_ERROR);
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    /**
     * Maneja excepciones de tipo MethodArgumentNotValidException, que ocurren cuando
     * los argumentos de un método no son válidos (por ejemplo, validación fallida).
     * 
     * @param exception La excepción que ha ocurrido.
     * @param request   La solicitud HTTP que causó la excepción.
     * @return Una respuesta HTTP con un objeto ApiError que contiene detalles del error.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        ApiErrorResponse error = createApiError(exception, "Error: la petición enviada posee un formato incorrecto", HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Crea un objeto ApiError con los detalles de la excepción.
     * 
     * @param exception La excepción que ha ocurrido.
     * @param message   El mensaje de error a incluir en el objeto ApiError.
     * @param status    El código de estado HTTP asociado al error.
     * @return Un objeto ApiError con los detalles del error.
     */
    private ApiErrorResponse createApiError(Exception exception, String message, HttpStatus status) {
        ApiErrorResponse error = new ApiErrorResponse();
        error.setMessage(message);
        error.setErrorDetails(exception.getLocalizedMessage());
        error.setTimestamp(LocalDateTime.now());
        error.setHttpStatus(status.value());
        return error;
    }
}