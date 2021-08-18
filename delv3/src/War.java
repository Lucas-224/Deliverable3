

import java.util.ArrayList;
import static java.util.Collections.shuffle;
import java.util.Scanner;




public class War extends Game {

    private Deck deck;
    private int handSize;
    private HumanPlayer help;

    public War(Deck deck, int handSize, String name) {
        super(name);
        this.deck = deck;
        this.handSize = handSize;
    }

    public War(String name) {
        super(name);
    }

    /**
     *
     * @param p1Card
     * @param p2Card
     */
    public void round(Card p1Card, Card p2Card) {
        // TODO - implement War.round
        throw new UnsupportedOperationException();
    }

    public GroupOfCards getDeck() {
        return this.deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public int getHandSize() {
        return this.handSize;
    }

    public void setHandSize(int handSize) {
        this.handSize = handSize;
    }

    @Override
    public void play() {

        System.out.println("----------WELCOME TO WAR----------");
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Select from menu:\n"
                + "1: Start Playing\n"
                + "2: How to play");
        boolean start = false;
        
        do {
        System.out.println("Type a number to make a selection: ");
        int value = input.nextInt();
        switch (value) {
            case 1:
                System.out.println("Game Starts");
                start = true;
                break;
            case 2:
                System.out.println("----------How to Play----------\n");
                System.out.println("""
                \u201cWar\u201d is a two-player game wherein players choose a card from 
                their hand and reveal their values at the same time as one another.  

                Whoever has the highest value card wins the \u201cbattle\u201d, and gets to keep the pair of cards (or, gets a point).  

                If there is a draw, players each choose another card to reveal at the same time, and this continues until one player wins 

                the \u201cbattle\u201d.  Whoever has the most points in the end wins the game of war.\n""");
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
            System.out.println("Select from menu:\n"
                + "1: Start Playing\n"
                + "2: How to play");
        } while(start == false);
           

    }

    public void Deck() {
        GroupOfCards deck = new GroupOfCards();
        for (int i = 0; i < 13; i++) {
            CardValue value = CardValue.values()[i];

            for (int j = 0; j < 4; j++) {
                Card card = new PlayingCard(CardSuits.values()[j], value);
                deck.addCard(card);
            }
        }
        
        shuffle(deck.getCards());
    }
    
    @Override
    public void declareWinner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
