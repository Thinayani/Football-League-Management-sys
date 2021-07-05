package sample;


import java.util.Date;
import java.util.Objects;

public class MatchPlayed {

    // Initializing variables
    private FootballClub teamA;
    private FootballClub teamB;
    private int teamAScore;
    private int teamBScore;
    private Date date;

    // Get team A name
    public FootballClub getTeamA() {
        return teamA;
    }
    // Set team A name
    public void setTeamA(FootballClub teamA) {
        this.teamA = teamA;
    }

    // Get team B name
    public FootballClub getTeamB() {
        return teamB;
    }
    // Set team B name
    public void setTeamB(FootballClub teamB) {
        this.teamB = teamB;
    }

    // Get team A score
    public int getTeamAScore() {
        return teamAScore;
    }
    // Set team A score
    public void setTeamAScore(int teamAScore) {
        this.teamAScore = teamAScore;
    }
    // Get team B score
    public int getTeamBScore() {
        return teamBScore;
    }
    // Set team A score
    public void setTeamBScore(int teamBScore) {
        this.teamBScore = teamBScore;
    }
    // Get date
    public Date getDate() {
        return date;
    }
    // Set date
    public void setDate(Date date) {
        this.date = date;
    }

    // Create toString method
    @Override
    public String toString() {
        return "MatchPlayed{" +
                "teamA=" + teamA +
                ", teamB=" + teamB +
                ", teamAScore=" + teamAScore +
                ", teamBScore=" + teamBScore +
                ", date=" + date +
                '}';
    }

    // Create equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchPlayed)) return false;
        MatchPlayed that = (MatchPlayed) o;
        return teamAScore == that.teamAScore && teamBScore == that.teamBScore && Objects.equals(teamA, that.teamA) && Objects.equals(teamB, that.teamB) && Objects.equals(date, that.date);
    }

    // Create hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(teamA, teamB, teamAScore, teamBScore, date);
    }
}
