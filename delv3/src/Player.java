

import java.util.ArrayList;

public abstract class Player {

    private String name;
    private int score;
    private GroupOfCards hand;

    public String getName() {
        return this.name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param name
     */
    public Player(String name) {
        // TODO - implement Player.Player
        throw new UnsupportedOperationException();
    }

    public void play() {
        // add play feature
    }

    public Card drawCard() {
        // TODO - implement Player.drawCard
        throw new UnsupportedOperationException();
    }

    public int getScore() {
        return this.score;
    }

    /**
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    public int chooseCard() {
        // TODO - implement Player.chooseCard
        throw new UnsupportedOperationException();
    }

    public GroupOfCards getHand() {
        return this.hand;
    }

    /**
     *
     * @param cards
     */
    public void setHand(ArrayList cards) {
        // TODO - implement Player.setHand
        throw new UnsupportedOperationException();
    }

}
