package sample;

import java.util.Objects;

public class UniversityFootballClub extends FootballClub{

    // Initializing variables
    private String universityName;

    // Create argument constructor
    public UniversityFootballClub(String clubName, String clubLocation, String clubOwner, String universityName) {
        super(clubName, clubLocation, clubOwner);
        this.universityName = universityName;
    }

    // Get school name
    public String getUniversityName() {
        return universityName;
    }
    // Set school name
    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }


    // Create toString method
    @Override
    public String toString() {
        return "UniversityFootballClub{" +
                "universityName='" + universityName + '\'' +
                '}';
    }

    // Create equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UniversityFootballClub)) return false;
        if (!super.equals(o)) return false;
        UniversityFootballClub that = (UniversityFootballClub) o;
        return Objects.equals(universityName, that.universityName);
    }

    // Create hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), universityName);
    }
}
