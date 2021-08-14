
import java.util.ArrayList;

public abstract class Game {

    private String name;
    private ArrayList players;

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
    public Game(String name) {
        // TODO - implement Game.Game
        throw new UnsupportedOperationException();
    }

    public abstract void play();

    public abstract void declareWinner();

}
