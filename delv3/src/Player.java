
/**
  * This is a test and I hate Netbeans.  -Chris
  */

import java.util.ArrayList;

public abstract class Player {

    private String name;
    private int score;
    private GroupOfCards hand;

    /**
     *
     * @param name
     */
    public Player(String name) {
        setName(name);
    }

    public String getName() {
        return this.name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = Game.cleanStringInput(name);
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
        if (score < 0) {
            this.score = 0;
        } else {
            this.score = score;
        }
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
