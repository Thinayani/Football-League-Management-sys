package sample;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleMain {
    private static final LeagueManager premierLeagueManager = new PremierLeagueManager();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Retrieve data at the beginning of the program
        premierLeagueManager.retrieveData("saveFile.txt");
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n    ******************* Premier League Championship *******************");

        // Create Menu
        loopOfMenu:
        while (true) {
            System.out.println("\n=================== Select an option to continue the program ===================");
            System.out.println("\nCreate a new Club     :  1\nDelete a Club         :  2\nDisplay a Club        :  3" +
                               "\nDisplay Table         :  4\nAdd a Played Match    :  5\nView Table            :  6" +
                               "\nSave File             :  7\nQuit Program          :  8");
            System.out.println("___________________________________________________________________________________");
            System.out.print("\nEnter the option  : ");

            String line = scanner.nextLine();
            int command = 0;
            try{
                command = Integer.parseInt(line);
            }catch(Exception e){
                System.out.println("Exception!");
            }
            // Switch cases in the menu
            switch (command) {
                case 1:
                    createClub();
                    break;
                case 2:
                    deleteClub();
                    break;
                case 3:
                    displayClub();
                    break;
                case 4:
                    premierLeagueManager.displayTable();
                    break;
                case 5:
                    premierLeagueManager.addPlayedMatch();
                    break;
                case 6:
                    premierLeagueManager.viewTable();
                    break;
                case 7:
                    premierLeagueManager.saveFile("saveFile.txt");
                    break ;
                case 8:
                    System.out.println("\nProgram is quitting...\nBye!");
                    break loopOfMenu;
                default:
                    System.out.println("Entered value is not valid. Please enter a valid input.");
                    break;
            }
        }
    }

    // Create football club
    public static void createClub(){
        Scanner scanner = new Scanner(System.in);
        FootballClub club = null;
        // Get the user inputs to create the club
        while(true){
            System.out.println("Enter Club Name                 : ");
            String name = scanner.nextLine();

            System.out.println("Enter Club Location             : ");
            String location = scanner.nextLine();

            System.out.println("Enter Club Owner                : ");
            String owner = scanner.nextLine();

            System.out.print("Enter the number of wins          : ");
            Integer wins = scanner.nextInt();

            System.out.print("Enter the number of draws         : ");
            Integer draws = scanner.nextInt();

            System.out.print("Enter the number of defeats       : ");
            Integer defeats = scanner.nextInt();

            System.out.print("Enter the number of scored goals  : ");
            Integer sGoals = scanner.nextInt();

            System.out.print("Enter the number of received goals: ");
            Integer rGoals = scanner.nextInt();

            System.out.print("Enter the number matches played   : ");
            Integer matches = scanner.nextInt();

            System.out.print("Enter the current number of points: ");
            Integer points = scanner.nextInt();

            premierLeagueManager.createClub(new FootballClub(name,location,owner,wins,draws,defeats,sGoals,rGoals,matches,points));
            break;
        }
        System.out.println("\nClub has been successfully created.");
    }

    // Delete club
    public static void deleteClub(){
        System.out.println("Please enter the Club Name you want to delete: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        premierLeagueManager.removeClub(name);
    }

    // Display club
    public static void displayClub(){
        System.out.println("Please enter the club name: ");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        premierLeagueManager.displayClub(line);

    }
}
