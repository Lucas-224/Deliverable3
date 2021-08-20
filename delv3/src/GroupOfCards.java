import java.util.ArrayList;
import java.util.Collections;

/**
 * Models a group of cards; can be used as a hand, a deck, or other groups (for
 * example).
 *
 * @author Course Instructors
 * @author Chris Klammer
 * @author Lucas Donegan
 * @author Kemon Brown
 */
public class GroupOfCards {

    private ArrayList<Card> cards;
    private int limit = 255; // too high is safer than too low; FORMERLY "size"

    /**
     * Creates a group of cards and initializes the ArrayList of cards.
     */
    public GroupOfCards() {
        this.cards = new ArrayList<Card>();
    }

    /**
     * Shuffles/randomizes the order of the cards in the group.
     *
     * @see {@link Collections.shuffle}
     */
    public void shuffle() {
        Collections.shuffle(this.getCards());
    }

    /**
     * Prints to console all cards in the Group. Displays a message if there
     * are no cards in the group.
     */
    public void showCards() {
        if (cards.size() == 0) {
            System.out.println("No cards left!");
        }
        for (int i = 0; i < cards.size(); i++) {
            System.out.println("Card [" + (i + 1) + "]: "
                + cards.get(i).toString());

            // i + 1 to use 1 to 9 instead of 0 - 8!
        }
    }

    /**
     * Takes a card from the group, generally to be placed elsewhere. This does
     * NOT delete a card!
     * <p>
     * This only removes a card from the group. It still exists if it is
     * received by another method!
     * <p>
     * For example, this method is used for both "drawing cards" from the deck
     * (from the deck to a hand), and for "playing cards" (from the hand to the
     * playing field).
     * <p>
     * NOTE: Crashes on receiving bad input. Check BEFORE calling this method!
     *
     * @param index A chosen index corresponding to a card in the group
     * @return A card, determined by the index.
     */
    // to remove a specific card (determined by int)
    public Card removeCard(int index) {
        if (index < 0 || index > cards.size()) {
            throw new IllegalArgumentException("Position does not exist in hand: " + index);
        }
        return cards.remove(index);
    }

    /**
     * Removes the first card from a group of cards (index = 0).
     * <p>
     * NOTE: Crashes on attempting to remove a card that does not exist!
     * Check availability BEFORE calling this method!
     *
     * @see {@link #removeCard(int index)}
     * @return A card at index 0.
     */
    // to remove first card (e.g. for drawing from deck)...ALSO REQUIRES VALIDATION BEFORE ACCESSING
    public Card removeCard() {
        if (cards.size() == 0) {
            throw new RuntimeException("Attempted to take card...no card "
                + "available!");
        }
        return cards.remove(0);
    }

    /**
     * Adds a card to a group of cards. Generally used in tandem with the
     * {@link removeCard} method. This method can receive a card that was
     * "removed" by the removeCard() method.
     * <p>
     * NOTE: Verify limit BEFORE attempting to add a card! It will crash
     * otherwise!
     *
     * @param newCard
     */
    public void addCard(Card newCard) {
        if ((cards.size() + 1 > this.limit) || cards.size() > limit) {
            throw new IllegalArgumentException("Card limit exceeded!");
        }
        cards.add(newCard);
    }

    /**
     * Retrieves the number of cards in the group.
     *
     * @return The number of cards
     */
    public int getSize() {
        return cards.size();
    }

    /**
     * Retrieves the maximum number of cards allowed in the group.
     *
     * @return The max card limit as an integer
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Determines the maximum number of cards allowed in the group. Cannot be
     * 0 or fewer; must be 1 or greater.
     *
     * @param limit The maximum number of cards allowed in the group
     */
    public void setLimit(int limit) {
        if (limit <= 0) {
            limit = 1; // force minimum limit of 1
        }
        this.limit = limit;
    }

    /**
     * Provides cards to this group. Must be entered as an ArrayList.
     *
     * @param cards A pre-populated ArrayList of cards
     */
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    /**
     * Retrieves the cards in this group as an ArrayList.
     *
     * @return an arraylist of cards that make up this group.
     */
    public ArrayList getCards() {
        return this.cards;

    }
}
