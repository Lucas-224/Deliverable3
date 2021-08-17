
import java.util.ArrayList;
import java.util.Collections;

public class GroupOfCards {

    private ArrayList<Card> card;
    private int size;

    

    public GroupOfCards(int size) {
        this.card = new ArrayList<Card>();
        this.size = size;
        
    }

    public void shuffle(ArrayList<Card> card) {
        Collections.shuffle(card);
    }

    
    public ArrayList getDeck() {
        return this.card;
    }
    public void showDeck(ArrayList<Card> deck) {
        for(int i = 0; i < deck.size(); i++){
            System.out.println( i + ": " + deck.get(i).toString());
            
        }
    }

    
    public int getSize() {
        return this.size;
    }
    public void setSize(int size) {
        this.size = size;
    }

}
