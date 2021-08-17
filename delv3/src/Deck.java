
import java.util.ArrayList;
import java.util.Collections;


/**
 * this class 
 * @author Lucas
 */
public class Deck extends GroupOfCards {

    private ArrayList<Card> deck;
    private int size;


    public Deck(ArrayList<Card> deck) {
        for (int i = 0; i <= 13; i++) {
            CardValue value = CardValue.values()[i];

            for (int j = 0; j < 4; j++) {
                Card card = new PlayingCard(CardSuits.values()[j], value);
                deck.add(card);
            }
        }
        size = 52;
        this.deck = deck;
    }

    public int cardsLeft() {
        return deck.size() - size;
    }

    public Card dealCard() {
        if (size == deck.size())
            throw new IllegalStateException("No cards are left in the deck.");
        size--;
        return deck.get(0);
        // not actually removed !!!
    }
}
