package model;

/**
 * Enumeración que representa los diferentes tipos de incidentes
 * que pueden ser registrados en el sistema.
 */
public enum IncidentType {
    /**
     * Representa un incidente del tipo "Robo".
     */
    ROBO,

    /**
     * Representa un incidente del tipo "Accidente".
     */
    ACCIDENTE,

    /**
     * Representa un incidente del tipo "Incendio".
     */
    INCENDIO,

    /**
     * Representa un incidente de otro tipo no especificado.
     */
    OTRO
}