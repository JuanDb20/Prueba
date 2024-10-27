package model;

import java.util.Date;

/**
 * Represents a match between two teams, including details about the teams, referees, score, and date.
 */
public class Match {
    private Team team1;
    private Team team2;
    private Referee mainReferee;
    private Referee assistantReferee1;
    private Referee assistantReferee2;
    private int scoreTeam1;
    private int scoreTeam2;
    private Date date;

    /**
     * Constructs a Match with the specified teams and date.
     *
     * @param team1 the first team in the match
     * @param team2 the second team in the match
     * @param date  the date of the match
     */
    public Match(Team team1, Team team2, Date date) {
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
        this.scoreTeam1 = -1; // The match has not been played
        this.scoreTeam2 = -1; // The match has not been played
    }

    /**
     * Returns the first team in the match.
     *
     * @return the first team
     */
    public Team getTeam1() {
        return team1;
    }

    /**
     * Returns the second team in the match.
     *
     * @return the second team
     */
    public Team getTeam2() {
        return team2;
    }

    /**
     * Returns the main referee assigned to the match.
     *
     * @return the main referee
     */
    public Referee getMainReferee() {
        return mainReferee;
    }

    /**
     * Returns the first assistant referee assigned to the match.
     *
     * @return the first assistant referee
     */
    public Referee getFirstAssistantReferee() {
        return assistantReferee1;
    }

    /**
     * Returns the second assistant referee assigned to the match.
     *
     * @return the second assistant referee
     */
    public Referee getSecondAssistantReferee() {
        return assistantReferee2;
    }

    /**
     * Sets the main referee for the match.
     *
     * @param referee the main referee to assign to the match
     */
    public void setMainReferee(Referee referee) {
        this.mainReferee = referee;
    }

    /**
     * Sets the first assistant referee for the match.
     *
     * @param referee the first assistant referee to assign to the match
     */
    public void setFirstAssistantReferee(Referee referee) {
        this.assistantReferee1 = referee;
    }

    /**
     * Sets the second assistant referee for the match.
     *
     * @param referee the second assistant referee to assign to the match
     */
    public void setSecondAssistantReferee(Referee referee) {
        this.assistantReferee2 = referee;
    }

    /**
     * Returns the score of the first team.
     *
     * @return the score of the first team
     */
    public int getScoreTeam1() {
        return scoreTeam1;
    }

    /**
     * Returns the score of the second team.
     *
     * @return the score of the second team
     */
    public int getScoreTeam2() {
        return scoreTeam2;
    }

    /**
     * Sets the scores for both teams.
     *
     * @param scoreTeam1 the score of the first team
     * @param scoreTeam2 the score of the second team
     */
    public void setScores(int scoreTeam1, int scoreTeam2) {
        this.scoreTeam1 = scoreTeam1;
        this.scoreTeam2 = scoreTeam2;
    }

    /**
     * Returns the date of the match.
     *
     * @return the date of the match
     */
    public Date getDate() {
        return date;
    }
}