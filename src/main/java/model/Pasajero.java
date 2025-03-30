package model;

/**
 * Clase que representa a un pasajero en el sistema.
 * Un pasajero es una subclase de {@link Persona} y puede tener una ruta asignada.
 */
public class Pasajero extends Persona {

    /**
     * Ruta asignada al pasajero.
     */
    private Ruta rutaAsignada;

    /**
     * Constructor para inicializar un objeto {@link Pasajero} con su información básica.
     * La ruta asignada inicialmente será nula.
     *
     * @param id       Identificador único del pasajero.
     * @param nombre   Nombre del pasajero.
     * @param contacto Información de contacto del pasajero.
     */
    public Pasajero(String id, String nombre, String contacto) {
        super(id, nombre, contacto);
        this.rutaAsignada = null;
    }

    /**
     * Obtiene la ruta actualmente asignada al pasajero.
     *
     * @return La ruta asignada o {@code null} si no tiene una ruta asignada.
     */
    public Ruta getRutaAsignada() {
        return rutaAsignada;
    }

    /**
     * Asigna una ruta al pasajero.
     *
     * @param rutaAsignada La ruta que se asignará al pasajero.
     */
    public void setRutaAsignada(Ruta rutaAsignada) {
        this.rutaAsignada = rutaAsignada;
    }
}