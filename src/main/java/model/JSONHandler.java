package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import model.exceptions.JSONFormatException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase encargada de realizar operaciones relacionadas con la lectura, escritura y manipulación de archivos JSON.
 * Utiliza la biblioteca Gson para procesar datos de formato JSON.
 */
public class JSONHandler {

    private final Gson gson;

    /**
     * Constructor que inicializa un objeto {@link Gson} configurado con impresión legible (pretty printing).
     */
    public JSONHandler() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Lee el contenido de un archivo en una ubicación específica y lo devuelve como una cadena de texto.
     *
     * @param ruta Ruta del archivo a leer.
     * @return Contenido del archivo en formato de texto.
     * @throws IOException Si ocurre un error durante la lectura del archivo.
     */
    public String leerArchivo(String ruta) throws IOException {
        FileReader reader = new FileReader(ruta);
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = reader.read()) != -1) {
            sb.append((char) c);
        }
        reader.close();
        return sb.toString();
    }

    /**
     * Convierte un texto JSON a un objeto Java de la clase especificada.
     *
     * @param json  Cadena JSON que se desea convertir.
     * @param clazz Clase del objeto al que se mapeará el JSON.
     * @param <T>   Tipo genérico del objeto resultante.
     * @return Objeto del tipo especificado, construido a partir del JSON.
     * @throws JSONFormatException Si el JSON tiene un formato inválido o no es compatible con la clase especificada.
     */
    public <T> T parseJSON(String json, Class<T> clazz) throws JSONFormatException {
        try {
            return gson.fromJson(json, clazz);
        } catch (JsonSyntaxException e) {
            throw new JSONFormatException("Error al parsear JSON: " + e.getMessage());
        }
    }

    /**
     * Escribe un objeto Java en un archivo como un texto JSON.
     *
     * @param ruta Ruta del archivo donde se almacenará el JSON.
     * @param data Objeto a ser convertido y escrito como JSON en el archivo.
     * @throws IOException Si ocurre un error durante la escritura del archivo.
     */
    public void escribirArchivo(String ruta, Object data) throws IOException {
        FileWriter writer = new FileWriter(ruta);
        gson.toJson(data, writer);
        writer.flush();
        writer.close();
    }
}