package model;

/**
 * Clase que representa los datos del sistema SGMMS.
 * Esta clase se utiliza para exportar e importar rutas, incidentes, pasajeros y conductores de manera estructurada.
 */
public class SGMMSData {

    /**
     * Array de rutas registradas en el sistema.
     */
    private Ruta[] rutas;

    /**
     * Array de incidentes registrados en el sistema.
     */
    private Incidente[] incidentes;

    /**
     * Array de datos de pasajeros registrados en el sistema.
     */
    private PersonaData[] pasajeros;

    /**
     * Array de datos de conductores registrados en el sistema.
     */
    private PersonaData[] conductores;

    /**
     * Obtiene el array de rutas registradas.
     *
     * @return Un array con las rutas registradas.
     */
    public Ruta[] getRutas() {
        return rutas;
    }

    /**
     * Asigna un array de rutas a este objeto.
     *
     * @param rutas Un array con las rutas a asignar.
     */
    public void setRutas(Ruta[] rutas) {
        this.rutas = rutas;
    }

    /**
     * Obtiene el array de incidentes registrados.
     *
     * @return Un array con los incidentes registrados.
     */
    public Incidente[] getIncidentes() {
        return incidentes;
    }

    /**
     * Asigna un array de incidentes a este objeto.
     *
     * @param incidentes Un array con los incidentes a asignar.
     */
    public void setIncidentes(Incidente[] incidentes) {
        this.incidentes = incidentes;
    }

    /**
     * Obtiene el array de pasajeros registrados.
     *
     * @return Un array con los datos de los pasajeros registrados.
     */
    public PersonaData[] getPasajeros() {
        return pasajeros;
    }

    /**
     * Asigna un array de pasajeros a este objeto.
     *
     * @param pasajeros Un array con los datos de los pasajeros a asignar.
     */
    public void setPasajeros(PersonaData[] pasajeros) {
        this.pasajeros = pasajeros;
    }

    /**
     * Obtiene el array de conductores registrados.
     *
     * @return Un array con los datos de los conductores registrados.
     */
    public PersonaData[] getConductores() {
        return conductores;
    }

    /**
     * Asigna un array de conductores a este objeto.
     *
     * @param conductores Un array con los datos de los conductores a asignar.
     */
    public void setConductores(PersonaData[] conductores) {
        this.conductores = conductores;
    }
}