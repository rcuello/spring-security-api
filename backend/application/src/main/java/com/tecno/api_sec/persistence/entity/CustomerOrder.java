package com.tecno.api_sec.persistence.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Entidad que representa un pedido de un cliente.
 * Esta clase mapea la tabla 'customer_orders' en la base de datos y contiene
 * información sobre los pedidos realizados por los clientes, incluyendo la fecha
 * del pedido, el estado del pedido, el monto total del pedido, los detalles del
 * pedido y el cliente que realizó el pedido.
 * 
 * @author Ronald Cuello
 * @version 1.0
 * @since 2024-10-01
 */
@Entity
@Table(name = "customer_orders")
public class CustomerOrder {

    /**
     * Identificador único del pedido. Este campo es generado automáticamente
     * por la base de datos y sirve como clave primaria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Fecha en la que se realizó el pedido.
     */
    @Column(name = "order_date")
    private Date order_date;

    /**
     * Estado actual del pedido. Puede ser "pendiente", "enviado", "entregado", etc.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CustomerOrderStatus status;

    /**
     * Monto total del pedido. Este valor representa la suma de todos los productos
     * incluidos en el pedido.
     */
    @Column(name = "total_amount")
    private BigDecimal total_amount;

    /**
     * Detalles del pedido. Esta relación es de uno a muchos, lo que significa que
     * un pedido puede tener múltiples detalles (productos).
     */
    @OneToMany(targetEntity = CustomerOrderDetail.class, mappedBy = "customerOrder")
    private Set<CustomerOrderDetail> orderDetails;

    /**
     * Cliente que realizó el pedido. Esta relación es de uno a uno, lo que significa
     * que cada pedido está asociado a un solo cliente.
     */
    @OneToOne()
    @JoinColumn(name = "customer_id")
    private UserEntity customer;

    /**
     * Obtiene el identificador único del pedido.
     *
     * @return El identificador único del pedido.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del pedido.
     *
     * @param id El nuevo identificador único del pedido.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha en la que se realizó el pedido.
     *
     * @return La fecha del pedido.
     */
    public Date getOrder_date() {
        return order_date;
    }

    /**
     * Establece la fecha en la que se realizó el pedido.
     *
     * @param order_date La nueva fecha del pedido.
     */
    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    /**
     * Obtiene el estado actual del pedido.
     *
     * @return El estado del pedido.
     */
    public CustomerOrderStatus getStatus() {
        return status;
    }

    /**
     * Establece el estado del pedido.
     *
     * @param status El nuevo estado del pedido.
     */
    public void setStatus(CustomerOrderStatus status) {
        this.status = status;
    }

    /**
     * Obtiene el monto total del pedido.
     *
     * @return El monto total del pedido.
     */
    public BigDecimal getTotal_amount() {
        return total_amount;
    }

    /**
     * Establece el monto total del pedido.
     *
     * @param total_amount El nuevo monto total del pedido.
     */
    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }

    /**
     * Obtiene el cliente que realizó el pedido.
     *
     * @return El cliente que realizó el pedido.
     */
    public UserEntity getCustomer() {
        return customer;
    }

    /**
     * Establece el cliente que realizó el pedido.
     *
     * @param customer El nuevo cliente que realizó el pedido.
     */
    public void setCustomer(UserEntity customer) {
        this.customer = customer;
    }
}