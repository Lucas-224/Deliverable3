
public class PlayingCard extends Card {

    private CardSuits suit;
    private CardValue value;

    /**
     *
     * @param Suit
     * @param Value
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
