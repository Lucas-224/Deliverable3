
import java.util.ArrayList;
import java.util.Collections;

public abstract class GroupOfCards {

    private ArrayList<Card> card;
    private int size;

    
    public GroupOfCards() {
        this.card = new ArrayList<Card>();
    }
    public GroupOfCards(int size) {
        this.card = new ArrayList<Card>();
        this.size = size;
    }

    public void shuffle() {
        Collections.shuffle(getCards());
    }

    public ArrayList getCards() {
        return this.card;
    }
    public void showCards(ArrayList<Card> deck) {
        for(int i = 0; i < deck.size(); i++){
            System.out.println( i + ": " + deck.get(i).toString());
        }
    }

    public void removeCard(int index) {
        if (index < 0 || index >= card.size()) {
            throw new IllegalArgumentException("Position does not exist in hand: " + index);
        }
        card.remove(index);
    }
    public void addCard(Card newCard){
        card.add(newCard);
    }
    
    public int getSize() {
        return card.size();
    }
    public void setSize(int size) {
        this.size = size;
    }
    
    public void Deck(){
        
    }
    
    public void Hand() {
        
    }

}
