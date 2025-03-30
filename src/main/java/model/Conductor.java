package model;

/**
 * Clase que representa a un conductor dentro del sistema.
 * Un conductor hereda características básicas de una Persona y posee atributos adicionales
 * como el vehículo asignado y su estado actual.
 */
public class Conductor extends Persona {

    /**
     * Vehículo asignado al conductor.
     */
    private final String vehiculoAsignado;

    /**
     * Estado actual del conductor. Por ejemplo: "disponible" o "en ruta".
     */
    private String estado;

    /**
     * Constructor de la clase Conductor.
     * Inicializa los atributos del conductor, incluyendo los heredados de Persona
     * y los propios de la clase.
     *
     * @param id               ID único del conductor.
     * @param nombre           Nombre del conductor.
     * @param contacto         Información de contacto del conductor.
     * @param vehiculoAsignado Vehículo asignado al conductor.
     * @param estado           Estado inicial del conductor (por ejemplo, "disponible" o "en ruta").
     */
    public Conductor(String id, String nombre, String contacto, String vehiculoAsignado, String estado) {
        super(id, nombre, contacto);
        this.vehiculoAsignado = vehiculoAsignado;
        this.estado = estado;
    }

    /**
     * Retorna el vehículo asignado al conductor.
     *
     * @return Vehículo asignado al conductor.
     */
    public String getVehiculoAsignado() {
        return vehiculoAsignado;
    }

    /**
     * Retorna el estado actual del conductor.
     *
     * @return Estado del conductor (por ejemplo, "disponible" o "en ruta").
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Modifica el estado actual del conductor.
     *
     * @param estado Nuevo estado del conductor. Debe ser un valor válido como "disponible" o "en ruta".
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}