package model;

/**
 * Class representing a referee in a tournament.
 */
public class Referee {
    private String name;         // Name of the referee
    private String nationality;  // Nationality of the referee
    private RefereeType type;    // Type of referee (Main or Assistant)

    /**
     * Constructs a Referee with the specified name, nationality, and type.
     *
     * @param name        the name of the referee
     * @param nationality the nationality of the referee
     * @param type        the type of the referee (Main or Assistant)
     */
    public Referee(String name, String nationality, RefereeType type) {
        this.name = name;
        this.nationality = nationality;
        this.type = type;
    }

    /**
     * Gets the name of the referee.
     *
     * @return the name of the referee
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the nationality of the referee.
     *
     * @return the nationality of the referee
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Gets the type of the referee.
     *
     * @return the type of the referee (Main or Assistant)
     */
    public RefereeType getType() {
        return type;
    }
}
