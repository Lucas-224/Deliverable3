
import java.util.ArrayList;
import java.util.Collections;


/**
 * this class 
 * @author Lucas
 */
public class Deck extends GroupOfCards {

    private ArrayList<Card> deck;
    private int size;


    

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
