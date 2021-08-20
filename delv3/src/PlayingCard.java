/**
 * Models a playing card from a standard 52-deck of cards.
 *
 * @author Lucas Donegan
 * @author Chris Klammer
 */
public class PlayingCard extends Card {

    private CardSuits suit;
    private CardValue value;

    /**
     * Creates a playing card with a given suit and value. Creation is done
     * elsewhere.
     *
     * @param suit The suit of the card (Generally Hearts, Spades, Diamonds,
     * Clubs)
     * @param value The value of the card (From Ace to King)
     */
    public PlayingCard(CardSuits suit, CardValue value) {
        this.suit = suit;
        this.value = value;
    }

    /**
     * Retrieves the suit of the card. Clubs, Spades, Diamonds, Hearts,
     * generally.
     *
     * @return This card's suit.
     */
    public CardSuits getSuit() {
        return suit;
    }

    /**
     * Retrieves the value of the card. 2 through 9, Ace, Jack, Queen, or King.
     *
     * @return the card's value.
     */
    public CardValue getValue() {
        return value;
    }

    /**
     * Retrieves the "strength" of the card, roughly corresponding with its
     * value. Ace has a strength of 11 (not 1), and all "Face cards" (Jack,
     * Queen, King) all have a strength of 10.
     *
     * @return The strength of the given card.
     */
    public int getStrength() {
        return value.strength;
    }

    /**
     * Models a card as a String. Generally used for displaying a card.
     *
     * @return A string to show a card's value and suit.
     */
    @Override
    public String toString() {
        return value + " of " + suit;
    }

}
