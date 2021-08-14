package deliverable3;

import java.util.ArrayList;
import java.util.Collections;

public class GroupOfCards {

    private ArrayList deck;
    private int size;

    public void standardDeck() {
        this.deck = new ArrayList();
        for (int i = 0; i <= size; i++) {
            CardValue value = CardValue.values()[i];

            for (int j = 0; j < 4; j++) {
                PlayingCard card = new PlayingCard(CardSuits.values()[j], value);
                this.deck.add(card);
            }
        }
    }

    public GroupOfCards(int size) {
        
    }

    public void shuffle(ArrayList deck) {
        Collections.shuffle(deck);
    }

    public ArrayList getDeck() {
        return this.deck;
    }

    public int getSize() {
        return this.size;
    }

    /**
     *
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }

}
