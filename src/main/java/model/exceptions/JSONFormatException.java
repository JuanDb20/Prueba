package model.exceptions;

/**
 * Excepción personalizada que se lanza cuando se encuentra un error en el formato JSON.
 */
public class JSONFormatException extends Exception {

    /**
     * Crea una nueva instancia de JSONFormatException con el mensaje de error especificado.
     *
     * @param message El mensaje que describe el error de formato JSON.
     */
    public JSONFormatException(String message) {
        super(message);
    }
}