package model;

/**
 * Clase que representa una ruta dentro del sistema.
 * Incluye detalles como la distancia, tiempo estimado y los puntos de inicio y fin de la ruta.
 */
public class Ruta {

    /**
     * Identificador único de la ruta.
     */
    private String id;

    /**
     * Distancia total de la ruta en kilómetros.
     */
    private final double distancia;

    /**
     * Tiempo estimado para completar la ruta en minutos.
     */
    private final int tiempoEstimado;

    /**
     * Punto de inicio de la ruta.
     */
    private final String puntoInicio;

    /**
     * Punto de finalización de la ruta.
     */
    private final String puntoFin;

    /**
     * Constructor para inicializar una ruta con toda su información.
     *
     * @param id            Identificador único de la ruta.
     * @param distancia     Distancia total de la ruta en kilómetros.
     * @param tiempoEstimado Tiempo estimado para completar la ruta en minutos.
     * @param puntoInicio   Nombre o descripción del punto de inicio de la ruta.
     * @param puntoFin      Nombre o descripción del punto de finalización de la ruta.
     */
    public Ruta(String id, double distancia, int tiempoEstimado, String puntoInicio, String puntoFin) {
        this.id = id;
        this.distancia = distancia;
        this.tiempoEstimado = tiempoEstimado;
        this.puntoInicio = puntoInicio;
        this.puntoFin = puntoFin;
    }

    /**
     * Obtiene el identificador único de la ruta.
     *
     * @return El identificador único de la ruta.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador único de la ruta.
     *
     * @param id El nuevo identificador único de la ruta.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene la distancia total de la ruta en kilómetros.
     *
     * @return La distancia de la ruta en kilómetros.
     */
    public double getDistancia() {
        return distancia;
    }

    /**
     * Obtiene el tiempo estimado para completar la ruta en minutos.
     *
     * @return Tiempo estimado en minutos.
     */
    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    /**
     * Obtiene el punto de inicio de la ruta.
     *
     * @return El punto de inicio de la ruta.
     */
    public String getPuntoInicio() {
        return puntoInicio;
    }

    /**
     * Obtiene el punto de finalización de la ruta.
     *
     * @return El punto de finalización de la ruta.
     */
    public String getPuntoFin() {
        return puntoFin;
    }
}