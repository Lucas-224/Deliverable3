

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
                + "2: How to play\n"
                + "3: Blaah Blaah Blaah\n"
                + "4: Blaah1 Cyaa2 Naaa3");
        int value = 0;
        value = input.nextInt();

        switch (value) {
            case 1:
                System.out.println("Game Starts");
                break;
            case 2:
                help.helpMe();
                break;
            case 3:
                System.out.println("3: Blaah Blaah Blaah");
                break;
            case 4:
                System.out.println("4: Blaah1 Cyaa2 Naaa3");
                break;
            default:
                System.out.println("Invalid Input");    
        }
           

    }

    @Override
    public void declareWinner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
