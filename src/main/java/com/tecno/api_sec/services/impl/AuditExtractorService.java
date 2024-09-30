package com.tecno.api_sec.services.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tecno.api_sec.configuration.security.filters.AuditDetails;

/**
 * Servicio que proporciona funcionalidad para extraer detalles de auditoría del cliente desde los detalles de autenticación.
 * 
 * Este servicio se encarga de obtener el objeto de autenticación actual desde el contexto de seguridad,
 * extraer los detalles de auditoría y devolverlos. Los detalles de auditoría incluyen la dirección IP del cliente
 * y el agente de usuario (User-Agent).
 * 
 * @author Ronald Cuello
 * @version 1.0
 * @since 2024-10-01
 */
@Service
public class AuditExtractorService {

    /**
     * Extrae los detalles de auditoría del cliente desde los detalles de autenticación.
     * 
     * Este método obtiene el objeto de autenticación actual desde el contexto de seguridad,
     * extrae los detalles de auditoría y los devuelve. Los detalles de auditoría incluyen la dirección IP del cliente
     * y el agente de usuario (User-Agent).
     * 
     * @return AuditDetails Los detalles de auditoría del cliente o null si no se pueden obtener.
     */
    public AuditDetails extractClientAudit() {
        // 1. Obtener el objeto de autenticación actual desde el contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 2. Verificar si el objeto de autenticación es nulo
        if (authentication == null) {
            return null;
        }

        // 3. Obtener los detalles de autenticación
        Object details = authentication.getDetails();

        // 4. Verificar si los detalles de autenticación son una instancia de AuditDetails
        if (details instanceof AuditDetails) {
            // 5. Extraer los detalles de auditoría del cliente
            AuditDetails audit = (AuditDetails) details;
            return audit;
        }

        // 6. Si no se pueden obtener los detalles de auditoría, devolver null
        return null;
    }
}