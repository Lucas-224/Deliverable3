import java.util.Scanner;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Lucas
 */

public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        GroupOfCards deck = new GroupOfCards();

        int handsize = 5;

        String name = "War";


        for (int i = 0; i < 13; i++) {
            CardValue value = CardValue.values()[i];

            for (int j = 0; j < 4; j++) {
                Card card = new PlayingCard(CardSuits.values()[j], value);
                deck.addCard(card);
            }
        }
        deck.shuffle();

        //War war = new War(deck, handsize, name);
        //war.play();

        System.out.println("Testing deck...");
        System.out.println("Deck size: " + deck.getSize());
        System.out.println("Default limit: " + deck.getLimit());
        deck.addCard(new PlayingCard(CardSuits.SPADES, CardValue.ACE));
        System.out.println("New ACE of SPADES added, new size = " + deck.getSize());
        System.out.println("Drew the first card! Result: " + deck.removeCard());
        System.out.println("Drew newly added card: " + deck.removeCard(51));

        System.out.println("\nTesting a hand...(same class!)");
        GroupOfCards hand = new GroupOfCards();
        hand.setLimit(5);
        System.out.println("Hand size: " + hand.getSize());
        System.out.println("Hand limit: " + hand.getLimit());
        System.out.println("Adding cards to hand until limit...");
        while (hand.getSize() < hand.getLimit()) {
            hand.addCard(deck.removeCard());
        }
        hand.showCards();
    }
}
