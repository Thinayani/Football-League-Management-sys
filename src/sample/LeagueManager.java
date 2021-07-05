package sample;

import java.io.IOException;
public interface LeagueManager {

    void createClub(FootballClub club);
    void removeClub(String remove);
    void displayClub(String dClub);
    void displayTable();
    void retrieveData(String saveFile) throws IOException, ClassNotFoundException;
    void saveFile(String saveFile)throws IOException;
    void addPlayedMatch();
    void viewTable();
//    void loadInfo();
}