

public class War extends Game {

    private GroupOfCards deck;
    private int hand_size;

    /**
     *
     * @param p1Card
     * @param p2Card
     */
    public void round(Card p1Card, Card p2Card) {
        // TODO - implement War.round
        throw new UnsupportedOperationException();
    }

    public GroupOfCards getDeck() {
        return this.deck;
    }

    /**
     *
     * @param deck
     */
    public void setDeck(GroupOfCards deck) {
        this.deck = deck;
    }

    public int getHand_size() {
        return this.hand_size;
    }

    /**
     *
     * @param hand_size
     */
    public void setHand_size(int hand_size) {
        this.hand_size = hand_size;
    }

    @Override
    public void play() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void declareWinner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
