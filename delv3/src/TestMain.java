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

        War war = new War("war");
        war.play();

        /*
         * Scanner input = new Scanner(System.in);
         *
         * GroupOfCards deck = new GroupOfCards();
         * int handsize = 5;
         *
         * for (int i = 0; i < 13; i++) {
         * CardValue value = CardValue.values()[i];
         *
         * for (int j = 0; j < 4; j++) {
         * PlayingCard card = new PlayingCard(CardSuits.values()[j], value);
         * deck.addCard(card);
         * }
         * }
         *
         * //War war = new War(deck, handsize, name);
         * //war.play();
         * /*
         * System.out.println("Testing deck...");
         * System.out.println("createDeck size: " + deck.getSize());
         * System.out.println("Default limit: " + deck.getLimit());
         * deck.addCard(new PlayingCard(CardSuits.SPADES, CardValue.ACE));
         * System.out.println("New ACE of SPADES added, new size = " +
         * deck.getSize());
         * System.out.println("Drew the first card! Result: " +
         * deck.removeCard());
         * System.out.println("Drew newly added card: " + deck.removeCard(51));
         *
         * System.out.println("\nTesting a hand...(same class!)");
         * GroupOfCards hand = new GroupOfCards();
         * hand.setLimit(5);
         * System.out.println("Hand size: " + hand.getSize());
         * System.out.println("Hand limit: " + hand.getLimit());
         * System.out.println("Adding cards to hand until limit...");
         * while (hand.getSize() < hand.getLimit()) {
         * hand.addCard(deck.removeCard());
         * }
         * hand.showCards();
         *
         * Scanner sc = new Scanner(System.in);
         * System.out.println("Enter a card 0 ~ 4");
         * int val = sc.nextInt();
         * PlayingCard testCard = (PlayingCard) hand.removeCard(val);
         * System.out.println("Card Strength: " + testCard.getStrength());
         * String Gname = "\"The Game of WAR -- (C)Abstract Warriors
         * Software\"";
         * System.out.println("deck size:" + deck.getSize());
         */
 /*
         * War war = new War("War");
         * war.play();
         * //Deck deck = new createDeck();
         * //int handsize = 5;
         */
    }
}
