package model;

/**
 * Clase que genera reportes detallados sobre rutas, incidentes y personas en el sistema.
 * Utiliza la información proporcionada por una instancia de {@link SGMMS}.
 */
public class Reporte {

    /**
     * Constructor vacío para crear un objeto {@code Reporte}.
     */
    public Reporte() {
    }

    /**
     * Genera un reporte detallado basado en la información del sistema {@link SGMMS}.
     *
     * El reporte incluye:
     * <ul>
     *     <li>Reporte de rutas: ID, distancia y tiempo estimado.</li>
     *     <li>Reporte de incidentes: ID, tipo y fecha/hora.</li>
     *     <li>Reporte de personas: ID y nombre de cada persona registrada.</li>
     * </ul>
     *
     * @param sgmms El sistema de gestión (SGMMS) del cual se extraerá la información para generar el reporte.
     *              Este sistema debe proporcionar listas de rutas, incidentes y personas.
     * @return Una cadena de texto que contiene el reporte completo.
     */
    public String generarReporte(SGMMS sgmms) {
        StringBuilder reporte = new StringBuilder();
        reporte.append("Reporte de Rutas:\n");
        for (int i = 0; i < sgmms.getListaRutas().size(); i++) {
            Ruta r = sgmms.getListaRutas().get(i);
            reporte.append("ID: ").append(r.getId())
                    .append(" | Distancia: ").append(r.getDistancia())
                    .append(" km | Tiempo: ").append(r.getTiempoEstimado()).append(" min\n");
        }
        reporte.append("\nReporte de Incidentes:\n");
        for (int i = 0; i < sgmms.getListaIncidentes().size(); i++) {
            Incidente inc = sgmms.getListaIncidentes().get(i);
            reporte.append("ID: ").append(inc.getId())
                    .append(" | Tipo: ").append(inc.getTipo())
                    .append(" | Fecha: ").append(inc.getFechaHora()).append("\n");
        }
        reporte.append("\nReporte de Personas:\n");
        for (int i = 0; i < sgmms.getListaPersonas().size(); i++) {
            Persona p = sgmms.getListaPersonas().get(i);
            reporte.append("ID: ").append(p.getId())
                    .append(" | Nombre: ").append(p.getNombre()).append("\n");
        }
        return reporte.toString();
    }
}