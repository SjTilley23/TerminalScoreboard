import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

//Samuel Tilley, Ciss-111-300, Program 12
class GamerScore {

    static LinkedList<GamerScore> scores = new LinkedList<>();
    private String name;
    private Integer score;

    public void setNewScore(String sName, int sScore) {
        this.name = sName;
        this.score = sScore;
    }

    // Iterates through the scores LinkedList and compares the score entered to each score in the list.
    // If list is empty adds player/score. If entered score is not found to be higher than any on the list
    // player/score is added to the last spot. If it is found to be higher than a score the new player/score
    // is added to the list at that index. Effectively replacing whatever was there originally.
    public boolean insert(GamerScore gamerScore) {

        if (scores.isEmpty()) {
            scores.add(gamerScore);
            return true;
        } else {
            for (int x = 0; x < scores.size(); x++) {

                if (Objects.equals(gamerScore.name, scores.get(x).name)) {
                    return false;
                }

                int scoreToCompare = scores.get(x).score;
                if (gamerScore.score > scoreToCompare) {
                    scores.add(x,gamerScore);
                    return true;

                } else if (x + 1 == scores.size()) {
                    scores.addLast(gamerScore);
                    return true;
                }
            }
        } return false;
    }

    // Allows user to add extra points to a player's score or remove them from the board.
    // Both methods use basically the same logic just with different outcomes.
    public boolean addToScore(String sName, int scoreToAdd) {

        for (int x = 0; x < scores.size(); x++) {

            if (Objects.equals(sName, scores.get(x).name)) {
                scores.get(x).setNewScore(sName, scoreToAdd + scores.get(x).score);
                return true;

            } else if (x+1 == scores.size()) {
                return false;
            }
        } return false;
    }

    public boolean remove(String sName) {

        for (int x = 0; x < scores.size(); x++) {

            if (Objects.equals(sName, scores.get(x).name)) {
                scores.remove(x);
                return true;

            } else if (x+1 == scores.size()) {
                return false;
            }
        } return false;
    }

    // Simple override of the toString method
    @Override public String toString() {
        return name + " " + score;
    }
} // End of Class


public class Tilley_Samuel_Program_12 {
    public static void main(String[] args) {

        boolean Run = true;

        while (Run) {

            // Setting Class Objects, Displaying options to user, and waiting for response.
            GamerScore gamerScore = new GamerScore();
            Scanner scanner = new Scanner(System.in);
            System.out.println("What would you like to do?\n[1] To add a player\n[2] To display score list" +
                    "\n[3] To add to a pre-existing score\n[4] To remove a player\n[0] to Quit");
            String userResponse = scanner.nextLine();

            // Changes actions depending on user response using simple switch/case statements.
            // If 0, quits the program. If 1 asks for players name and score and attempts to add to scoreboard
            // using the Insert method. If 2 Uses toString method to print scoreboard to console.
            // If 3 asks for players name and score to add and attempts to add to players score using the
            // addToScore method. If 4 asks for players name and attempts to use the remove method
            // to remove them from the scoreboard.
            switch (userResponse) {
                case "0":
                    Run = false;
                    break;

                case "1":
                    System.out.println("Please type the name of the player:");
                    String playerName = scanner.nextLine();
                    System.out.println("Please type the players score:");
                    int playerScore = scanner.nextInt();
                    gamerScore.setNewScore(playerName,playerScore);

                    if (gamerScore.insert(gamerScore)) {
                        System.out.println("\nPlayer added to Scoreboard\n");
                    } else {
                        System.out.println("\nPlayer already on Scoreboard\n");
                    } break;

                case "2":
                    for (int x = 0; x < GamerScore.scores.size(); x++) {
                        System.out.println(GamerScore.scores.get(x).toString());
                    } break;

                case "3":
                    System.out.println("Please type the name of the player:");
                    String sPlayerName = scanner.nextLine();
                    System.out.println("Please type the players score:");
                    int sPlayerScore = scanner.nextInt();

                    if (gamerScore.addToScore(sPlayerName, sPlayerScore)) {
                        System.out.println("\nAdded to Players Score\n");
                    } else {
                        System.out.println("\nPlayer not Found\n");
                    } break;

                case "4":
                    System.out.println("Please type the name of the player:");
                    String cPlayerName = scanner.nextLine();

                    if (gamerScore.remove(cPlayerName)) {
                        System.out.println("\nPlayer removed\n");
                    } else {
                        System.out.println("\nPlayer not Found\n");
                    } break;

            }
        }
    }
} // End of Class