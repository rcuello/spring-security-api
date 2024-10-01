package com.tecno.api_sec.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad que representa un parámetro del sistema.
 * Esta clase mapea la tabla 'system_parameters' en la base de datos y contiene
 * información sobre los parámetros de configuración del sistema, incluyendo la clave
 * de configuración y el valor de la configuración.
 * 
 * @author Ronald Cuello
 * @version 1.0
 * @since 2024-10-01
 */
@Entity
@Table(name = "system_parameters")
public class SystemParameter {

    /**
     * Identificador único del parámetro del sistema. Este campo es generado automáticamente
     * por la base de datos y sirve como clave primaria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Clave de configuración del parámetro del sistema. Este campo es único y se utiliza
     * para identificar de manera única cada parámetro de configuración.
     */
    @Column(name = "config_key", unique = true)
    private String config_key;

    /**
     * Valor de la configuración del parámetro del sistema. Este campo contiene el valor
     * asociado a la clave de configuración.
     */
    @Column(name = "setting_value")
    private String settingValue;

    /**
     * Obtiene el identificador único del parámetro del sistema.
     *
     * @return El identificador único del parámetro del sistema.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del parámetro del sistema.
     *
     * @param id El nuevo identificador único del parámetro del sistema.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la clave de configuración del parámetro del sistema.
     *
     * @return La clave de configuración del parámetro del sistema.
     */
    public String getConfig_key() {
        return config_key;
    }

    /**
     * Establece la clave de configuración del parámetro del sistema.
     *
     * @param config_key La nueva clave de configuración del parámetro del sistema.
     */
    public void setConfig_key(String config_key) {
        this.config_key = config_key;
    }

    /**
     * Obtiene el valor de la configuración del parámetro del sistema.
     *
     * @return El valor de la configuración del parámetro del sistema.
     */
    public String getSettingValue() {
        return settingValue;
    }

    /**
     * Establece el valor de la configuración del parámetro del sistema.
     *
     * @param setting_value El nuevo valor de la configuración del parámetro del sistema.
     */
    public void setSettingValue(String setting_value) {
        this.settingValue = setting_value;
    }
}