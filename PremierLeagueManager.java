package sample;

import javafx.application.Application;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PremierLeagueManager implements LeagueManager {

    // Create arraylist for football club
    private List<FootballClub> clubList = new ArrayList<>();
    // Create arraylist for match played
    private static List<MatchPlayed> matches = new ArrayList<>();

    public List<FootballClub> arrayList(){
        return clubList;
    }
    public  List<MatchPlayed> matchArray(){
        return matches;
    }

    // Create club
    @Override
    public void createClub(FootballClub club) {

        for (FootballClub club1 : clubList) {
            if (club.equals(club1)) {
                System.out.println("This Club already exists.");
                return;
            }
        }
        clubList.add(club);
        System.out.println("\n"+clubList);
    }

    // Remove club
    @Override
    public void removeClub(String remove) {

        if (clubList.isEmpty()) {
            System.out.println("Data file is empty");
        } else {
            for (FootballClub footballClub : clubList) {
                if (footballClub.getClubName().equals(remove)) {
                    clubList.remove(footballClub);
                    System.out.println("\n" + footballClub.getClubName() + " has been successfully removed.");
                    break;
                }else{
                    System.out.println("\nInvalid club!");
                }

            }
        }
    }

    // Display club
    @Override
    public void displayClub(String dClub) {
        if (clubList.size() == 0) {
            System.out.println("\nList is empty");
        } for (FootballClub footballClub : clubList) {
            if(footballClub.getClubName().equals(dClub)) {
                System.out.println("____________________________________________________________________________");
                System.out.println(footballClub.toString());
                System.out.println("____________________________________________________________________________");
            }else{
                System.out.println("The club is not in the list.");
            }
        }
    }

    // Display table
    @Override
    public void displayTable() {
        if (clubList.isEmpty()) {
            System.out.println("\nTable is empty!");
        } else {
            // Calling custom comparator
            Collections.sort(clubList, new CustomComparator());
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%10s %10s %10s %10s %10s %10s %20s %20s %20s %20s %n", "Name", "Owner", "Wins", "Draws", "Defeats", "Points", "Received Goals", "Scored Goals", "Goal difference", "Matches Played");
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (FootballClub footballClub : clubList) {
                System.out.printf("%10s %10s %8s %10s %10s %10s %15s %20s %20s %17s %n",
                        footballClub.getClubName(), footballClub.getClubOwner(), footballClub.getNumberOfWins(), footballClub.getNumberOfDraws(), footballClub.getNumberOfDefeats(), footballClub.getCurrentNumberOfPoints(), footballClub.getNumberOfReceivedGoals(), footballClub.getNumberOfScoredGoals(), (footballClub.getNumberOfReceivedGoals()-footballClub.getNumberOfScoredGoals()), footballClub.getNumberOfMatchesPlayed());
                System.out.println();
            }
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    // Retrieve data
    public void retrieveData(String PremierLeague) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(PremierLeague);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        for (;;) {
            try {
                clubList.add((FootballClub) objectInputStream.readObject());
            } catch (EOFException e) {
                break;
            }
        }
        System.out.println("--------- Sports clubs have been retrieved successfully ---------");
    }

    // Save file
    @Override
    public void saveFile(String saveFile) throws IOException{

        System.out.println(clubList);
        FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        for(FootballClub club : clubList) {
            objectOutputStream.writeObject(club);
        }
        System.out.println("\nClubs have been saved successfully!");
    }

    // Add played match
    @Override
    public void addPlayedMatch() {

        System.out.println("Enter date (format mm-dd-yyyy): ");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Date date;
        try{
            date = new SimpleDateFormat("MM-dd-yyyy").parse(line);
        }catch (ParseException e){
            System.out.println("You have to enter the date in format mm-dd-yyyy");
            return;
        }

        // Get the home team from the user
        System.out.println("Enter the home team: ");
        line = scanner.nextLine();
        FootballClub homeTeam = null;
        for (FootballClub club : clubList){
            if (club.getClubName().equals(line)){
                homeTeam = club;
            }
        }if (homeTeam == null){
            System.out.println("No such club in league!");
            return;
        }

        // Get the away team from the user
        System.out.println("Enter the away team: ");
        line = scanner.nextLine();
        FootballClub awayTeam = null;
        for (FootballClub club : clubList){
            if (club.getClubName().equals(line)){
                awayTeam = club;
            }
        }if (awayTeam == null){
            System.out.println("No such club in league!");
            return;
        }

        // Get the home team goals
        System.out.println("Enter home team goals: ");
        line = scanner.nextLine();
        int homeGoals = -1;
        try{
            homeGoals = Integer.parseInt(line);
        }catch (Exception e){
            System.out.println("Please enter an integer");
        }
        if(homeGoals == -1){
            System.out.println("You have to enter the number of goals.");
        }

        // Get the away team goals
        System.out.println("Enter away team goals: ");
        line = scanner.nextLine();
        int awayGoals = -1;
        try{
            awayGoals = Integer.parseInt(line);
        }catch (Exception e){
            System.out.println("Please enter an integer");
        }
        if(awayGoals == -1){
            System.out.println("You have to enter the number of goals.");
        }

        MatchPlayed matchPlayed = new MatchPlayed();
        // Set the variables in match played to the respective teams
        matchPlayed.setDate(date);
        matchPlayed.setTeamA(homeTeam);
        matchPlayed.setTeamB(awayTeam);
        matchPlayed.setTeamAScore(awayGoals);
        matchPlayed.setTeamBScore(homeGoals);
        // Add data in match played to the arraylist matches
        matches.add(matchPlayed);
        homeTeam.setNumberOfScoredGoals(homeTeam.getNumberOfScoredGoals()+homeGoals);
        awayTeam.setNumberOfScoredGoals(awayTeam.getNumberOfScoredGoals()+awayGoals);
        homeTeam.setNumberOfReceivedGoals(homeTeam.getNumberOfReceivedGoals()+awayGoals);
        awayTeam.setNumberOfReceivedGoals(awayTeam.getNumberOfReceivedGoals()+homeGoals);
        homeTeam.setNumberOfMatchesPlayed(homeTeam.getNumberOfMatchesPlayed()+1);
        awayTeam.setNumberOfMatchesPlayed(awayTeam.getNumberOfMatchesPlayed()+1);

        if(homeGoals > awayGoals){
            homeTeam.setCurrentNumberOfPoints(homeTeam.getCurrentNumberOfPoints() + 1);
            homeTeam.setNumberOfWins(homeTeam.getNumberOfWins() + 1);
            awayTeam.setNumberOfDefeats(awayTeam.getNumberOfDefeats() + 1);
        }else if(homeGoals < awayGoals){
            awayTeam.setCurrentNumberOfPoints(awayTeam.getCurrentNumberOfPoints() + 1);
            awayTeam.setNumberOfWins(awayTeam.getNumberOfWins() + 1);
            homeTeam.setNumberOfDefeats(homeTeam.getNumberOfDefeats() + 1);
        }else{
            homeTeam.setCurrentNumberOfPoints(homeTeam.getCurrentNumberOfPoints() + 1);
            awayTeam.setCurrentNumberOfPoints(awayTeam.getCurrentNumberOfPoints() + 1);
            homeTeam.setNumberOfDraws(homeTeam.getNumberOfDraws() + 1);
            awayTeam.setNumberOfDraws(awayTeam.getNumberOfDraws() + 1);

        }
    }


    // View Table
    @Override
    public void viewTable(){
        Application.launch(LeagueTable.class);

    }
}
