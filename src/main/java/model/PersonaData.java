package model;

/**
 * Clase que representa los datos de una persona en el sistema.
 * Incluye información básica como id, nombre y contacto, y campos adicionales
 * específicos para conductores, como vehículo asignado y estado.
 */
public class PersonaData {

    /**
     * Identificador único de la persona.
     */
    private String id;

    /**
     * Nombre de la persona.
     */
    private String nombre;

    /**
     * Información de contacto de la persona (por ejemplo, número de teléfono o correo electrónico).
     */
    private String contacto;

    /**
     * Vehículo asignado a la persona. Este campo únicamente aplica para los conductores.
     */
    private String vehiculoAsignado;

    /**
     * Estado actual de la persona. Este campo únicamente aplica para los conductores.
     */
    private String estado;

    /**
     * Obtiene el identificador único de la persona.
     *
     * @return El identificador único.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador único de la persona.
     *
     * @param id El identificador único.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la persona.
     *
     * @return El nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la persona.
     *
     * @param nombre El nombre de la persona.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la información de contacto de la persona.
     *
     * @return La información de contacto de la persona.
     */
    public String getContacto() {
        return contacto;
    }

    /**
     * Establece la información de contacto de la persona.
     *
     * @param contacto La información de contacto de la persona.
     */
    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    /**
     * Obtiene el vehículo asignado a la persona (aplica solo para conductores).
     *
     * @return El vehículo asignado o {@code null} si no aplica.
     */
    public String getVehiculoAsignado() {
        return vehiculoAsignado;
    }

    /**
     * Establece el vehículo asignado a la persona (aplica solo para conductores).
     *
     * @param vehiculoAsignado El vehículo asignado.
     */
    public void setVehiculoAsignado(String vehiculoAsignado) {
        this.vehiculoAsignado = vehiculoAsignado;
    }

    /**
     * Obtiene el estado actual de la persona (aplica solo para conductores).
     *
     * @return El estado actual o {@code null} si no aplica.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado actual de la persona (aplica solo para conductores).
     *
     * @param estado El estado actual de la persona.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}