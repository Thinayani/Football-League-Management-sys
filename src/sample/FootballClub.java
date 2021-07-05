package sample;

import java.io.Serializable;
import java.util.Objects;

public class FootballClub extends SportsClub implements Comparable<FootballClub>, Serializable {

    // Initializing variables
    private int numberOfWins;
    private int numberOfDraws;
    private int numberOfDefeats;
    private int numberOfScoredGoals;
    private int numberOfReceivedGoals;
    private int numberOfMatchesPlayed;
    private int currentNumberOfPoints;

    // Create argument constructor
    public FootballClub( String clubName, String clubLocation, String clubOwner,int numberOfWins, int numberOfDraws, int numberOfDefeats, int numberOfScoredGoals, int numberOfReceivedGoals, int numberOfMatchesPlayed, int currentNumberOfPoints) {
        super(clubName, clubLocation, clubOwner);
        this.numberOfWins = numberOfWins;
        this.numberOfDraws = numberOfDraws;
        this.numberOfDefeats = numberOfDefeats;
        this.numberOfMatchesPlayed = numberOfMatchesPlayed;
        this.currentNumberOfPoints = currentNumberOfPoints;
        this.numberOfScoredGoals = numberOfScoredGoals;
        this.numberOfReceivedGoals = numberOfReceivedGoals;
    }
    public FootballClub(String clubName, String clubLocation, String clubOwner) {
        super(clubName, clubLocation, clubOwner);
    }

    // Get the number of wins
    public int getNumberOfWins() {
        return numberOfWins;
    }
    // Set the number of wins
    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    // Get the number of draws
    public int getNumberOfDraws() {
        return numberOfDraws;
    }
    // Set the number of draws
    public void setNumberOfDraws(int numberOfDraws) {
        this.numberOfDraws = numberOfDraws;
    }

    // Get the number of defeats
    public int getNumberOfDefeats() {
        return numberOfDefeats;
    }
    // Set the number of defeats
    public void setNumberOfDefeats(int numberOfDefeats) {
        this.numberOfDefeats = numberOfDefeats;
    }

    // Get the number of goals scored
    public int getNumberOfScoredGoals() {
        return numberOfScoredGoals;
    }
    // Set the number of goals scored
    public void setNumberOfScoredGoals(int numberOfScoredGoals) {
        this.numberOfScoredGoals = numberOfScoredGoals;
    }

    // Get the number of received goals
    public int getNumberOfReceivedGoals() {
        return numberOfReceivedGoals;
    }
    // Set the number of received goals
    public void setNumberOfReceivedGoals(int numberOfReceivedGoals) {
        this.numberOfReceivedGoals = numberOfReceivedGoals;
    }

    // Get the number of matches played
    public int getNumberOfMatchesPlayed() {
        return numberOfMatchesPlayed;
    }
    // Set the number of matches played
    public void setNumberOfMatchesPlayed(int numberOfMatchesPlayed) {
        this.numberOfMatchesPlayed = numberOfMatchesPlayed;
    }

    // Get the current number of points
    public int getCurrentNumberOfPoints() {
        return currentNumberOfPoints;
    }
    // Set the current number of points
    public void setCurrentNumberOfPoints(int currentNumberOfPoints) {
        this.currentNumberOfPoints = currentNumberOfPoints;
    }

    // Create toString method
    @Override
    public String toString() {
        return "FootballClub " +getClubName()+ "\n" +
                "\nNumberOfWins          = " + numberOfWins +
                "\nNumberOfDraws         = " + numberOfDraws +
                "\nNumberOfDefeats       = " + numberOfDefeats +
                "\nNumberOfReceivedGoals = " + numberOfReceivedGoals +
                "\nNumberOfScoredGoals   = " + numberOfScoredGoals +
                "\nNumberOfMatchesPlayed = " + numberOfMatchesPlayed +
                "\nCurrentNumberOfPoints = " + currentNumberOfPoints ;
    }

    // create equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FootballClub that = (FootballClub) o;
        return numberOfWins == that.numberOfWins &&
                numberOfDraws == that.numberOfDraws &&
                numberOfDefeats == that.numberOfDefeats &&
                numberOfScoredGoals == that.numberOfScoredGoals &&
                numberOfReceivedGoals == that.numberOfReceivedGoals &&
                numberOfMatchesPlayed == that.numberOfMatchesPlayed &&
                currentNumberOfPoints == that.currentNumberOfPoints;
    }

    // Create hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(numberOfWins, numberOfDraws, numberOfDefeats, numberOfReceivedGoals, numberOfScoredGoals, numberOfMatchesPlayed, currentNumberOfPoints);
    }


}
