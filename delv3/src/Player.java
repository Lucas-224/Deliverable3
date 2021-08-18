import java.util.ArrayList;

public abstract class Player {


    private String name;
    private int score;
    private GroupOfCards hand;
    private boolean isHuman; // there's probably a better way to do this...


    public String getName() {
        return this.name;
    }

    public Player() {
        // null constructor to allow for placeholders in War class
    }

    public Player(String name) {
        setName(name);
    }

    public void setName(String name) {
        this.name = Game.cleanStringInput(name);
    }

    public void play() {
        // add play feature
    }


    /**
     * player choices
     * <p>
     */

    //public int chooseCardToDraw() {
    //    return in.nextInt();
    //}

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

    // changed from returning int to returning a card, made it abstract
    public abstract Card chooseCard();

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
