import java.util.ArrayList;
import java.util.Scanner;

public abstract class Game {

    private String name;
    private ArrayList players;

    /**
     *
     * @param name
     */
    public Game(String name) {

    }

    public abstract void play() throws InterruptedException;

    public abstract void declareWinner();


    /**
     * Removes potential bad characters.
     * List of naughty characters from
     * http://www.javapractices.com/topic/TopicAction.do?Id=96
     *
     * @param input
     * @return
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

    public String getName() {
        return this.name;
    }

    public ArrayList getPlayers() {
        return this.players;
    }

    /**
     *
     * @param players
     */
    public void setPlayers(ArrayList players) {
        this.players = players;
    }
}
