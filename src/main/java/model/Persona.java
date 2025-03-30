package model;

/**
 * Clase abstracta que representa a una persona en el sistema.
 * Sirve como base para crear otras clases que hereden sus propiedades.
 */
public abstract class Persona {

    /**
     * Identificador único de la persona.
     */
    protected String id;

    /**
     * Nombre de la persona.
     */
    protected String nombre;

    /**
     * Información de contacto de la persona.
     */
    protected String contacto;

    /**
     * Constructor para inicializar una persona con sus datos básicos.
     *
     * @param id       Identificador único de la persona.
     * @param nombre   Nombre de la persona.
     * @param contacto Información de contacto de la persona (por ejemplo, número de teléfono o correo electrónico).
     */
    public Persona(String id, String nombre, String contacto) {
        this.id = id;
        this.nombre = nombre;
        this.contacto = contacto;
    }

    /**
     * Obtiene el identificador único de la persona.
     *
     * @return El identificador de la persona.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene el nombre de la persona.
     *
     * @return El nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la información de contacto de la persona.
     *
     * @return La información de contacto de la persona.
     */
    public String getContacto() {
        return contacto;
    }
}
