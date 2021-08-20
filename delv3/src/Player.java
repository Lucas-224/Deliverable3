public abstract class Player {


    private String name;
    private int score;
    GroupOfCards hand = new GroupOfCards(); // this might work better?
    boolean atWar; // for AI logic

    public Player() {
        // null constructor to allow for placeholders in War class
    }

    public Player(String name) {
        setName(name);
    }

    // changed from returning int to returning a card, made it abstract
    public abstract Card chooseCard() throws InterruptedException;

    public GroupOfCards getHand() {
        return this.hand;
    }

    /**
     *
     * @param cards
     */
    public void setHand(GroupOfCards cards) {
        this.hand = cards;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = Game.cleanStringInput(name);
    }

    public int getScore() {
        return this.score;
    }

    /**
     *
     * @param score
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
