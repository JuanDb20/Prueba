package model;

import java.util.Date;

/**
 * Clase que representa un incidente registrado en el sistema.
 * Un incidente contiene información relevante como su tipo, ubicación,
 * fecha de ocurrencia, descripción detallada y estado actual.
 */
public class Incidente {

    /**
     * Identificador único del incidente.
     */
    private final String id;

    /**
     * Tipo del incidente (por ejemplo, "Accidente", "Congestión", etc.).
     */
    private final IncidentType tipo;

    /**
     * Ubicación donde ocurrió el incidente.
     */
    private final String ubicacion;

    /**
     * Fecha y hora en que ocurrió el incidente.
     */
    private final Date fechaHora;

    /**
     * Descripción detallada del incidente.
     */
    private final String descripcion;

    /**
     * Estado actual del incidente. Valores típicos: "pendiente", "en proceso", "resuelto".
     */
    private String estado;

    /**
     * Constructor para inicializar un objeto de tipo Incidente.
     *
     * @param id         Identificador único del incidente.
     * @param tipo       Tipo del incidente (de tipo {@link IncidentType}).
     * @param ubicacion  Ubicación del incidente.
     * @param fechaHora  Fecha y hora en que ocurrió el incidente.
     * @param descripcion Descripción detallada del incidente.
     * @param estado     Estado actual del incidente.
     */
    public Incidente(String id, IncidentType tipo, String ubicacion, Date fechaHora, String descripcion, String estado) {
        this.id = id;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.fechaHora = fechaHora;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    /**
     * Obtiene el identificador único del incidente.
     *
     * @return ID del incidente.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene el tipo del incidente.
     *
     * @return Tipo del incidente, representado por {@link IncidentType}.
     */
    public IncidentType getTipo() {
        return tipo;
    }

    /**
     * Obtiene la ubicación del incidente.
     *
     * @return Ubicación del incidente.
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * Obtiene la fecha y hora del incidente.
     *
     * @return Fecha y hora en que ocurrió el incidente, de tipo {@link Date}.
     */
    public Date getFechaHora() {
        return fechaHora;
    }

    /**
     * Obtiene la descripción del incidente.
     *
     * @return Descripción detallada del incidente.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtiene el estado actual del incidente.
     *
     * @return Estado del incidente (por ejemplo, "pendiente", "en proceso", "resuelto").
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece un nuevo estado para el incidente.
     *
     * @param estado Nuevo estado del incidente (por ejemplo, "pendiente", "en proceso", "resuelto").
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}