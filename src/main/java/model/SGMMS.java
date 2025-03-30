package model;

import model.comparators.*;
import java.util.List;
import java.util.LinkedList;
import model.exceptions.DataNotFoundException;

/**
 * Sistema de Gestión de Movilidad y Seguridad (SGMMS).
 * Esta clase administra rutas, incidentes, personas (pasajeros y conductores) y operaciones relacionadas
 * con registros, búsquedas, ordenamientos, exportación e importación de datos.
 */

public class SGMMS {

    /**
     * Lista de rutas registradas en el sistema.
     */
    private ListaEnlazadaSimple<Ruta> listaRutas;
    /**
     * Lista de incidentes registrados en el sistema.
     */
    private ListaEnlazadaSimple<Incidente> listaIncidentes;
    /**
     * Lista de personas registradas en el sistema (pasajeros y conductores).
     */
    private ListaEnlazadaSimple<Persona> listaPersonas;
    /**
     * Lista específica de conductores registrados.
     */
    private ListaEnlazadaSimple<Conductor> listaConductores = new ListaEnlazadaSimple<>();

    /**
     * Constructor que inicializa las listas de rutas, incidentes y personas.
     */

    public SGMMS() {

        listaRutas = new ListaEnlazadaSimple<>();
        listaIncidentes = new ListaEnlazadaSimple<>();
        listaPersonas = new ListaEnlazadaSimple<>();
    }

    /**
     * Obtiene la lista de conductores registrados.
     *
     * @return Lista enlazada de conductores.
     */

    public ListaEnlazadaSimple<Conductor> getListaConductores() {
        return listaConductores;
    }

    /**
     * Busca conductores cuyo nombre contenga una palabra clave.
     *
     * @param nombre La palabra clave a buscar (ignora mayúsculas y minúsculas).
     * @return Lista de conductores cuyo nombre coincida con la palabra clave.
     */

    public List<Conductor> buscarConductoresPorNombre(String nombre) {
        // Crear una lista para almacenar los conductores encontrados
        List<Conductor> conductoresEncontrados = new LinkedList<>();

        // Iterar por cada persona en la lista de personas
        for (int i = 0; i < listaPersonas.size(); i++) {
            Persona persona = listaPersonas.get(i); // Obtenemos una persona

            // Verificar si la persona es un conductor
            if (persona instanceof Conductor) {
                Conductor conductor = (Conductor) persona; // Convertimos a Conductor

                // Verificar si el nombre contiene la palabra clave (ignorando mayúsculas y minúsculas)
                if (conductor.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                    conductoresEncontrados.add(conductor); // Agregar a la lista de conductores encontrados
                }
            }
        }

        // Retornar la lista con los conductores encontrados
        return conductoresEncontrados;
    }

    /**
     * Busca un conductor específico en la lista de conductores registrados por su identificador único.
     *
     * @param id El identificador único del conductor que se desea buscar.
     * @return El conductor encontrado con el identificador proporcionado.
     * @throws DataNotFoundException Si no se encuentra un conductor con el identificador especificado.
     */

    public Conductor buscarConductor(String id) throws DataNotFoundException {
        // Iterar sobre la lista de conductores
        for (Conductor conductor : listaConductores) {
            if (conductor.getId().equals(id)) {
                return conductor; // Retornar el conductor si se encuentra el ID
            }
        }
        // Lanzar la excepción si no se encuentra el conductor en la lista
        throw new DataNotFoundException("Conductor con ID '" + id + "' no encontrado.");
    }
    /**
     * Registra una nueva ruta en el sistema.
     *
     * @param r La ruta a registrar.
     */

    public void registrarRuta(Ruta r) {
        listaRutas.addLast(r);
    }

    /**
     * Registra un nuevo incidente en el sistema.
     *
     * @param i El incidente a registrar.
     */

    public void registrarIncidente(Incidente i) {
        listaIncidentes.addLast(i);
    }

    /**
     * Registra una nueva persona en el sistema.
     *
     * @param p La persona a registrar (puede ser pasajero o conductor).
     */

    public void registrarPersona(Persona p) {
        listaPersonas.addLast(p);
    }

    /**
     * Elimina a una persona específica del sistema según su identificador único.
     *
     * @param id El identificador de la persona.
     * @return {@code true} si se eliminó correctamente; {@code false} en caso contrario.
     */

    public boolean eliminarPersona(String id) {
        for (int i = 0; i < listaPersonas.size(); i++) {
            Persona current = listaPersonas.get(i);
            if (current.getId().equals(id)) {
                return listaPersonas.remove(current);
            }
        }
        return false;
    }

    /**
     * Busca un incidente específico en la lista de incidentes registrados por su identificador único.
     *
     * @param id El identificador único del incidente que se desea buscar.
     * @return El incidente encontrado con el identificador proporcionado.
     * @throws DataNotFoundException Si no se encuentra un incidente con el identificador especificado.
     */
    public Incidente buscarIncidente(String id) throws DataNotFoundException {
        for (int i = 0; i < listaIncidentes.size(); i++) {
            Incidente inc = listaIncidentes.get(i);
            if (inc.getId().equals(id)) {
                return inc; // Retornar el incidente si se encuentra el ID
            }
        }
        // Lanzar la excepción si no se encuentra el incidente en la lista
        throw new DataNotFoundException("Incidente con ID '" + id + "' no encontrado.");
    }

