package model;

/**
 * Represents a player in a team, including their name, age, position, and goals scored.
 */
public class Player {
    private String name;
    private int age;
    private Position position;
    private int goalsScored; // Field to keep track of goals scored by the player

    /**
     * Constructs a Player with the specified name, age, and position.
     *
     * @param name     the name of the player
     * @param age      the age of the player
     * @param position the position of the player on the field
     */
    public Player(String name, int age, Position position) {
        this.name = name;
        this.age = age;
        this.position = position;
        this.goalsScored = 0; // Initialize goals scored to 0 by default
    }

    /**
     * Returns the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the age of the player.
     *
     * @return the age of the player
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the position of the player.
     *
     * @return the position of the player
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Returns the number of goals scored by the player.
     *
     * @return the number of goals scored
     */
    public int getGoalsScored() {
        return goalsScored;
    }

    /**
     * Sets the number of goals scored by the player.
     *
     * @param goalsScored the number of goals to set
     */
    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }
}
