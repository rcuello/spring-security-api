package com.tecno.api_sec.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

/**
 * Entidad que representa un producto en el sistema.
 * Esta clase mapea la tabla 'products' en la base de datos y contiene
 * información sobre los productos, incluyendo el nombre, el precio,
 * el estado del producto y la categoría a la que pertenece.
 * 
 * @author Ronald Cuello
 * @version 1.0
 * @since 2024-10-01 
 */
@Entity
@Table(name = "products")
public class Product {

    /**
     * Identificador único del producto. Este campo es generado automáticamente
     * por la base de datos y sirve como clave primaria.
     */
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del producto. Este campo contiene el nombre descriptivo del producto.
     */
    @Column(name = "name")
    private String name;

    /**
     * Precio del producto. Este campo contiene el precio del producto en la moneda local.
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * Estado del producto. Este campo indica el estado actual del producto,
     * como "disponible", "agotado", "en promoción", etc.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProductStatus status;

    /**
     * Categoría a la que pertenece el producto. Esta relación es de muchos a uno,
     * lo que significa que muchos productos pueden pertenecer a una sola categoría.
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * Obtiene el identificador único del producto.
     *
     * @return El identificador único del producto.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del producto.
     *
     * @param id El nuevo identificador único del producto.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return El nombre del producto.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param name El nuevo nombre del producto.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return El precio del producto.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Establece el precio del producto.
     *
     * @param price El nuevo precio del producto.
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Obtiene el estado del producto.
     *
     * @return El estado del producto.
     */
    public ProductStatus getStatus() {
        return status;
    }

    /**
     * Establece el estado del producto.
     *
     * @param status El nuevo estado del producto.
     */
    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    /**
     * Obtiene la categoría a la que pertenece el producto.
     *
     * @return La categoría del producto.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Establece la categoría a la que pertenece el producto.
     *
     * @param category La nueva categoría del producto.
     */
    public void setCategory(Category category) {
        this.category = category;
    }
}