package com.tecno.api_sec.persistence.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

/**
 * Entidad que almacena reseñas relacionadas específicamente con productos.
 * Esta clase mapea la tabla 'product_reviews' en la base de datos y contiene
 * información sobre las reseñas de los productos, incluyendo la calificación,
 * el comentario, la fecha de la reseña, el producto asociado y el cliente que
 * realizó la reseña.
 * 
 * @author Ronald Cuello
 * @version 1.0
 * @since 2024-10-01
 */

@Entity
@Table(name = "product_reviews")
public class ProductReview {

    /**
     * Identificador único de la reseña. Este campo es generado automáticamente
     * por la base de datos y sirve como clave primaria.
     */
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Calificación dada al producto en la reseña. Debe estar en el rango de 1 a 5.
     */
    @Column(name = "rating")
    @Min(1)
    @Max(5)
    private int rating;

    /**
     * Comentario proporcionado por el cliente sobre el producto.
     */
    @Column(name = "comment")
    private String comment;

    /**
     * Fecha en la que se realizó la reseña.
     */
    @Column(name = "review_date")
    private Date review_date;

    /**
     * Producto al que se refiere la reseña. Esta relación es de muchos a uno,
     * lo que significa que muchas reseñas pueden estar asociadas a un solo producto.
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * Cliente que realizó la reseña. Esta relación es de muchos a uno,
     * lo que significa que muchas reseñas pueden estar asociadas a un solo cliente.
     */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private UserEntity customer;

    /**
     * Obtiene el identificador único de la reseña.
     *
     * @return El identificador único de la reseña.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la reseña.
     *
     * @param id El nuevo identificador único de la reseña.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la calificación dada al producto en la reseña.
     *
     * @return La calificación del producto.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Establece la calificación del producto en la reseña.
     *
     * @param rating La nueva calificación del producto.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Obtiene el comentario proporcionado por el cliente sobre el producto.
     *
     * @return El comentario del cliente.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Establece el comentario del cliente sobre el producto.
     *
     * @param comment El nuevo comentario del cliente.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Obtiene la fecha en la que se realizó la reseña.
     *
     * @return La fecha de la reseña.
     */
    public Date getReview_date() {
        return review_date;
    }

    /**
     * Establece la fecha en la que se realizó la reseña.
     *
     * @param review_date La nueva fecha de la reseña.
     */
    public void setReview_date(Date review_date) {
        this.review_date = review_date;
    }

    /**
     * Obtiene el producto al que se refiere la reseña.
     *
     * @return El producto asociado a la reseña.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Establece el producto al que se refiere la reseña.
     *
     * @param product El nuevo producto asociado a la reseña.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Obtiene el cliente que realizó la reseña.
     *
     * @return El cliente que realizó la reseña.
     */
    public UserEntity getCustomer() {
        return customer;
    }

    /**
     * Establece el cliente que realizó la reseña.
     *
     * @param customer El nuevo cliente que realizó la reseña.
     */
    public void setCustomer(UserEntity customer) {
        this.customer = customer;
    }
}