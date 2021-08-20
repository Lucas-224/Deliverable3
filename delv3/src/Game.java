import java.util.ArrayList;
import java.util.Scanner;

/**
 * Models a game, and contains methods for receiving input in a way to prevent
 * crashes.
 * <p>
 * Preventing crashes seems like an essential part of a game.
 *
 * @author Course Instructors
 * @author Chris Klammer
 */
public abstract class Game {

    private String name;
    private ArrayList players;

    /**
     * Creates a Game object for running a game.
     *
     * @param name The name of the game.
     */
    public Game(String name) {
        // left purposefully blank
    }

    public abstract void play() throws InterruptedException;

    public abstract void declareWinner();


    /**
     * Removes potential bad characters.
     * List of naughty characters from
     * http://www.javapractices.com/topic/TopicAction.do?Id=96
     *
     * @param input
     * @return a String with "dangerous" characters removed.
     */
    public static String cleanStringInput(String input) {
        input = input.strip();
        input = input.replaceAll("\\\\", "");
        input = input.replaceAll("<", "");
        input = input.replaceAll(">", "");
        input = input.replaceAll("&", "");
        input = input.replaceAll("\\\"", "");
        input = input.replaceAll("\\\'", "");

        return input;
    }

    /**
     * Receives user input as a String and returns an integer representing a
     * single digit that is required by another method.
     * <p>
     * Loops until valid input is received.
     *
     * @param prompt A message displayed to the user
     * @param errorMsg An error message that displays if a non-digit was given
     * @param sc A scanner for retrieving input
     * @return
     */
    public static int getValidDigit(String prompt, String errorMsg,
                                    Scanner sc) {
        boolean validInt = false;
        String input = "";

        while (!validInt) {
            System.out.print(prompt);
            input = cleanStringInput(sc.nextLine());
            if (input.matches("\\d")) {
                validInt = true;
            } else {
                System.out.println(errorMsg);
            }

        }
        return Integer.parseInt(input);
    }

    /**
     * Retrieves the name of the game.
     *
     * @return A string (game name)
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves an array list of players.
     *
     * @return An array list containing all player objects.
     */
    public ArrayList getPlayers() {
        return this.players;
    }

    /**
     * Sets game's array list of players
     *
     * @param players A populated arraylist of players
     */
    public void setPlayers(ArrayList players) {
        this.players = players;
    }
}
