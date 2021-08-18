
import java.util.ArrayList;

public abstract class Game {

    private String name;
    private ArrayList players;


    /**
     *
     * @param name
     */
    public Game(String name) {
        
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


    /**
     *
     * @param name
     */
    //public Game(String name) {
        // TODO - implement Game.Game
     //   throw new UnsupportedOperationException();
    //}

    public abstract void play();

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
}
