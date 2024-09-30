package com.tecno.api_sec.controllers.api.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO que representa una respuesta de error de la API.
 * Contiene información detallada sobre el error, como el mensaje, el código HTTP,
 * los detalles del error y la fecha y hora en que ocurrió el error.
 */
public class ApiErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Mensaje de error descriptivo.
     */
    private String message;

    /**
     * Código de estado HTTP asociado al error.
     */
    private int httpStatus;

    /**
     * Detalles del error, como la traza de la pila (stack trace).
     */
    private String errorDetails;

    /**
     * Fecha y hora en que ocurrió el error.
     */
    private LocalDateTime timestamp;

    // Getters y Setters

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}