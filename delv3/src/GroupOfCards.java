import java.util.ArrayList;
import java.util.Collections;

public class GroupOfCards {

    private ArrayList<Card> cards;
    private int limit = 255; // too high is safer than too low // FORMERLY "size"

    public GroupOfCards() {
        this.cards = new ArrayList<Card>();
    }

    public void shuffle() {
        Collections.shuffle(this.getCards());
    }

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

    // to remove a specific card (determined by int)...WILL NEED VALIDATION BEFORE ACCESSING THIS METHOD!
    public Card removeCard(int index) {
        if (index < 0 || index > cards.size()) {
            throw new IllegalArgumentException("Position does not exist in hand: " + index);
        }
        return cards.remove(index);
    }

    // to remove first card (e.g. for drawing from deck)...ALSO REQUIRES VALIDATION BEFORE ACCESSING
    public Card removeCard() {
        if (cards.size() == 0) {
            throw new RuntimeException("Attempted to take card...no card "
                + "available!");
        }
        return cards.remove(0);
    }

    // WILL NEED VALIDATION BEFORE ACCESSING THIS METHOD!
    public void addCard(Card newCard) {
        if ((cards.size() + 1 > this.limit) || cards.size() > limit) {
            throw new IllegalArgumentException("Card limit exceeded!");
        }
        cards.add(newCard);
    }

    public int checkHand(GroupOfCards hand) {
        return hand.getLimit() - hand.getSize();
    }

    public int getSize() {
        return cards.size();
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit <= 0) {
            limit = 1; // force minimum limit of 1
        }
        this.limit = limit;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList getCards() {
        return this.cards;

    }
}
