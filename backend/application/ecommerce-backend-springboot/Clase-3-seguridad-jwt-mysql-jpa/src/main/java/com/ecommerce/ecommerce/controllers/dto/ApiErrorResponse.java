package com.ecommerce.ecommerce.controllers.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiErrorResponse implements Serializable{
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
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
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
