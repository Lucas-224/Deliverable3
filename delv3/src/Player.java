/**
 * Models a player that can play a game.
 *
 * @see {@link HumanPlayer} for the Human subclass
 * @see {@link AiOpponent} for the AI/computer subclass
 * @author Course Instructors
 * @author Lucas Donegan
 * @author Chris Klammer
 */
public abstract class Player {

    private String name;
    private int score;
    GroupOfCards hand = new GroupOfCards(); // this might work better?
    boolean atWar; // for AI logic

    /**
     * Creates a Player object.
     */
    public Player() {
        // null constructor to allow for placeholders in War class
    }

    /**
     * Creates a Player object with a name.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        setName(name);
    }

    /**
     * For choosing a card from a player's group of cards (such as a hand).
     *
     * @return A single card that was selected by the player.
     * @throws InterruptedException
     */
    // changed from returning int to returning a card, made it abstract
    public abstract Card chooseCard() throws InterruptedException;

    /**
     * Retrieves the group of cards owned by this player.
     *
     * @return A group of cards representing the player's hand.
     */
    public GroupOfCards getHand() {
        return this.hand;
    }

    /**
     * Assigns a group of cards to this player's hand.
     *
     * @param cards The cards belonging to the player.
     */
    public void setHand(GroupOfCards cards) {
        this.hand = cards;
    }

    /**
     * Retrieves the name of the player.
     *
     * @return This player's name as a String.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the player.
     *
     * @param name The name to be given to the player.
     */
    public void setName(String name) {
        this.name = Game.cleanStringInput(name);
    }

    /**
     * Retrieves the player's score as an integer.
     *
     * @return The player's score as an int.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Sets the player's score. To prevent a glitch/exploit with the
     * HumanPlayer class' surrender() method, the score is allowed to be set to
     * -2, instead of 0.
     *
     * @param score An int to set the player's score.
     */
    public void setScore(int score) {

        // prevents negative scores; except -2 which prevents Surrender-Wins
        // caused by surrendering while opponent has no cards and then winning
        // a round
        if (score < -2) {
            this.score = 0;
        } else {
            this.score = score;
        }
    }
}
