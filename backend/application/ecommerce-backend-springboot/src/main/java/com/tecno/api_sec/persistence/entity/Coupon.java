package com.tecno.api_sec.persistence.entity;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad que representa un cupón de descuento.
 * Esta clase mapea la tabla 'coupons' en la base de datos y contiene
 * información sobre los cupones de descuento, incluyendo el código del cupón,
 * el porcentaje de descuento, la fecha de validez, la fecha de expiración y
 * el límite de uso del cupón.
 * 
 * @author Ronald Cuello
 * @version 1.0
 * @since 2024-10-01
 */
@Entity
@Table(name = "coupons")
public class Coupon {

    /**
     * Identificador único del cupón. Este campo es generado automáticamente
     * por la base de datos y sirve como clave primaria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Código único del cupón. Este código es utilizado por los clientes para
     * aplicar el descuento en sus compras.
     */
    @Column(name = "code")
    private String code;

    /**
     * Porcentaje de descuento que ofrece el cupón. Este valor representa el
     * descuento aplicable a una compra cuando se utiliza el cupón.
     */
    @Column(name = "discount_Percentage")
    private BigDecimal discount_Percentage;

    /**
     * Fecha de inicio de validez del cupón. A partir de esta fecha, el cupón
     * puede ser utilizado en las compras.
     */
    @Column(name = "valid_from")
    private Date valid_from;

    /**
     * Fecha de expiración del cupón. Después de esta fecha, el cupón ya no es
     * válido y no puede ser utilizado en las compras.
     */
    @Column(name = "valid_until")
    private Date valid_until;

    /**
     * Límite de uso del cupón. Este valor indica cuántas veces puede ser utilizado
     * el cupón antes de que se agote su validez.
     */
    @Column(name = "usage_limit")
    private int usage_limit;

    /**
     * Obtiene el identificador único del cupón.
     *
     * @return El identificador único del cupón.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del cupón.
     *
     * @param id El nuevo identificador único del cupón.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el código único del cupón.
     *
     * @return El código del cupón.
     */
    public String getCode() {
        return code;
    }

    /**
     * Establece el código único del cupón.
     *
     * @param code El nuevo código del cupón.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Obtiene el porcentaje de descuento del cupón.
     *
     * @return El porcentaje de descuento del cupón.
     */
    public BigDecimal getDiscount_Percentage() {
        return discount_Percentage;
    }

    /**
     * Establece el porcentaje de descuento del cupón.
     *
     * @param discount_Percentage El nuevo porcentaje de descuento del cupón.
     */
    public void setDiscount_Percentage(BigDecimal discount_Percentage) {
        this.discount_Percentage = discount_Percentage;
    }

    /**
     * Obtiene la fecha de inicio de validez del cupón.
     *
     * @return La fecha de inicio de validez del cupón.
     */
    public Date getValid_from() {
        return valid_from;
    }

    /**
     * Establece la fecha de inicio de validez del cupón.
     *
     * @param valid_from La nueva fecha de inicio de validez del cupón.
     */
    public void setValid_from(Date valid_from) {
        this.valid_from = valid_from;
    }

    /**
     * Obtiene la fecha de expiración del cupón.
     *
     * @return La fecha de expiración del cupón.
     */
    public Date getValid_until() {
        return valid_until;
    }

    /**
     * Establece la fecha de expiración del cupón.
     *
     * @param valid_until La nueva fecha de expiración del cupón.
     */
    public void setValid_until(Date valid_until) {
        this.valid_until = valid_until;
    }

    /**
     * Obtiene el límite de uso del cupón.
     *
     * @return El límite de uso del cupón.
     */
    public int getUsage_limit() {
        return usage_limit;
    }

    /**
     * Establece el límite de uso del cupón.
     *
     * @param usage_limit El nuevo límite de uso del cupón.
     */
    public void setUsage_limit(int usage_limit) {
        this.usage_limit = usage_limit;
    }
}