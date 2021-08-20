/**
 * Models standard 52-card deck playing card values.
 * <p>
 * Note that Ace is represented with a "strength" of 11, and all "face cards"
 * (king, queen, jack) have a "strength" of 10.
 *
 * @author Lucas
 */
public enum CardValue {
    ACE(11),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);

    public final int strength;

    private CardValue(int label) {
        this.strength = label;
    }
}
