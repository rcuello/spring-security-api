package com.tecno.api_sec.persistence.entity;

/**
 * Enumeración que representa los diferentes estados posibles de un pedido de cliente.
 * Cada valor en esta enumeración describe un estado específico en el ciclo de vida de un pedido.
 * 
 * @author Ronald Cuello
 * @version 1.0
 * @since 2024-10-01
 */
public enum CustomerOrderStatus {

    /**
     * La orden ha sido creada pero aún no ha sido procesada.
     * Este estado indica que el pedido ha sido registrado pero no se ha iniciado su procesamiento.
     */
    PENDIENTE,

    /**
     * La orden ha sido confirmada y está lista para ser procesada.
     * Este estado indica que el pedido ha sido verificado y confirmado, y está listo para ser procesado.
     */
    CONFIRMADA,

    /**
     * La orden ha sido enviada al cliente.
     * Este estado indica que el pedido ha sido despachado y está en camino hacia el cliente.
     */
    ENVIADA,

    /**
     * La orden ha sido entregada al cliente.
     * Este estado indica que el pedido ha sido recibido por el cliente.
     */
    ENTREGADA,

    /**
     * La orden ha sido cancelada por el cliente o el vendedor.
     * Este estado indica que el pedido ha sido anulado antes de su entrega.
     */
    CANCELADA,

    /**
     * El cliente ha devuelto la orden.
     * Este estado indica que el pedido ha sido devuelto por el cliente después de haber sido entregado.
     */
    RETORNADA,

    /**
     * El pago de la orden ha sido reembolsado al cliente.
     * Este estado indica que el cliente ha recibido un reembolso por el pedido.
     */
    REEMBOLSADA
}