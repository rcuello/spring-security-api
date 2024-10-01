package com.tecno.api_sec.persistence.entity;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Entidad que representa un pago realizado por un cliente.
 * Esta clase mapea la tabla 'customer_payments' en la base de datos y contiene
 * información sobre los pagos realizados por los clientes, incluyendo la orden
 * asociada, el método de pago, el subtotal, el porcentaje de impuesto, el monto
 * total, la fecha de pago y la moneda utilizada.
 * 
 * @author Ronald Cuello
 * @version 1.0
 * @since 2024-10-01 
 */
@Entity
@Table(name = "customer_payments")
public class CustomerPayment {

    /**
     * Identificador único del pago. Este campo es generado automáticamente
     * por la base de datos y sirve como clave primaria.
     */
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Orden del cliente asociada al pago. Este campo no puede ser nulo y
     * representa la orden que se está pagando.
     */
    @OneToOne()
    @JoinColumn(name = "customer_order_id", nullable = false)
    @NotNull(message = "La orden del cliente no puede ser nula")
    private CustomerOrder customerOrder;

    /**
     * Método de pago utilizado. Este campo no puede ser nulo y representa
     * el método de pago utilizado por el cliente (por ejemplo, tarjeta de crédito, PayPal, etc.).
     */
    @Column(name = "payment_method", nullable = false)
    @NotNull(message = "El método de pago no puede ser nulo")
    private String paymentMethod;

    /**
     * Subtotal del pago. Este campo no puede ser nulo y representa el monto
     * antes de aplicar los impuestos.
     */
    @Column(name = "subtotal_amount", nullable = false)
    @NotNull(message = "El subtotal no puede ser nulo")
    private BigDecimal subtotalAmount;

    /**
     * Porcentaje de impuesto aplicado al pago. Este campo no puede ser nulo
     * y representa el porcentaje de impuesto aplicado al subtotal.
     */
    @Column(name = "tax_percentage", nullable = false)
    @NotNull(message = "El porcentaje de impuesto no puede ser nulo")
    private double taxPercentage;

    /**
     * Monto total del pago. Este campo no puede ser nulo y representa el monto
     * total a pagar después de aplicar los impuestos.
     */
    @Column(name = "total_amount", nullable = false)
    @NotNull(message = "El monto total no puede ser nulo")
    private BigDecimal totalAmount;

    /**
     * Fecha en la que se realizó el pago. Este campo no puede ser nulo y
     * representa la fecha en la que se completó el pago.
     */
    @Column(name = "payment_date", nullable = false)
    @NotNull(message = "La fecha de pago no puede ser nula")
    private Date paymentDate;

    /**
     * Moneda utilizada para el pago. Este campo no puede ser nulo y representa
     * la moneda en la que se realizó el pago (por ejemplo, USD, EUR, etc.).
     */
    @Column(name = "currency", nullable = false)
    @NotNull(message = "La moneda de pago no puede ser nula")
    private String currency;

    /**
     * Obtiene el identificador único del pago.
     *
     * @return El identificador único del pago.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del pago.
     *
     * @param id El nuevo identificador único del pago.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la orden del cliente asociada al pago.
     *
     * @return La orden del cliente asociada al pago.
     */
    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    /**
     * Establece la orden del cliente asociada al pago.
     *
     * @param customerOrder La nueva orden del cliente asociada al pago.
     */
    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    /**
     * Obtiene el método de pago utilizado.
     *
     * @return El método de pago utilizado.
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Establece el método de pago utilizado.
     *
     * @param paymentMethod El nuevo método de pago utilizado.
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Obtiene el subtotal del pago.
     *
     * @return El subtotal del pago.
     */
    public BigDecimal getSubtotalAmount() {
        return subtotalAmount;
    }

    /**
     * Establece el subtotal del pago.
     *
     * @param subtotalAmount El nuevo subtotal del pago.
     */
    public void setSubtotalAmount(BigDecimal subtotalAmount) {
        this.subtotalAmount = subtotalAmount;
    }

    /**
     * Obtiene el porcentaje de impuesto aplicado al pago.
     *
     * @return El porcentaje de impuesto aplicado al pago.
     */
    public double getTaxPercentage() {
        return taxPercentage;
    }

    /**
     * Establece el porcentaje de impuesto aplicado al pago.
     *
     * @param taxPercentage El nuevo porcentaje de impuesto aplicado al pago.
     */
    public void setTaxPercentage(double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    /**
     * Obtiene el monto total del pago.
     *
     * @return El monto total del pago.
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * Establece el monto total del pago.
     *
     * @param totalAmount El nuevo monto total del pago.
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * Obtiene la fecha en la que se realizó el pago.
     *
     * @return La fecha de pago.
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * Establece la fecha en la que se realizó el pago.
     *
     * @param paymentDate La nueva fecha de pago.
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * Obtiene la moneda utilizada para el pago.
     *
     * @return La moneda utilizada para el pago.
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Establece la moneda utilizada para el pago.
     *
     * @param currency La nueva moneda utilizada para el pago.
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}