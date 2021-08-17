
// this is a test for git pull
public class PlayingCard extends Card {

    private CardSuits suit;
    private CardValue value;

    /**
     *
     * @param suit
     * @param value
     */

    public PlayingCard(CardSuits suit, CardValue value) {
        this.suit = suit;
        this.value = value;
    }

    public CardSuits getSuit() {
        return suit;
    }

    public CardValue getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + " of " + suit;
    }

}
