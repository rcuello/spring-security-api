package com.tecno.api_sec.configuration.security.filters;

/**
 * Clase que encapsula detalles de auditoría.
 * 
 * Esta clase se utiliza para almacenar información de auditoría relacionada con una solicitud HTTP,
 * como la dirección IP del cliente y el agente de usuario.
 * 
 * @author Ronald Cuello
 * @version 1.0
 * @since 2024-10-01
 */
public class AuditDetails {

    /**
     * La dirección IP remota del cliente.
     */
    private String remoteAddress;

    /**
     * El agente de usuario (User-Agent) del cliente.
     */
    private String userAgent;

    /**
     * Constructor que inicializa los detalles de auditoría.
     * 
     * @param remoteAddress La dirección IP remota del cliente.
     * @param userAgent     El agente de usuario (User-Agent) del cliente.
     */
    public AuditDetails(String remoteAddress, String userAgent) {
        this.remoteAddress = remoteAddress;
        this.userAgent = userAgent;
    }

    /**
     * Obtiene la dirección IP remota del cliente.
     * 
     * @return La dirección IP remota del cliente.
     */
    public String getRemoteAddress() {
        return remoteAddress;
    }

    /**
     * Obtiene el agente de usuario (User-Agent) del cliente.
     * 
     * @return El agente de usuario (User-Agent) del cliente.
     */
    public String getUserAgent() {
        return userAgent;
    }
}