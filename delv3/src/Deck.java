
import java.util.ArrayList;
import java.util.Collections;


/**
 * this class 
 * @author Lucas
 */
public class Deck extends GroupOfCards {

    
        private ArrayList<Card> deck;
    private int size;


    public ArrayList<Card> Deck() {
        this.deck = new ArrayList<Card>();
        for (int i = 0; i <= 13; i++) {
            CardValue value = CardValue.values()[i];

            for (int j = 0; j < 4; j++) {
                Card card = new PlayingCard(CardSuits.values()[j], value);
                this.deck.add(card);
            }
        }
        size = 52;
        return deck;
    }

    public void shuffle(ArrayList<Card> deck) {
        Collections.shuffle(deck);
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
