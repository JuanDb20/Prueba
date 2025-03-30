package ui;

import model.Controller;
import model.exceptions.JSONFormatException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    private final Controller controller;
    private final Scanner sc;
    private static final String JSON_FILE_PATH = "docs/datos_sgmms.json";

    public Main() {
        controller = new Controller();
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        // Crear una instancia del sistema SGMMS
        Main main = new Main();
        main.start();
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n*** SGMMS - Menú ***");
            System.out.println("1. Registrar ruta");
            System.out.println("2. Registrar incidente");
            System.out.println("3. Registrar persona (Pasajero/Conductor)");
            System.out.println("4. Eliminar persona");
            System.out.println("5. Mostrar incidentes ordenados por fecha");
            System.out.println("6. Mostrar rutas ordenadas por distancia");
            System.out.println("7. Generar reporte");
            System.out.println("8. Exportar/Importar datos JSON");
            System.out.println("9. Buscar incidente por ID");
            System.out.println("10. Buscar conductores por nombre");
            System.out.println("11. Mostrar la mejor ruta");
            System.out.println("12. Actualizar estado de un incidente");
            System.out.println("13. Actualizar estado de un conductor");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1" -> registrarRutaUI();
                case "2" -> registrarIncidenteUI();
                case "3" -> registrarPersonaUI();
                case "4" -> eliminarPersonaUI();
                case "5" -> mostrarIncidentesOrdenadosPorFecha();
                case "6" -> mostrarRutasOrdenadasPorDistancia();
                case "7" -> generarReporteUI();
                case "8" -> menuJSON();
                case "9" -> buscarIncidentePorIdUI();
                case "10" -> buscarConductoresPorNombreUI();
                case "11" -> mostrarMejorRutaUI();
                case "12" -> actualizarEstadoIncidenteUI();
                case "13" -> actualizarEstadoConductorUI();
                case "0" -> running = false;
                default -> System.out.println("Opción no válida.");
            }
        }
        System.out.println("Saliendo... ¡Adiós!");
    }

    private void registrarRutaUI() {
        System.out.println("Registrar nueva ruta:");

        // Validar ID
        String id;
        do {
            System.out.print("Ingrese ID: ");
            id = sc.nextLine();
            if (id.trim().isEmpty()) {
                System.out.println("Error: El ID no puede estar vacío. Por favor, ingrese un ID válido.");
            }
        } while (id.trim().isEmpty());

        // Validar distancia
        double distancia = 0;
        boolean distanciaValida = false;
        do {
            try {
                System.out.print("Ingrese distancia (en km): ");
                distancia = Double.parseDouble(sc.nextLine());
                if (distancia <= 0) {
                    System.out.println("Error: La distancia debe ser mayor a 0. Inténtelo de nuevo.");
                } else {
                    distanciaValida = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un valor numérico válido para la distancia.");
            }
        } while (!distanciaValida);

        // Validar tiempo estimado
        int tiempo = 0;
        boolean tiempoValido = false;
        do {
            try {
                System.out.print("Ingrese tiempo estimado (en minutos): ");
                tiempo = Integer.parseInt(sc.nextLine());
                if (tiempo <= 0) {
                    System.out.println("Error: El tiempo estimado debe ser mayor a 0. Inténtelo de nuevo.");
                } else {
                    tiempoValido = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un valor numérico válido para el tiempo.");
            }
        } while (!tiempoValido);

        // Validar punto de inicio
        String inicio;
        do {
            System.out.print("Ingrese punto de inicio: ");
            inicio = sc.nextLine();
            if (inicio.trim().isEmpty()) {
                System.out.println("Error: El punto de inicio no puede estar vacío. Ingrese un valor válido.");
            }
        } while (inicio.trim().isEmpty());

        // Validar punto final
        String fin;
        do {
            System.out.print("Ingrese punto final: ");
            fin = sc.nextLine();
            if (fin.trim().isEmpty()) {
                System.out.println("Error: El punto final no puede estar vacío. Ingrese un valor válido.");
            }
        } while (fin.trim().isEmpty());

        // Intentar registrar la ruta
        try {
            controller.registrarRuta(id, distancia, tiempo, inicio, fin);
            System.out.println("¡Ruta registrada correctamente!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private void registrarIncidenteUI() {
        System.out.println("Registrar nuevo incidente:");
        System.out.print("Ingrese ID del incidente: ");
        String id = sc.nextLine();
        System.out.print("Ingrese tipo de incidente (ROBO, ACCIDENTE, INCENDIO, OTRO): ");
        String tipoTexto = sc.nextLine();
        System.out.print("Ingrese ubicación: ");
        String ubicacion = sc.nextLine();
        System.out.print("Ingrese fecha y hora (formato: yyyy-MM-dd HH:mm:ss): ");
        Date fechaHora;
        try {
            fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sc.nextLine());
        } catch (ParseException e) {
            System.out.println("Fecha inválida. Use el formato yyyy-MM-dd HH:mm:ss.");
            return;
        }
        System.out.print("Ingrese descripción: ");
        String descripcion = sc.nextLine();
        System.out.print("Ingrese estado (pendiente/en proceso/resuelto): ");
        String estado = sc.nextLine();

        try {
            controller.registrarIncidente(id, tipoTexto, ubicacion, fechaHora, descripcion, estado);
            System.out.println("¡Incidente registrado correctamente!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void registrarPersonaUI() {
        System.out.println("¿Qué desea registrar? (1. Pasajero, 2. Conductor): ");
        String opcion = sc.nextLine();
        if (opcion.equals("1")) { // Registrar pasajero
            System.out.print("Ingrese ID del pasajero: ");
            String id = sc.nextLine();
            System.out.print("Ingrese nombre del pasajero: ");
            String nombre = sc.nextLine();
            System.out.print("Ingrese contacto del pasajero: ");
            String contacto = sc.nextLine();

            // Mostrar las rutas disponibles antes de pedir el ID de la ruta
            System.out.println("--- Rutas Disponibles ---");
            String rutasDisponibles = controller.consultarRutasComoTexto(); // Método que obtendrá y formateará las rutas
            if (rutasDisponibles.isEmpty()) {
                System.out.println("No hay rutas disponibles registradas en el sistema.");
                return; // Salir si no hay rutas
            }
            System.out.println(rutasDisponibles);

            // Solicitar el ID de la ruta asignada
            System.out.print("Ingrese ID de la ruta asignada: ");
            String idRuta = sc.nextLine();

            try {
                controller.registrarPasajero(id, nombre, contacto, idRuta);
                System.out.println("¡Pasajero registrado correctamente!");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } else if (opcion.equals("2")) { // Registrar conductor
            System.out.print("Ingrese ID del conductor: ");
            String id = sc.nextLine();
            System.out.print("Ingrese nombre del conductor: ");
            String nombre = sc.nextLine();
            System.out.print("Ingrese contacto del conductor: ");
            String contacto = sc.nextLine();
            System.out.print("Ingrese vehículo asignado: ");
            String vehiculo = sc.nextLine();
            System.out.print("Ingrese estado del conductor (disponible/en ruta): ");
            String estado = sc.nextLine();

            try {
                controller.registrarConductor(id, nombre, contacto, vehiculo, estado);
                System.out.println("¡Conductor registrado correctamente!");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } else {
            System.out.println("Opción no válida.");
        }
    }

    private void eliminarPersonaUI() {
        String personasRegistradas = controller.obtenerPersonasComoTexto();

        if (personasRegistradas.isEmpty()) {
            System.out.println("No hay personas registradas en el sistema.");
            return;
        }

        System.out.println("Personas registradas:");
        System.out.println(personasRegistradas);

        System.out.print("Ingrese el ID de la persona que desea eliminar: ");
        String id = sc.nextLine();

        if (controller.eliminarPersona(id)) {
            System.out.println("¡Persona eliminada correctamente!");
        } else {
            System.out.println("Error: No se encontró una persona con ese ID.");
        }
    }

    private void mostrarIncidentesOrdenadosPorFecha() {
        System.out.println(controller.consultarIncidentesOrdenadosPorFecha());
    }

    private void mostrarRutasOrdenadasPorDistancia() {
        System.out.println(controller.consultarRutasOrdenadasPorDistancia());
    }

    private void generarReporteUI() {
        System.out.println("*** Reporte del sistema ***");
        System.out.println(controller.generarReporte());
    }
    private void buscarIncidentePorIdUI() {
        System.out.println("\n--- Buscar Incidente por ID ---");

        // Mostrar los IDs de incidentes disponibles
        String idsDisponibles = controller.obtenerIdsDeIncidentes();
        System.out.println(idsDisponibles);

        // Pedir al usuario que ingrese el ID del incidente
        System.out.print("Ingrese el ID del incidente: ");
        String id = sc.nextLine();

        try {
            // Llamar al controlador para buscar el incidente
            String resultado = controller.buscarIncidentePorId(id);

            // Mostrar el resultado devuelto por el controlador
            System.out.println(resultado);
        } catch (Exception e) {
            System.out.println("Error al buscar el incidente: " + e.getMessage());
        }
    }
    private void buscarConductoresPorNombreUI() {
        System.out.println("\n--- Buscar Conductores por Nombre ---");
        System.out.print("Ingrese el nombre del conductor: ");
        String nombre = sc.nextLine();

        try {
            String conductores = controller.buscarConductoresPorNombre(nombre);
            if (conductores.isEmpty()) {
                System.out.println("No se encontraron conductores con el nombre: " + nombre);
            } else {
                System.out.println("Conductores encontrados:");
                System.out.println(conductores);
            }
        } catch (Exception e) {
            System.out.println("Error al buscar conductores: " + e.getMessage());
        }
    }
    private void mostrarMejorRutaUI() {
        System.out.println("\n--- Mostrar Mejor Ruta ---");
        try {
            // Llama al controlador para obtener la información de la mejor ruta
            String resultado = controller.mostrarMejorRuta();
            System.out.println(resultado); // Muestra el resultado en la consola
        } catch (Exception e) {
            System.out.println("Error al determinar la mejor ruta: " + e.getMessage());
        }
    }
    private void actualizarEstadoIncidenteUI() {
        System.out.println("\n--- Actualizar Estado de un Incidente ---");

        // Mostrar los IDs disponibles de incidentes y sus estados actuales
        String idsDisponibles = controller.obtenerIdsDeIncidentes(); // Método existente para obtener incidentes
        System.out.println(idsDisponibles);

        if (idsDisponibles.startsWith("No hay")) {
            // Si no hay incidentes, salimos del método
            return;
        }

        // Pedir ID del incidente
        System.out.print("Ingrese el ID del incidente que quiere actualizar: ");
        String id = sc.nextLine();

        // Mostrar las opciones de estados válidos
        System.out.println("Estados válidos: 'pendiente', 'en proceso', 'resuelto'.");

        String nuevoEstado;
        while (true) {
            // Pedir el nuevo estado
            System.out.print("Ingrese el nuevo estado para el incidente: ");
            nuevoEstado = sc.nextLine().toLowerCase(); // Convertir a minúsculas para facilitar validación

            // Validar el estado ingresado
            if (nuevoEstado.equals("pendiente") || nuevoEstado.equals("en proceso") || nuevoEstado.equals("resuelto")) {
                break; // Si el estado es válido, salimos del bucle
            } else {
                System.out.println("Estado ingresado no válido. Por favor, ingrese un estado válido: 'pendiente', 'en proceso' o 'resuelto'.");
            }
        }

        // Llamar al controller para actualizar el estado
        String resultado = controller.actualizarEstadoIncidente(id, nuevoEstado);

        // Mostrar el resultado
        System.out.println(resultado);
    }
    private void actualizarEstadoConductorUI() {
        System.out.println("\n--- Actualizar Estado de un Conductor ---");

        // Mostrar los conductores disponibles
        String conductoresDisponibles = controller.obtenerIdsDeConductores(); // Método que retorna los conductores
        System.out.println(conductoresDisponibles);

        if (conductoresDisponibles.startsWith("No hay")) {
            // Si no hay conductores, terminamos el método
            return;
        }

        // Pedir el ID del conductor
        System.out.print("Ingrese el ID del conductor que quiere actualizar: ");
        String id = sc.nextLine();

        // Mostrar las opciones de estados válidos
        System.out.println("Estados válidos: 'disponible', 'en ruta'.");

        String nuevoEstado;
        while (true) {
            // Pedir el nuevo estado
            System.out.print("Ingrese el nuevo estado para el conductor: ");
            nuevoEstado = sc.nextLine().toLowerCase(); // Convertir a minúsculas para facilitar validación

            // Validar el estado ingresado
            if (nuevoEstado.equals("disponible") || nuevoEstado.equals("en ruta")) {
                break; // Si el estado es válido, salimos del bucle
            } else {
                System.out.println("Estado ingresado no válido. Por favor, ingrese un estado válido: 'disponible' o 'en ruta'.");
            }
        }

        // Llamar al controller para actualizar el estado
        String resultado = controller.actualizarEstadoConductor(id, nuevoEstado);

        // Mostrar el resultado
        System.out.println(resultado);
    }
    private void menuJSON() {
        System.out.println("¿Qué deseas hacer?");
        System.out.println("1. Exportar datos a JSON");
        System.out.println("2. Importar datos desde JSON");
        System.out.print("Seleccione una opción: ");
        String opcion = sc.nextLine();

        if (opcion.equals("1")) {
            try {
                controller.exportarDatosAJson(JSON_FILE_PATH);
                System.out.println("¡Datos exportados correctamente a " + JSON_FILE_PATH + "!");
            } catch (IOException e) {
                System.err.println("Error al exportar datos: " + e.getMessage());
            }
        } else if (opcion.equals("2")) {
            try {
                controller.importarDatosDesdeJson(JSON_FILE_PATH);
                System.out.println("¡Datos importados correctamente desde " + JSON_FILE_PATH + "!");
            } catch (IOException | JSONFormatException e) {
                System.err.println("Error al importar datos: " + e.getMessage());
            }
        } else {
            System.out.println("Opción no válida.");
        }
    }

}