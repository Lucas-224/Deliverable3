public abstract class Player {


    private String name;
    private int score;
    GroupOfCards hand = new GroupOfCards(); // this might work better?
    boolean atWar;


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

    // Made obsolete by chooseCard() method? **********
    public void play() {
        // add play feature
    }


    // Remove??? **********
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

        // prevents negative scores
        if (score < 0) {
            this.score = 0;
        } else {
            this.score = score;
        }
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
}
