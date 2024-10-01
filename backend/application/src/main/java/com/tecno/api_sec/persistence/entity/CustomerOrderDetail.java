package com.tecno.api_sec.persistence.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Entidad que representa los detalles de un pedido de cliente.
 * Esta clase mapea la tabla 'customer_order_details' en la base de datos y contiene
 * información sobre los productos incluidos en un pedido, incluyendo la cantidad,
 * el producto asociado y el pedido al que pertenece.
 * 
 * @author Ronald Cuello
 * @version 1.0
 * @since 2024-10-01
 */
@Entity
@Table(name = "customer_order_details")
public class CustomerOrderDetail {

    /**
     * Identificador único del detalle del pedido. Este campo es generado automáticamente
     * por la base de datos y sirve como clave primaria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Cantidad del producto en el pedido. Este valor indica cuántas unidades del producto
     * se han solicitado en el pedido.
     */
    @Column(name = "quantity")
    private BigDecimal quantity;

    /**
     * Producto asociado al detalle del pedido. Esta relación es de uno a uno, lo que significa
     * que cada detalle de pedido está asociado a un solo producto.
     */
    @OneToOne()
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * Pedido de cliente al que pertenece el detalle. Esta relación es de muchos a uno, lo que
     * significa que varios detalles de pedido pueden pertenecer a un solo pedido de cliente.
     */
    @ManyToOne
    @JoinColumn(name = "customer_order_id")
    private CustomerOrder customerOrder;

    /**
     * Obtiene el identificador único del detalle del pedido.
     *
     * @return El identificador único del detalle del pedido.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del detalle del pedido.
     *
     * @param id El nuevo identificador único del detalle del pedido.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la cantidad del producto en el pedido.
     *
     * @return La cantidad del producto en el pedido.
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * Establece la cantidad del producto en el pedido.
     *
     * @param quantity La nueva cantidad del producto en el pedido.
     */
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    /**
     * Obtiene el producto asociado al detalle del pedido.
     *
     * @return El producto asociado al detalle del pedido.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Establece el producto asociado al detalle del pedido.
     *
     * @param product El nuevo producto asociado al detalle del pedido.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Obtiene el pedido de cliente al que pertenece el detalle.
     *
     * @return El pedido de cliente al que pertenece el detalle.
     */
    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    /**
     * Establece el pedido de cliente al que pertenece el detalle.
     *
     * @param customerOrder El nuevo pedido de cliente al que pertenece el detalle.
     */
    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }
}