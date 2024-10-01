package com.tecno.api_sec.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad que representa una categoría de productos en el sistema.
 * Esta clase mapea la tabla 'categories' en la base de datos y contiene
 * información sobre las categorías de productos, incluyendo el nombre
 * y el estado de la categoría.
 * 
 * @author Ronald Cuello
 * @version 1.0
 * @since 2024-10-01
 */
@Entity
@Table(name = "categories")
public class Category {

    /**
     * Identificador único de la categoría. Este campo es generado automáticamente
     * por la base de datos y sirve como clave primaria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de la categoría. Este campo contiene el nombre descriptivo de la categoría.
     */
    @Column(name = "name")
    private String name;

    /**
     * Estado de la categoría. Este campo indica el estado actual de la categoría,
     * como "activa", "inactiva", "en revisión", etc.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CategoryStatus status;

    /**
     * Obtiene el identificador único de la categoría.
     *
     * @return El identificador único de la categoría.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la categoría.
     *
     * @param id El nuevo identificador único de la categoría.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la categoría.
     *
     * @return El nombre de la categoría.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre de la categoría.
     *
     * @param name El nuevo nombre de la categoría.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el estado de la categoría.
     *
     * @return El estado de la categoría.
     */
    public CategoryStatus getStatus() {
        return status;
    }

    /**
     * Establece el estado de la categoría.
     *
     * @param status El nuevo estado de la categoría.
     */
    public void setStatus(CategoryStatus status) {
        this.status = status;
    }
}