    /**
     * Ordena los incidentes registrados según su fecha.
     */

    public void ordenarIncidentesPorFecha() {
        listaIncidentes.ordenar(new IncidentDateComparator());
    }

    /**
     * Ordena las rutas registradas según su distancia.
     */

    public void ordenarRutasPorDistancia() {
        listaRutas.ordenar(new RouteDistanceComparator());
    }



    /**
     * Determina la mejor ruta basándose en la distancia y el tiempo estimado.
     *
     * @return La mejor ruta o {@code null} si no hay rutas disponibles.
     */

    public Ruta determinarMejorRuta() {
        if (listaRutas.isEmpty()) return null;

        Ruta best = listaRutas.get(0);
        for (int i = 1; i < listaRutas.size(); i++) {
            Ruta current = listaRutas.get(i);
            if ((current.getDistancia() + current.getTiempoEstimado()) <
                    (best.getDistancia() + best.getTiempoEstimado())) {
                best = current;
            }
        }
        return best;
    }

    /**
     * Obtiene la lista de rutas registradas.
     *
     * @return Lista enlazada de rutas.
     */

    public ListaEnlazadaSimple<Ruta> getListaRutas() {
        return listaRutas;
    }

    /**
     * Obtiene la lista de incidentes registrados.
     *
     * @return Lista enlazada de incidentes.
     */

    public ListaEnlazadaSimple<Incidente> getListaIncidentes() {
        return listaIncidentes;
    }

    /**
     * Obtiene la lista de personas registradas.
     *
     * @return Lista enlazada de personas.
     */

    public ListaEnlazadaSimple<Persona> getListaPersonas() {
        return listaPersonas;
    }

    /**
     * Exporta los datos actuales del sistema a un objeto SGMMSData.
     *
     * @return Objeto SGMMSData que contiene los datos actuales del sistema.
     */

    public SGMMSData exportar() {
        SGMMSData datos = new SGMMSData();

        // Exportar rutas
        Ruta[] rutasArray = new Ruta[listaRutas.size()];
        for (int i = 0; i < listaRutas.size(); i++) {
            rutasArray[i] = listaRutas.get(i);
        }
        datos.setRutas(rutasArray);

        // Exportar incidentes
        Incidente[] incidentesArray = new Incidente[listaIncidentes.size()];
        for (int i = 0; i < listaIncidentes.size(); i++) {
            incidentesArray[i] = listaIncidentes.get(i);
        }
        datos.setIncidentes(incidentesArray);

        // Exportar pasajeros
        ListaEnlazadaSimple<PersonaData> pasajeros = new ListaEnlazadaSimple<>();
        ListaEnlazadaSimple<PersonaData> conductores = new ListaEnlazadaSimple<>();

        for (int i = 0; i < listaPersonas.size(); i++) {
            Persona p = listaPersonas.get(i);
            if (p instanceof Pasajero) {
                Pasajero pasajero = (Pasajero) p;
                PersonaData pasajeroData = new PersonaData();
                pasajeroData.setId(pasajero.getId());
                pasajeroData.setNombre(pasajero.getNombre());
                pasajeroData.setContacto(pasajero.getContacto());
                pasajeros.addLast(pasajeroData);
            } else if (p instanceof Conductor) {
                Conductor conductor = (Conductor) p;
                PersonaData conductorData = new PersonaData();
                conductorData.setId(conductor.getId());
                conductorData.setNombre(conductor.getNombre());
                conductorData.setContacto(conductor.getContacto());
                conductorData.setVehiculoAsignado(conductor.getVehiculoAsignado());
                conductorData.setEstado(conductor.getEstado());
                conductores.addLast(conductorData);
            }
        }


        return datos;
    }

    /**
     * Importa los datos del sistema desde un objeto SGMMSData.
     *
     * @param datos Objeto SGMMSData con los datos a cargar.
     */

    public void importar(SGMMSData datos) {
        // Limpiar las listas actuales antes de importar
        listaRutas = new ListaEnlazadaSimple<>();
        listaIncidentes = new ListaEnlazadaSimple<>();
        listaPersonas = new ListaEnlazadaSimple<>();
        listaConductores = new ListaEnlazadaSimple<>();

        // Importar rutas
        if (datos.getRutas() != null) {
            for (Ruta r : datos.getRutas()) {
                listaRutas.addLast(r);
            }
        }

        // Importar incidentes
        if (datos.getIncidentes() != null) {
            for (Incidente i : datos.getIncidentes()) {
                listaIncidentes.addLast(i);
            }
        }

        // Importar pasajeros (Personas generales)
        if (datos.getPasajeros() != null) {
            for (PersonaData pd : datos.getPasajeros()) {
                Pasajero pasajero = new Pasajero(pd.getId(), pd.getNombre(), pd.getContacto());
                listaPersonas.addLast(pasajero);
            }
        }

        // Importar conductores
        if (datos.getConductores() != null) {
            for (PersonaData pd : datos.getConductores()) {
                Conductor conductor = new Conductor(
                        pd.getId(),
                        pd.getNombre(),
                        pd.getContacto(),
                        pd.getVehiculoAsignado(),
                        pd.getEstado()
                );
                listaConductores.addLast(conductor);
                listaPersonas.addLast(conductor); // También agregarlo a la lista general de personas
            }
        }
    }

}