package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.*;

public class LeagueTable extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        ArrayList<MatchPlayed> matchesArray = new ArrayList<>();

        PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
        List<FootballClub> clubList=premierLeagueManager.arrayList();
        Collections.sort(clubList, new CustomComparator());

        try {
            FileInputStream fileInputStream = new FileInputStream("saveFile.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            for (;;) {
                try {
                    clubList.add((FootballClub) objectInputStream.readObject());
                }catch (Exception e){
                    break;
                }
            }
        }catch (EOFException ignored){ }

        // Sort the clubList
        Comparator tableViewComparator = Comparator.comparing(FootballClub::getCurrentNumberOfPoints).thenComparing(FootballClub::getNumberOfScoredGoals);
        Collections.sort(clubList,tableViewComparator);
        Collections.reverse(clubList);

        Pane mainPane = new Pane();

        // Create league table
        TableView<FootballClub> leagueTable=new TableView<FootballClub>();
        leagueTable.setLayoutX(60);
        leagueTable.setLayoutY(10);
        leagueTable.setPrefSize(1150,400);
        leagueTable.setStyle("-fx-background-color:  #c8dddd;-fx-font-weight: bold");

        TableColumn<FootballClub,String> clubName=new TableColumn<FootballClub,String>("Club Name");
        clubName.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        clubName.setStyle("-fx-background-color:  #c8dddd");
        clubName.setPrefWidth(115);

        TableColumn<FootballClub,String> location=new TableColumn<FootballClub,String>("Location");
        location.setCellValueFactory(new PropertyValueFactory<>("clubLocation"));
        location.setPrefWidth(115);

        TableColumn<FootballClub,String> ownerName =new TableColumn<FootballClub,String>("Owner Name");
        ownerName.setCellValueFactory(new PropertyValueFactory<>("clubOwner"));
        ownerName.setPrefWidth(115);

        TableColumn<FootballClub,Integer> wins = new TableColumn<FootballClub,Integer>("Wins");
        wins.setCellValueFactory(new PropertyValueFactory<>("numberOfWins"));
        wins.setPrefWidth(115);

        TableColumn<FootballClub,Integer> draws = new TableColumn<FootballClub,Integer>("Draws");
        draws.setCellValueFactory(new PropertyValueFactory<>("numberOfDraws"));
        draws.setPrefWidth(115);

        TableColumn<FootballClub,Integer> defeats =new TableColumn<FootballClub,Integer>("Defeats");
        defeats.setCellValueFactory(new PropertyValueFactory<>("numberOfDefeats"));
        defeats.setPrefWidth(115);

        TableColumn<FootballClub,Integer> goalRec =new TableColumn<FootballClub,Integer>("Goals Received");
        goalRec.setCellValueFactory(new PropertyValueFactory<>("numberOfReceivedGoals"));
        goalRec.setPrefWidth(115);

        TableColumn<FootballClub,Integer> goalSco=new TableColumn<FootballClub,Integer>("Goals Scored");
        goalSco.setCellValueFactory(new PropertyValueFactory<>("numberOfScoredGoals"));
        goalSco.setPrefWidth(115);

        TableColumn<FootballClub,Integer> points = new TableColumn<FootballClub,Integer>("Points");
        points.setCellValueFactory(new PropertyValueFactory<>("currentNumberOfPoints"));
        points.setPrefWidth(115);

        TableColumn<FootballClub,Integer> numOfMatches = new TableColumn<FootballClub,Integer>("Played Matches");
        numOfMatches.setCellValueFactory(new PropertyValueFactory<>("numberOfMatchesPlayed"));
        numOfMatches.setPrefWidth(115);
        leagueTable.getColumns().addAll(clubName,location, ownerName, wins, draws , defeats, goalRec, goalSco, points, numOfMatches);

        //Adding Club details saved in the array to the tableview (Passing as objects)
        for (FootballClub footballClub : clubList) {
            leagueTable.getItems().add(footballClub);
        }

        // Create sort by scored goals button
        Button sortGButton = new Button("Sort By Goals Scored");
        sortGButton.setLayoutX(10);
        sortGButton.setLayoutY(420);

        // Create sort by wins button
        Button sortWButton = new Button("Sort By Wins");
        sortWButton.setLayoutX(150);
        sortWButton.setLayoutY(420);

        // Create random match button
        Button ranButton = new Button("Random Match");
        ranButton.setLayoutX(10);
        ranButton.setLayoutY(460);

        // Create label
        Label labelRandomMatch = new Label();
        labelRandomMatch.setLayoutX(10);
        labelRandomMatch.setLayoutY(500);

        // Create display match button
        Button btDisplayMatch = new Button("Display Match Table");
        btDisplayMatch.setLayoutX(250);
        btDisplayMatch.setLayoutY(420);


        // Set on action of sort by goals button
        sortGButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                labelRandomMatch.setText("");
                //Clearing all content in the table view
                leagueTable.getItems().clear();

                Comparator goalComparator = Comparator.comparing(FootballClub::getNumberOfScoredGoals);
                Collections.sort(clubList, goalComparator);
                //Reversing the sorted list
                Collections.reverse(clubList);

                for (FootballClub footballClub : clubList) {
                    leagueTable.getItems().add(footballClub);
                }
            }
        });

        // Set on action of sort by wins button
        sortWButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                labelRandomMatch.setText("");

                //Clearing all content in the table view
                leagueTable.getItems().clear();

                Comparator goalComparator = Comparator.comparing(FootballClub::getNumberOfWins);
                Collections.sort(clubList, goalComparator);
                //Reversing the sorted list
                Collections.reverse(clubList);

                for (FootballClub footballClub : clubList) {
                    leagueTable.getItems().add(footballClub);
                }
            }
        });

        // Set on action of random match button
        ranButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                leagueTable.getItems().clear();
                Random random = new Random();
                HashSet<FootballClub> randomHashSet = new HashSet<>();
                while (randomHashSet.size() < 2) {
                    FootballClub randomSportsClub = clubList.get(random.nextInt(clubList.size()));
                    randomHashSet.add(randomSportsClub);
                    clubList.remove(randomSportsClub);
                }

                ArrayList<FootballClub> randomArrayList = new ArrayList<>(randomHashSet);

                // Generate random score
                int maxGoals = 11;
                int goalScoClub01 = random.nextInt(maxGoals);
                int goalScoClub02 = random.nextInt(maxGoals);
                int randomMonth = random.nextInt(11 + 1);
                int randomDay = random.nextInt(30 + 1);

                // Assign randomly choose clubs
                FootballClub randomClub1 ;
                FootballClub randomClub2 ;
                randomClub1 = randomArrayList.get(0);
                FootballClub team1 = randomArrayList.get(0);
                randomClub2 = randomArrayList.get(1);
                FootballClub team2 = randomArrayList.get(1);

                Date newDate = new Date();
                newDate.setYear(2020);
                newDate.setMonth(randomMonth);
                newDate.setDate(randomDay);

                MatchPlayed footballMatch = new MatchPlayed();
                footballMatch.setTeamA(team1);
                footballMatch.setTeamB(team2);
                footballMatch.setTeamAScore(goalScoClub01);
                footballMatch.setTeamBScore(goalScoClub02);
                footballMatch.setDate(newDate);

                matchesArray.add(footballMatch);

                // Increase the number of matches played
                randomClub1.setNumberOfMatchesPlayed(randomClub1.getNumberOfMatchesPlayed() + 1);
                randomClub2.setNumberOfMatchesPlayed(randomClub2.getNumberOfMatchesPlayed() + 1);

                randomClub1.setNumberOfScoredGoals(randomClub1.getNumberOfScoredGoals() + goalScoClub01);
                randomClub1.setNumberOfReceivedGoals(randomClub1.getNumberOfReceivedGoals() + goalScoClub02);

                randomClub2.setNumberOfScoredGoals(randomClub2.getNumberOfScoredGoals() + goalScoClub02);
                randomClub2.setNumberOfReceivedGoals(randomClub2.getNumberOfReceivedGoals() + goalScoClub01);

                // Validate wins according to goals scored
                if(goalScoClub01 > goalScoClub02){
                    labelRandomMatch.setText("Team " + randomClub1.getClubName() + " scored " + goalScoClub01 + " goals" + "\n" + "Team " + randomClub2.getClubName() + " scored " + goalScoClub02 + " goals" + "\n" + "Match was won by team, " + randomClub1.getClubName()  );

                    randomClub1.setNumberOfWins(randomClub1.getNumberOfWins() + 1);
                    randomClub1.setCurrentNumberOfPoints(randomClub1.getCurrentNumberOfPoints() + 3);

                    randomClub2.setNumberOfDefeats(randomClub2.getNumberOfDefeats() + 1);
                }
                else if(goalScoClub01 == goalScoClub02) {
                    labelRandomMatch.setText("Team " + randomClub1.getClubName() + " scored " + goalScoClub01 + " goals" + "\n" + "Team " + randomClub2.getClubName() + " scored " + goalScoClub02 + " goals" + "\n" + "Match was drawn" );

                    randomClub1.setNumberOfDraws(randomClub1.getNumberOfDraws() + 1);
                    randomClub1.setCurrentNumberOfPoints(randomClub1.getCurrentNumberOfPoints() + 1);

                    randomClub2.setNumberOfDraws(randomClub2.getNumberOfDraws() + 1);
                    randomClub2.setCurrentNumberOfPoints(randomClub2.getCurrentNumberOfPoints() + 1);
                }
                else {
                    labelRandomMatch.setText("Team " + randomClub1.getClubName() + " scored " + goalScoClub01 + " goals" + "\n" + "Team " + randomClub2.getClubName() + " scored " + goalScoClub02 + " goals" + "\n" + "Match was won by team, " + randomClub2.getClubName());

                    randomClub2.setNumberOfWins(randomClub2.getNumberOfWins() + 1);
                    randomClub2.setCurrentNumberOfPoints(randomClub2.getCurrentNumberOfPoints() + 3);

                    randomClub1.setNumberOfDefeats(randomClub1.getNumberOfDefeats() + 1);
                }

                // Add two clubs with changed to the main arraylist
                clubList.add(randomClub1);
                clubList.add(randomClub2);

                Comparator statComparator = Comparator.comparing(FootballClub::getCurrentNumberOfPoints).thenComparing(FootballClub::getNumberOfScoredGoals);
                Collections.sort(clubList,statComparator);
                // Reverse the sorted list
                Collections.reverse(clubList);

                for (FootballClub footballClub : clubList) {
                    leagueTable.getItems().add(footballClub);
                }
            }
        });

        // Set on action for display match button
        btDisplayMatch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage matchStage = new Stage();
                matchStage.setTitle("Matches");

                //Search bar for search by date
                TextField textField = new TextField();
                textField.setLayoutX(10);
                textField.setLayoutY(10);

                Button btSortS = new Button("Search");
                btSortS.setLayoutX(200);
                btSortS.setLayoutY(10);

                Button btSortD = new Button("Sort By Date");
                btSortD.setLayoutX(290);
                btSortD.setLayoutY(10);

                // TableView to add match details
                TableView<MatchPlayed> footballMatchTable = new TableView<MatchPlayed>();
                footballMatchTable.setLayoutX(10);
                footballMatchTable.setLayoutY(50);
                footballMatchTable.setPrefSize(1500,800);

                // Create columns in the tableview
                TableColumn<MatchPlayed,Date> date = new TableColumn<MatchPlayed, Date>("Date");
                date.setCellValueFactory(new PropertyValueFactory<>("date"));
                date.setPrefWidth(250);

                TableColumn<MatchPlayed, Integer> teamAScore = new TableColumn<MatchPlayed,Integer>("Team A Goals");
                teamAScore.setCellValueFactory(new PropertyValueFactory<>("teamAScore"));
                teamAScore.setPrefWidth(250);
                TableColumn<MatchPlayed,Integer> teamBScore = new TableColumn<MatchPlayed,Integer>("Team B Goals");
                teamBScore.setCellValueFactory(new PropertyValueFactory<>("teamBScore"));
                teamBScore.setPrefWidth(250);
                TableColumn<MatchPlayed,String> teamA = new TableColumn<MatchPlayed,String>("Team A");
                teamA.setCellValueFactory(new PropertyValueFactory<>("teamA"));
                teamA.setPrefWidth(250);
                TableColumn<MatchPlayed,String> teamB = new TableColumn<MatchPlayed,String>("Team B");
                teamB.setCellValueFactory(new PropertyValueFactory<>("teamB"));
                teamB.setPrefWidth(250);
                footballMatchTable.getColumns().addAll(date, teamA, teamAScore, teamB, teamBScore);

                for (MatchPlayed matchPlayed : matchesArray){
                    footballMatchTable.getItems().add(matchPlayed);
                }

                Pane matchPane = new Pane();
                matchPane.getChildren().addAll(textField,btSortS, btSortD, footballMatchTable);

                matchStage.setScene(new Scene(matchPane, 700, 500));
                matchStage.show();
                System.out.println(matchesArray);

                // Set on action for sort by date button
                btSortD.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        footballMatchTable.getItems().clear();

                        for (MatchPlayed matchPlayed : matchesArray){
                            footballMatchTable.getItems().add(matchPlayed);
                        }
                    }
                });
            }
        });

        // Add content to the main pane
        mainPane.getChildren().addAll(leagueTable, sortGButton, sortWButton, ranButton, labelRandomMatch, btDisplayMatch);

        // Create stage
        Stage pStage = new Stage();
        pStage.setTitle("Premier League Manager");
        // Add mainPane into new scene
        pStage.setScene(new Scene(mainPane, 1000, 700));
        pStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
