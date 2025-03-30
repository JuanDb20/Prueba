package model.exceptions;

/**
 * Excepción personalizada que se lanza cuando no se encuentra un dato específico en el sistema.
 */
public class DataNotFoundException extends Exception {

    /**
     * Crea una nueva instancia de DataNotFoundException con el mensaje de error especificado.
     *
     * @param message El mensaje que describe el problema relacionado con la falta de datos.
     */
    public DataNotFoundException(String message) {
        super(message);
    }
}