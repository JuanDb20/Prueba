package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Controller for managing a football tournament.
 */
public class TournamentController {
    private List<Team> teams;
    private List<Referee> referees;
    private List<Match> matches;
    private static final int MAX_MAIN_REFEREES = 4;
    private static final int MAX_ASSISTANT_REFEREES = 8;

    /**
     * Constructor for TournamentController.
     * Initializes the lists of teams, referees, and matches.
     */
    public TournamentController() {
        this.teams = new ArrayList<>();
        this.referees = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    /**
     * Adds a team to the tournament.
     *
     * @param name    Name of the team.
     * @param country Country of the team.
     * @param coach   Name of the team's coach.
     * @return true if the team is added successfully, false if the team already exists.
     */
    public boolean addTeam(String name, String country, String coach) {
        // Check if team already exists
        for (Team team : teams) {
            if (team.getName().equalsIgnoreCase(name)) {
                return false;
            }
        }
        teams.add(new Team(name, country, coach));
        return true;
    }

    /**
     * Adds a player to a specific team.
     *
     * @param teamName   Name of the team to which the player will be added.
     * @param playerName Name of the player.
     * @param age        Age of the player.
     * @param position   Position of the player on the field.
     * @return true if the player is added successfully, false if the team is not found
     *         or player limit reached.
     */
    public boolean addPlayerToTeam(String teamName, String playerName, int age, Position position) {
        for (Team team : teams) {
            if (team.getName().equalsIgnoreCase(teamName)) {
                return team.addPlayer(new Player(playerName, age, position));
            }
        }
        return false;
    }

    /**
     * Adds a referee to the tournament.
     *
     * @param name        Name of the referee.
     * @param nationality Nationality of the referee.
     * @param type        Type of referee (MAIN or ASSISTANT).
     * @return true if referee was added successfully, false if maximum limit reached
     */
    public boolean addReferee(String name, String nationality, RefereeType type) {
        // Count current referees of each type
        long mainCount = referees.stream()
                .filter(r -> r.getType() == RefereeType.MAIN)
                .count();
        long assistantCount = referees.stream()
                .filter(r -> r.getType() == RefereeType.ASSISTANT)
                .count();

        // Check if we can add more referees of this type
        if ((type == RefereeType.MAIN && mainCount >= MAX_MAIN_REFEREES) ||
            (type == RefereeType.ASSISTANT && assistantCount >= MAX_ASSISTANT_REFEREES)) {
            return false;
        }

        referees.add(new Referee(name, nationality, type));
        return true;
    }

    /**
     * Creates the fixture of matches between the registered teams.
     */
    public void createFixture() {
        matches.clear(); // Clear existing matches
        if (teams.size() < 2) {
            System.out.println("Not enough teams to create a fixture (minimum 2 teams required).");
            return;
        }

        // Create matches between all teams
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                matches.add(new Match(teams.get(i), teams.get(j), new Date()));
            }
        }
    }

    /**
     * Gets the list of matches.
     *
     * @return List of matches.
     */
    public List<Match> getMatches() {
        return matches;
    }

    /**
     * Gets the list of referees.
     *
     * @return List of referees.
     */
    public List<Referee> getReferees() {
        return referees;
    }

    /**
     * Assigns referees to a specific match.
     *
     * @param matchIndex Index of the match to assign referees to.
     * @return true if referees were successfully assigned, false otherwise.
     */
    public boolean assignRefereeToMatch(int matchIndex) {
        if (matchIndex < 0 || matchIndex >= matches.size()) {
            return false;
        }

        Match match = matches.get(matchIndex);
        
        // Check if match already has referees assigned
        if (match.getMainReferee() != null) {
            System.out.println("This match already has referees assigned.");
            return false;
        }

        // Get available referees
        List<Referee> availableMainReferees = new ArrayList<>();
        List<Referee> availableAssistantReferees = new ArrayList<>();

        for (Referee referee : referees) {
            if (referee.getType() == RefereeType.MAIN) {
                availableMainReferees.add(referee);
            } else {
                availableAssistantReferees.add(referee);
            }
        }

        // Check if we have enough referees
        if (availableMainReferees.isEmpty() || availableAssistantReferees.size() < 2) {
            System.out.println("Not enough referees available. Need 1 main and 2 assistant referees.");
            return false;
        }

        // Randomly select referees
        Random random = new Random();
        
        // Assign main referee
        Referee mainReferee = availableMainReferees.get(random.nextInt(availableMainReferees.size()));
        match.setMainReferee(mainReferee);

        // Assign assistant referees
        Referee assistant1 = availableAssistantReferees.get(random.nextInt(availableAssistantReferees.size()));
        match.setFirstAssistantReferee(assistant1);
        
        availableAssistantReferees.remove(assistant1);
        Referee assistant2 = availableAssistantReferees.get(random.nextInt(availableAssistantReferees.size()));
        match.setSecondAssistantReferee(assistant2);

        System.out.println("Referees assigned successfully:");
        System.out.println("Main Referee: " + mainReferee.getName());
        System.out.println("Assistant 1: " + assistant1.getName());
        System.out.println("Assistant 2: " + assistant2.getName());

        return true;
    }

    /**
     * Records the result of a match.
     *
     * @param matchIndex Index of the match to record the result for.
     * @param scoreTeam1 Score of team 1.
     * @param scoreTeam2 Score of team 2.
     * @return true if the result was recorded successfully, false otherwise.
     */
    public boolean recordMatchResult(int matchIndex, int scoreTeam1, int scoreTeam2) {
        if (matchIndex < 0 || matchIndex >= matches.size()) {
            return false;
        }

        Match match = matches.get(matchIndex);
        
        // Verify that referees have been assigned before recording the result
        if (match.getMainReferee() == null || 
            match.getFirstAssistantReferee() == null || 
            match.getSecondAssistantReferee() == null) {
            System.out.println("Cannot record result: Referees have not been assigned to this match.");
            return false;
        }

        // Verify that scores are not negative
        if (scoreTeam1 < 0 || scoreTeam2 < 0) {
            System.out.println("Scores cannot be negative.");
            return false ;
        }

        match.setScoreTeam1(scoreTeam1);
        match.setScoreTeam2(scoreTeam2);

        System.out.println("Match result recorded successfully:");
        System.out.println("Match " + matchIndex + ": " + match.getTeam1().getName() + " vs " + match.getTeam2().getName());
        System.out.println("Score: " + scoreTeam1 + " - " + scoreTeam2);

        return true;
    }

    /**
     * Preloads data for the tournament.
     */
    public void preloadData() {
        // Add some sample teams

//Group A
addTeam("FC Barcelona", "Spain", "Xavi Hernandez");
addTeam("Manchester City", "England", "Pep Guardiola");
addTeam("Bayern Munich", "Germany", "Thomas Tuchel");
addTeam("Paris Saint-Germain", "France", "Luis Enrique");
//Group B
addTeam("CF Real Madrid", "Spain", "Carlo Anchelloti");
addTeam("Atletico Madrid", "Spain", "Cholo Simeone");
addTeam("Atletico Nacional", "Colombia", "David Gomez");
addTeam("Ajax Amsterdam", "Netherlands", "Gerrit van der Poel");
// Add some sample players
addPlayerToTeam("FC Barcelona", "Marc-André ter Stegen", 31, Position.GOALKEEPER);
addPlayerToTeam("FC Barcelona", "Jules Koundé", 24, Position.DEFENDER);
addPlayerToTeam("Manchester City", "Kevin De Bruyne", 32, Position.MIDFIELDER);
addPlayerToTeam("Manchester City", "Erling Haaland", 23, Position.FORWARD);
addPlayerToTeam("Bayern Munich", "Manuel Neuer", 37, Position.GOALKEEPER);
addPlayerToTeam("Bayern Munich", "Matthijs de Ligt", 24, Position.DEFENDER);
addPlayerToTeam("Paris Saint-Germain", "Marco Verratti", 31, Position.MIDFIELDER);
addPlayerToTeam("Paris Saint-Germain", "Kylian Mbappé", 25, Position.FORWARD);

// Add some sample referees
addReferee("Pierluigi Collina", "Italy", RefereeType.MAIN);
addReferee("Howard Webb", "England", RefereeType.ASSISTANT);
addReferee("Mark Clattenburg", "England", RefereeType.ASSISTANT);
addReferee("Néstor Pitana", "Argentina", RefereeType.MAIN);
addReferee("Bibiana Steinhaus", "Germany", RefereeType.ASSISTANT);
addReferee("Fernando Rapallini", "Argentina", RefereeType.ASSISTANT);
addReferee("Mateu Lahoz", "Spain", RefereeType.MAIN);
addReferee("Eduardo González", "Spain", RefereeType.ASSISTANT);
addReferee ("André Naldo", "France", RefereeType.ASSISTANT);
addReferee ("José Antonio Rodriguez", "Spain", RefereeType.ASSISTANT);
addReferee ("Luciano Gazzola", "Italy", RefereeType.ASSISTANT);
    }
}