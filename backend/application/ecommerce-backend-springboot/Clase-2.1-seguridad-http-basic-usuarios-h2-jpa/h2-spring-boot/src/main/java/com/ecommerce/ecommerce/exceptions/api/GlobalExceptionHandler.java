package com.ecommerce.ecommerce.exceptions.api;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.ecommerce.controllers.dto.ApiErrorResponse;

import java.time.LocalDateTime;

/**
 * Manejador global de excepciones para la aplicación.
 * Esta clase se encarga de capturar y manejar diferentes tipos de excepciones
 * que pueden ocurrir durante las solicitudes HTTP.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception exception, HttpServletRequest request) {
        ApiErrorResponse  error = createApiError(exception, "Error interno en el servidor, vuelva a intentarlo", HttpStatus.INTERNAL_SERVER_ERROR);
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        ApiErrorResponse error = createApiError(exception, "Error: la petición enviada posee un formato incorrecto", HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    private ApiErrorResponse createApiError(Exception exception, String message, HttpStatus status) {
        ApiErrorResponse error = new ApiErrorResponse();
        error.setMessage(message);
        error.setErrorDetails(exception.getLocalizedMessage());
        error.setTimestamp(LocalDateTime.now());
        error.setHttpStatus(status.value());
        return error;
    }
}
