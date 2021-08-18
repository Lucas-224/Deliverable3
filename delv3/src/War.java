import static java.util.Collections.shuffle;
import java.util.Scanner;




public class War extends Game {

    private GroupOfCards deck;
    private int handSize;
    private HumanPlayer help; // What is this?
    private Player p1;
    private Player p2; // we can change these later
    private Scanner scanner = new Scanner(System.in);

    public War(GroupOfCards deck, int handSize, String name) {
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
    public void round(PlayingCard p1Card, PlayingCard p2Card) {

        // 1 = player 1; 2 = player 2; 0 = draw
        int roundWinnerInt = compareCards(p1Card, p2Card);

        // New score = old score + 2... 2 points to win a round

        switch (roundWinnerInt) {
            case 1:
                p1.setScore(p1.getScore() + 2);
                break;
            case 2:
                p2.setScore(p2.getScore() + 2);
                break;
            case 0:
                System.out.println("A draw?!? DECLARATION OF WAR!!!");
                warRound();
                break;
            default:
                System.out.println("Round winner unknown.  No points awarded.");
        }
    }

    public void warRound() {

        int pointPool = 2; // 2 points for previous cards        
        boolean warDone = false;
        int warVictor = 0;

        // until war is done, OR hand runs out of cards, do this
        for (int i = 0; !warDone && i < p1.getHand().getSize(); i++) {

            PlayingCard p1card = (PlayingCard) p1.chooseCard();
            PlayingCard p2card = (PlayingCard) p2.chooseCard();

            warVictor = compareCards(p1card, p2card);
            if (warVictor != 0) {
                pointPool += 2;
                warDone = true;
            }
        } // end war for-loop

        // there's probably a more elegant way to check this...
        if (!warDone) {
            pointPool = 0;
        }

        if (warVictor == 1) {
            p1.setScore(p1.getScore() + pointPool);

        } else if (warVictor == 2) {
            p2.setScore(p2.getScore() + pointPool);
        }
    }

    public int compareCards(PlayingCard p1Card, PlayingCard p2Card) {
        if (p1Card.getStrength() > p2Card.getStrength()) {
            return 1;

        } else if (p1Card.getStrength() < p2Card.getStrength()) {
            return 2;

        } else { // draw
            return 0;
        }
    }

    public GroupOfCards getDeck() {
        return this.deck;
    }

    public void setDeck(GroupOfCards deck) {
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
        } while (start == false);


        System.out.println("----------WELCOME TO WAR----------");
        System.out.println("Hello, please enter your name.");
        String playerName = input.nextLine();

        //Player player = new HumanPlayer(playerName);

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
