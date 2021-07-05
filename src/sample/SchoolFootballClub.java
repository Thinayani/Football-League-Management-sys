package sample;

import java.util.Objects;

public class SchoolFootballClub extends FootballClub{

    // Initializing variables
    private String schoolName;

    // Create argument constructor
    public SchoolFootballClub(String clubName, String clubLocation, String clubOwner, String schoolName) {
        super(clubName, clubLocation, clubOwner);
        this.schoolName = schoolName;
    }

    // Get school name
    public String getSchoolName() {
        return schoolName;
    }
    // Set school name
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    // Create toString method
    @Override
    public String toString() {
        return "SchoolFootballClub{" +
                "schoolName='" + schoolName + '\'' +
                '}';
    }

    // Create equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SchoolFootballClub that = (SchoolFootballClub) o;
        return Objects.equals(schoolName, that.schoolName);

    }

    // Create hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), schoolName);
    }
}
