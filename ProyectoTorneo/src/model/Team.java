package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a football team.
 */
public class Team {
    private String name;         // Name of the team
    private String country;      // Country of the team
    private String coach;        // Coach of the team
    private List<Player> players; // List of players in the team

    /**
     * Constructor for creating a Team instance.
     *
     * @param name    Name of the team.
     * @param country Country of the team.
     * @param coach   Name of the team's coach.
     */
    public Team(String name, String country, String coach) {
        this.name = name;
        this.country = country;
        this.coach = coach;
        this.players = new ArrayList<>();
    }

    /**
     * Gets the name of the team.
     *
     * @return The name of the team.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the country of the team.
     *
     * @return The country of the team.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Gets the name of the team's coach.
     *
     * @return The name of the coach.
     */
    public String getCoach() {
        return coach;
    }

    /**
     * Adds a player to the team.
     *
     * @param player The player to be added.
     * @return true if the player was added successfully, false if the team already has 11 players.
     */
    public boolean addPlayer(Player player) {
        if (players.size() < 11) {
            players.add(player);
            return true; // Player added successfully
        }
        return false; // Maximum of 11 players reached
    }
}
