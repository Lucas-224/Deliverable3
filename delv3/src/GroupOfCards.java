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

    public ArrayList getCards() {
        return this.cards;
    }

    public void showCards() {
        for (int i = 0; i < cards.size(); i++) {
            System.out.println("Card [" + i + "]: " + cards.get(i).toString());
        }
    }

    // to remove a specific card (determined by int)...WILL NEED VALIDATION BEFORE ACCESSING THIS METHOD!
    public Card removeCard(int index) {
        if (index < 0 || index > cards.size()) {
            throw new IllegalArgumentException("Position does not exist in hand: " + index);
        }
        return cards.remove(index);
    }

    // to remove first card (e.g. for drawing from deck)
    public Card removeCard() {
        return cards.remove(0);
    }

    // WILL NEED VALIDATION BEFORE ACCESSING THIS METHOD!
    public void addCard(Card newCard) {
        if ((cards.size() + 1 > this.limit) || cards.size() > limit) {
            throw new IllegalArgumentException("Card limit exceeded!");
        }
        cards.add(newCard);
    }

    public int getSize() {
        return cards.size();
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit <= 0) {
            limit = 1; // force min value of 1
        }
        this.limit = limit;
    }
}
