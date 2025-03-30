package model;

import java.util.Date;
import java.io.IOException;
import java.util.List;

import model.exceptions.JSONFormatException;
import model.exceptions.DataNotFoundException;

public class Controller {

    private final SGMMS sgmms;
    private final JSONHandler jsonHandler;

    public Controller() {
        sgmms = new SGMMS();
        this.jsonHandler = new JSONHandler();
    }

    /**
     * Registra una nueva ruta en el sistema.
     *
     * @param id ID de la ruta
     * @param distancia Distancia en km
     * @param tiempoEstimado Tiempo estimado en minutos
     * @param puntoInicio Punto de inicio de la ruta
     * @param puntoFin Punto final de la ruta
     */
    public void registrarRuta(String id, double distancia, int tiempoEstimado, String puntoInicio, String puntoFin) {
        Ruta nuevaRuta = new Ruta(id, distancia, tiempoEstimado, puntoInicio, puntoFin);
        sgmms.registrarRuta(nuevaRuta);
    }

    /**
     * Registra un nuevo incidente en el sistema.
     *
     * @param id ID del incidente
     * @param tipoTexto Tipo de incidente como texto
     * @param ubicacion Ubicación del incidente
     * @param fechaHora Fecha y hora del incidente
     * @param descripcion Descripción del incidente
     * @param estado Estado del incidente
     * @throws IllegalArgumentException Si el tipo de incidente no es válido
     */
    public void registrarIncidente(String id, String tipoTexto, String ubicacion, Date fechaHora, String descripcion, String estado) {
        // Convertir el tipo en texto al enum IncidentType
        IncidentType tipo;
        try {
            tipo = IncidentType.valueOf(tipoTexto.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El tipo de incidente '" + tipoTexto + "' no es válido. Tipos permitidos: ROBO, ACCIDENTE, INCENDIO, OTRO.");
        }

        // Crear e insertar el incidente
        Incidente nuevoIncidente = new Incidente(id, tipo, ubicacion, fechaHora, descripcion, estado);
        sgmms.registrarIncidente(nuevoIncidente);
    }

    /**
     * Registra un nuevo pasajero en el sistema.
     *
     * @param id ID del pasajero
     * @param nombre Nombre del pasajero
     * @param contacto Información de contacto del pasajero
     * @param idRuta ID de la ruta asignada
     */
    public void registrarPasajero(String id, String nombre, String contacto, String idRuta) {
        Ruta rutaAsignada = buscarRuta(idRuta);
        if (rutaAsignada == null) {
            throw new IllegalArgumentException("La ruta con ID '" + idRuta + "' no existe.");
        }
        Pasajero nuevoPasajero = new Pasajero(id, nombre, contacto);
        nuevoPasajero.setRutaAsignada(rutaAsignada);
        sgmms.registrarPersona(nuevoPasajero);
    }

    /**
     * Registra un nuevo conductor en el sistema.
     *
     * @param id ID del conductor
     * @param nombre Nombre del conductor
     * @param contacto Información de contacto del conductor
     * @param vehiculoAsignado Vehículo asignado al conductor
     * @param estado Estado actual del conductor (e.g., disponible, en ruta)
     */
    public void registrarConductor(String id, String nombre, String contacto, String vehiculoAsignado, String estado) {
        Conductor nuevoConductor = new Conductor(id, nombre, contacto, vehiculoAsignado, estado);
        sgmms.registrarPersona(nuevoConductor);
    }

    /**
     * Busca una ruta por ID.
     *
     * @param idRuta ID de la ruta a buscar
     * @return La ruta encontrada o null si no existe
     */
    private Ruta buscarRuta(String idRuta) {
        for (Ruta ruta : sgmms.getListaRutas()) {
            if (ruta.getId().equals(idRuta)) {
                return ruta;
            }
        }
        return null;
    }

    /**
     * Consulta las rutas ordenadas por distancia y devuelve un reporte como cadena.
     *
     * @return Reporte de rutas ordenadas por distancia
     */
    public String consultarRutasOrdenadasPorDistancia() {
        sgmms.ordenarRutasPorDistancia();
        StringBuilder sb = new StringBuilder("Rutas ordenadas por distancia:\n");
        for (Ruta ruta : sgmms.getListaRutas()) {
            sb.append("ID: ").append(ruta.getId())
                    .append(" | Distancia: ").append(ruta.getDistancia()).append(" km")
                    .append(" | Tiempo: ").append(ruta.getTiempoEstimado()).append(" min\n");
        }
        return sb.toString();
    }

    /**
     * Consulta los incidentes ordenados por fecha y devuelve un reporte como cadena.
     *
     * @return Reporte de incidentes ordenados por fecha
     */
    public String consultarIncidentesOrdenadosPorFecha() {
        sgmms.ordenarIncidentesPorFecha();
        StringBuilder sb = new StringBuilder("Incidentes ordenados por fecha:\n");
        for (Incidente inc : sgmms.getListaIncidentes()) {
            sb.append("ID: ").append(inc.getId())
                    .append(" | Tipo: ").append(inc.getTipo())
                    .append(" | Fecha: ").append(inc.getFechaHora())
                    .append(" | Estado: ").append(inc.getEstado()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Devuelve una lista de personas en formato de texto para mostrar en la interfaz.
     *
     * @return Lista de personas como texto.
     */
    public String obtenerPersonasComoTexto() {
        StringBuilder sb = new StringBuilder();
        for (Persona persona : sgmms.getListaPersonas()) {
            sb.append("ID: ").append(persona.getId())
                    .append(" - Nombre: ").append(persona.getNombre()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Elimina una persona del sistema según su ID.
     *
     * @param id ID de la persona a eliminar.
     * @return true si se eliminó exitosamente, false si no se encontró
     */
    public boolean eliminarPersona(String id) {
        return sgmms.eliminarPersona(id);
    }

    /**
     * Genera un reporte detallado del sistema.
     *
     * @return Reporte en formato de texto
     */
    public String generarReporte() {
        Reporte reporte = new Reporte();
        return reporte.generarReporte(sgmms);
    }

    /**
     * Exporta los datos actuales del sistema a un archivo JSON.
     *
     * @param filePath La ruta del archivo JSON donde se guardarán los datos.
     * @throws IOException Si ocurre un error de escritura.
     */
    public void exportarDatosAJson(String filePath) throws IOException {
        SGMMSData datos = sgmms.exportar();
        jsonHandler.escribirArchivo(filePath, datos);
    }

    /**
     * Importa datos desde un archivo JSON y los carga al sistema.
     *
     * @param filePath La ruta del archivo JSON desde donde se cargarán los datos.
     * @throws IOException          Si ocurre un error de lectura.
     * @throws JSONFormatException Si el formato del archivo JSON es inválido.
     */
    public void importarDatosDesdeJson(String filePath) throws IOException, JSONFormatException {
        SGMMSData datos = jsonHandler.parseJSON(jsonHandler.leerArchivo(filePath), SGMMSData.class);
        sgmms.importar(datos);
    }

    /**
     * Busca un incidente en el sistema según su ID.
     *
     * @param id ID único del incidente que se desea buscar.
     * @return Una cadena de texto con los detalles del incidente si se encuentra (incluyendo
     *         su tipo, ubicación, fecha, descripción y estado). Si no se encuentra un incidente
     *         con el ID proporcionado, retorna un mensaje indicando que el incidente no fue encontrado.
     */
    public String buscarIncidentePorId(String id) {
        // Iterar sobre la lista de incidentes
        for (int i = 0; i < sgmms.getListaIncidentes().size(); i++) {
            Incidente incidente = sgmms.getListaIncidentes().get(i);

            // Si el ID coincide, devolvemos una descripción formateada
            if (incidente.getId().equals(id)) {
                return "ID: " + incidente.getId() + "\n" +
                        "Tipo: " + incidente.getTipo() + "\n" +
                        "Ubicación: " + incidente.getUbicacion() + "\n" +
                        "Fecha: " + incidente.getFechaHora() + "\n" +
                        "Descripción: " + incidente.getDescripcion() + "\n" +
                        "Estado: " + incidente.getEstado();
            }
        }
        // Si no se encuentra el incidente, devolver un mensaje genérico
        return "El incidente con ID " + id + " no se encontró.";
    }
    /**
     * Busca conductores registrados cuyo nombre coincida parcial o totalmente
     * con el nombre proporcionado.
     *
     * @param nombre Nombre (total o parcial) del conductor a buscar.
     * @return Una cadena de texto con los datos de los conductores encontrados, incluyendo
     *         ID, nombre, vehículo asignado y estado. Si no se encuentran coincidencias,
     *         retorna una cadena vacía.
     */
    public String buscarConductoresPorNombre(String nombre) {
        List<Conductor> conductores = sgmms.buscarConductoresPorNombre(nombre);

        StringBuilder resultado = new StringBuilder();
        for (Conductor conductor : conductores) {
            resultado.append("ID: ").append(conductor.getId())
                    .append(", Nombre: ").append(conductor.getNombre())
                    .append(", Vehículo: ").append(conductor.getVehiculoAsignado())
                    .append(", Estado: ").append(conductor.getEstado())
                    .append("\n");
        }
        return resultado.toString();
    }

    /**
     * Obtiene los IDs de todos los incidentes registrados en el sistema.
     *
     * @return Una cadena de texto con los IDs de los incidentes disponibles. Si no hay incidentes,
     *         retorna un mensaje indicando que no hay incidentes registrados.
     */
    public String obtenerIdsDeIncidentes() {
        ListaEnlazadaSimple<Incidente> incidentes = sgmms.getListaIncidentes(); // Obtener la lista personalizada

        if (incidentes.isEmpty()) {
            return "No hay incidentes registrados.";
        }

        // Convertir ListaEnlazadaSimple a una String con IDs de incidentes
        StringBuilder ids = new StringBuilder("IDs de incidentes disponibles:\n");
        for (int i = 0; i < incidentes.size(); i++) {
            Incidente incidente = incidentes.get(i); // Usar métodos de ListaEnlazadaSimple
            ids.append("- ").append(incidente.getId()).append("\n");
        }
        return ids.toString();
    }

    /**
     * Busca y muestra la mejor ruta disponible en el sistema.
     *
     * @return Una cadena de texto con los detalles de la mejor ruta (incluyendo ID, distancia, tiempo estimado,
     *         punto de inicio y punto final). Si no hay rutas registradas en el sistema, retorna un mensaje
     *         indicando que no hay rutas disponibles.
     */
    public String mostrarMejorRuta() {
        Ruta mejorRuta = sgmms.determinarMejorRuta(); // Llama a determinarMejorRuta()
        if (mejorRuta != null) {
            // Formatea la información de la ruta en un reporte sencillo
            return "Mejor Ruta Encontrada:\n" +
                    "ID: " + mejorRuta.getId() + "\n" +
                    "Distancia: " + mejorRuta.getDistancia() + " km\n" +
                    "Tiempo estimado: " + mejorRuta.getTiempoEstimado() + " minutos\n" +
                    "Punto de Inicio: " + mejorRuta.getPuntoInicio() + "\n" +
                    "Punto de Fin: " + mejorRuta.getPuntoFin();
        } else {
            return "No hay rutas disponibles en el sistema.";
        }
    }

    /**
     * Actualiza el estado de un incidente específico basado en su ID.
     *
     * @param id         ID del incidente a actualizar.
     * @param nuevoEstado Nuevo estado que se asignará al incidente (por ejemplo, "pendiente",
     *                    "en proceso", "resuelto").
     * @return Una cadena de texto confirmando la actualización si el incidente fue encontrado. Si no existe
     *         un incidente con el ID proporcionado, devuelve un mensaje de error.
     */
    public String actualizarEstadoIncidente(String id, String nuevoEstado) {
        try {
            // Buscar el incidente por el ID
            Incidente incidente = sgmms.buscarIncidente(id);

            if (incidente != null) {
                // Actualizar el estado del incidente
                incidente.setEstado(nuevoEstado);

                // Confirmar la actualización
                return "Estado del incidente con ID \"" + id + "\" actualizado a \"" + nuevoEstado + "\".";
            } else {
                // Si el incidente no existe
                return "Incidente con ID \"" + id + "\" no encontrado.";
            }
        } catch (DataNotFoundException e) {
            // Manejar el caso donde no se encuentra el incidente
            return "Error: No se pudo encontrar el incidente con ID \"" + id + "\". " + e.getMessage();
        }
    }

    /**
     * Actualiza el estado de un conductor específico basado en su ID.
     *
     * @param id         ID del conductor a actualizar.
     * @param nuevoEstado Nuevo estado que se asignará al conductor (por ejemplo, "disponible",
     *                    "en ruta").
     * @return Una cadena de texto confirmando la actualización si el conductor fue encontrado. Si no existe
     *         un conductor con el ID proporcionado, devuelve un mensaje de error.
     */
    public String actualizarEstadoConductor(String id, String nuevoEstado) {
        try {
            // Buscar al conductor por el ID
            Conductor conductor = sgmms.buscarConductor(id); // Método existente en tu sistema

            if (conductor != null) {
                // Actualizar el estado del conductor
                conductor.setEstado(nuevoEstado);

                // Confirmar la actualización
                return "Estado del conductor con ID \"" + id + "\" actualizado a \"" + nuevoEstado + "\".";
            } else {
                // Si el conductor no existe
                return "Conductor con ID \"" + id + "\" no encontrado.";
            }
        } catch (DataNotFoundException e) {
            // Manejar el caso donde no se encuentra el conductor
            return "Error: No se pudo encontrar el conductor con ID \"" + id + "\". " + e.getMessage();
        }
    }
    /**
     * Consulta todas las rutas registradas en el sistema y las devuelve en formato de texto.
     *
     * @return Una cadena de texto con los detalles de todas las rutas (incluyendo ID, distancia,
     *         y tiempo estimado). Si no hay rutas registradas, retorna una cadena vacía.
     */
    public String consultarRutasComoTexto() {
        ListaEnlazadaSimple<Ruta> listaRutas = sgmms.getListaRutas();

        // Si no hay rutas, retornar texto vacío
        if (listaRutas.isEmpty()) {
            return "";
        }

        // Construir el texto con los detalles de las rutas
        StringBuilder resultado = new StringBuilder();
        for (Ruta ruta : listaRutas) {
            resultado.append("ID: ").append(ruta.getId())
                    .append(" | Distancia: ").append(ruta.getDistancia())
                    .append(" km | Tiempo: ").append(ruta.getTiempoEstimado()).append(" min\n");
        }
        return resultado.toString();
    }

    /**
     * Obtiene los IDs de todos los conductores registrados en el sistema.
     *
     * @return Una cadena de texto con una lista de los IDs de los conductores registrados, incluyendo
     *         su estado actual. Si no hay conductores, retorna un mensaje indicando que no hay
     *         conductores registrados.
     */
    public String obtenerIdsDeConductores() {
        // Acceder a la lista de conductores desde SGMMS
        ListaEnlazadaSimple<Conductor> listaConductores = sgmms.getListaConductores();

        // Verificar si la lista está vacía
        if (listaConductores.isEmpty()) {
            return "No hay conductores registrados.";
        }

        // Construir una lista de conductores disponibles
        StringBuilder resultado = new StringBuilder("--- Conductores disponibles ---\n");
        for (Conductor conductor : listaConductores) {
            resultado.append("ID: ").append(conductor.getId())
                    .append(", Estado: ").append(conductor.getEstado()).append("\n");
        }

        // Retornar como texto
        return resultado.toString();
    }
}