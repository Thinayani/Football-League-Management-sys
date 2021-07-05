package sample;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public abstract class SportsClub implements Comparable<FootballClub>, Serializable {

    // Initializing variables
    private Date date;
    private String clubName;
    private String clubLocation;
    private String clubOwner;

    // Create argument constructor
    public SportsClub(String clubName, String clubLocation, String clubOwner) {
        super();
        this.clubName = clubName;
        this.clubLocation = clubLocation;
        this.clubOwner = clubOwner;
    }

    // Get date
    public Date getDate() {
        return date;
    }
    // Set date
    public void setDate(Date date) {
        this.date = date;
    }

    // Get club name
    public String getClubName() {
        return clubName;
    }
    // Set club name
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    // Get club location
    public String getClubLocation() {
        return clubLocation;
    }
    // Set club location
    public void setClubLocation(String clubLocation) {
        this.clubLocation = clubLocation;
    }

    // Get club owner
    public String getClubOwner() {
        return clubOwner;
    }
    // Set club owner
    public void setClubOwner(String clubOwner) {
        this.clubOwner = clubOwner;
    }

    // Create toString method
    @Override
    public String toString() {
        return "SportsClub{" +
                "date=" + date +
                ", clubName='" + clubName + '\'' +
                ", clubLocation='" + clubLocation + '\'' +
                ", clubOwner='" + clubOwner + '\'' +
                '}';
    }

    // Create equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SportsClub)) return false;
        SportsClub club = (SportsClub) o;
        return Objects.equals(date, club.date) && Objects.equals(clubName, club.clubName) && Objects.equals(clubLocation, club.clubLocation) && Objects.equals(clubOwner, club.clubOwner);
    }

    // Create hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(date, clubName, clubLocation, clubOwner);
    }

    // Create compareTo method
    @Override
    public int compareTo(FootballClub o) {
        return 0;
    }
}

