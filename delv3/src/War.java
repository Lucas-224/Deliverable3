import static java.util.Collections.shuffle;
import java.util.Scanner;

public class War extends Game {

    private GroupOfCards deck;
    private int handSize;

    // What is this?
    // this was for the helpMe() thing in humanPlayer that was removed i believe
    // private HumanPlayer help;
    // I think we can remove helpMe() from the player class; better than creating an object just for that

    private Player p1;
    private Player p2; // we can change these later
    private Scanner scanner = new Scanner(System.in);

    // maybe we can delete this constructor?
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

    // determines round logic
    public void round(PlayingCard p1Card, PlayingCard p2Card) {

        // 1 = player 1; 2 = player 2; 0 = draw
        int roundWinnerInt = compareCards(p1Card, p2Card);

        // New score = old score + 2... 2 points for winning a round
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

        // set-up vars + scanner
        Scanner input = new Scanner(System.in);
        boolean startGame = false;
        boolean validHandSize = false;
        boolean validDifficulty = false;
        String aiName = "";
        int aiPersonality = 0;

        // introduction
        System.out.println("----------WELCOME TO WAR----------");
        while (!startGame) {
            // Maybe "What is War?" + "Controls" ++ messages should easily fit in CLI)
            System.out.println("Select from menu:\n"
                + "1: Start Playing\n"
                + "2: How to play\n"
                + "0: Quick Start");

            // user chooses option
            int optionChoice = Game.getValidDigit("Enter a number to make a "
                + "choice: ", "Only enter a single digit.", input);

            switch (optionChoice) {
                case 1:
                    System.out.println("Game Starts -- WAR!");
                    startGame = true;
                    break;
                case 2:
                    System.out.println("----------How to Play----------\n");
                    System.out.println("""
                    \u201cWar\u201d is a two-player game wherein players choose a card from 
                    their hand and reveal their values at the same time as one another.  

                    Whoever has the highest value card wins the \u201cbattle\u201d, and gets to keep the pair of cards (or, gets a point).  

                    If there is a draw, players each choose another card to reveal at the same time, and this continues until one player wins 

                    the \u201cbattle\u201d.  Whoever has the most points in the end wins the game of war.\n""");
                case 0:
                    validHandSize = true;
                    validDifficulty = true;
                    startGame = true;
                    setHandSize(5);
                    aiPersonality = 1;
                    aiName = "Easy Eric";
                    break;
                default:
                    System.out.println("Only enter 1 or 2!");
                    break;
            }
        }

        // re-worked above; should be ok to delete
        /*
         * do {
         * System.out.println("Type a number to make a selection: ");
         * int value = input.nextInt();
         * switch (value) {
         * case 1:
         * System.out.println("Game Starts");
         * start = true;
         * break;
         * case 2:
         * System.out.println("----------How to Play----------\n");
         * System.out.println("""
         * \u201cWar\u201d is a two-player game wherein players choose a card
         * from
         * their hand and reveal their values at the same time as one another. *
         * Whoever has the highest value card wins the \u201cbattle\u201d, and
         * gets to keep the pair of cards (or, gets a point). *
         * If there is a draw, players each choose another card to reveal at the
         * same time, and this continues until one player wins *
         * the \u201cbattle\u201d. Whoever has the most points in the end wins
         * the game of war.\n""");
         * System.out.println("Select from menu:\n"
         * + "1: Start Playing\n"
         * + "2: How to play");
         * break;
         * default:
         * System.out.println("Invalid Input");
         * System.out.println("Select from menu:\n"
         * + "1: Start Playing\n"
         * + "2: How to play");
         * break;
         * }
         *
         * } while (start == false);
         */

        // "welcome" is a repeat of above?
        //  System.out.println("----------WELCOME TO WAR----------");

        // Get player name
        System.out.println("Hello, please enter your name.");
        input.nextLine(); // purge the scanner's buffer
        String playerName = Game.cleanStringInput(input.nextLine());


        // select hand size

        while (!validHandSize) {
            int handSizeChoice = Game.getValidDigit("Enter hand size (3 to 9..."
                + " 5 is recommended): ", "Only enter a single digit.", input);

            if (3 <= handSizeChoice && handSizeChoice <= 9) {
                setHandSize(handSizeChoice);
                validHandSize = true;

            } else {
                System.out.println("Game only supports hand sizes between\n"
                    + "3 and 9.  Sorry!");
            }
        }

        System.out.println("Select your opponent:\n1) Easy Eric");

        while (!validDifficulty) {
            aiPersonality = Game.getValidDigit("Choose opponent number: ",
                                               "Please choose from the "
                                               + "listed options", input);
            switch (aiPersonality) {
                case 1:
                    aiName = "Easy Eric";
                    validDifficulty = true; // is there a better way? lol
                    break;
                default:
                    System.out.println("Please choose a supported difficulty.");
                    break;
            }
        }

        // create players
        this.p1 = new HumanPlayer(playerName);
        this.p2 = new AiOpponent(aiPersonality, aiName);

        // I think this is duplicate of the createDeck() method
        /*
         * GroupOfCards deck = new GroupOfCards();
         * for (int i = 0; i < 13; i++) {
         * CardValue value = CardValue.values()[i];
         *
         * for (int j = 0; j < 4; j++) {
         * Card card = new PlayingCard(CardSuits.values()[j], value);
         * deck.addCard(card);
         * }
         * }
         * shuffle(deck.getCards());
         */

        this.deck = createDeck();
        GroupOfCards playerHand = new GroupOfCards();
        playerHand.setLimit(handSize);
        GroupOfCards oppHand = new GroupOfCards();
        oppHand.setLimit(handSize);

        p1.setHand(initialDraw());
        p2.setHand(initialDraw());


    }

    // refactored this to "createDeck" to use verbNoun() naming convention
    public GroupOfCards createDeck() {
        GroupOfCards deck = new GroupOfCards();
        for (int i = 0; i < 13; i++) {
            CardValue value = CardValue.values()[i];

            for (int j = 0; j < 4; j++) {
                Card card = new PlayingCard(CardSuits.values()[j], value);
                deck.addCard(card);
            }
        }

        shuffle(deck.getCards());
        return deck;
    }

    public GroupOfCards initialDraw() {
        GroupOfCards newHand = new GroupOfCards();

        for (int i = 0; i < handSize; i++) {
            newHand.addCard(this.deck.removeCard(handSize));
        }
        return newHand;
    }

    public void bothPlayersDrawToFullHand() {

        // while player hand is empty OR not full... need to check null to
        // prevent null pointer exception
        while (p1.getHand() == null
            || (p1.getHand().getSize() < p1.getHand().getLimit())) {
            if (deck.getSize() >= 2) {
                p1.getHand().addCard(deck.removeCard());
                p2.getHand().addCard(deck.removeCard());
            }
        }
    }

    @Override
    public void declareWinner() {
        if (p1.getScore() > p2.getScore()) {
            System.out.print(p1.getName());
        } else if (p2.getScore() > p1.getScore()) {
            System.out.print(p2.getName());
        }
        System.out.println(" wins the game!");
    }
}
