import static java.util.Collections.shuffle;
import java.util.Scanner;

public class War extends Game {

    private GroupOfCards deck;
    private int handSize;
    private Player p1;
    private Player p2;
    private Scanner scanner = new Scanner(System.in);

    public War(String name) {
        super(name);
    }

    /**
     *
     * @param p1Card
     * @param p2Card
     */

    // determines round logic
    public void round(PlayingCard p1Card, PlayingCard p2Card)
        throws InterruptedException {

        // if 1 = player 1 wins; 2 = player 2 wins; returns 0 for draw
        int roundWinnerInt = compareCards(p1Card, p2Card);

        System.out.println("\n" + p1.getName() + " played: "
            + p1Card.toString());
        System.out.println(p2.getName() + " played: " + p2Card.toString()
            + "\n");
        Thread.sleep(1000);

        // New score = old score + 2... 2 points for winning a round
        switch (roundWinnerInt) {
            case 1:
                p1.setScore(p1.getScore() + 2);
                System.out.println(p1.getName() + " gets 2 points!\n");
                break;
            case 2:
                p2.setScore(p2.getScore() + 2);
                System.out.println(p2.getName() + " gets 2 points!\n");
                break;
            case 0:
                System.out.println("A draw?!? DECLARATION OF WAR!!!\n");
                warRound();
                break;
            default:
                System.out.println("Round winner unknown.  No points "
                    + "awarded.\n");
        }
        // looked up how to make java wait, found this method in Java
        // official documentation
        Thread.sleep(1000);
    }

    public void warRound() throws InterruptedException {

        p1.atWar = true;
        p2.atWar = true;
        int pointPool = 0;
        boolean warDone = false;
        int warVictor = 0;

        // until war is done, OR hand runs out of cards, do this
        for (int i = 0; !warDone && p1.getHand().getSize() != 0; i++) {

            pointPool += 2; // 2 pts for each draw that leads to this iteration

            PlayingCard p1card = (PlayingCard) p1.chooseCard();
            PlayingCard p2card = (PlayingCard) p2.chooseCard();

            System.out.println("\n" + p1.getName() + " played: "
                + p1card.toString());
            Thread.sleep(500);
            System.out.println(p2.getName() + " played: " + p2card.toString()
                + "\n");
            Thread.sleep(500);

            warVictor = compareCards(p1card, p2card);
            if (warVictor != 0) {
                pointPool += 2; // 2 cards that are in the war now
                warDone = true;
            } else {
                System.out.println("The war wages on!!!\n");
                Thread.sleep(500);
            }
        } // end war for-loop

        // there's probably a more elegant way to check this...
        if (!warDone) {
            System.out.println("The war ended in a draw... " + pointPool
                + " points vanished...\n");
            pointPool = 0;
        }

        if (warVictor == 1) {
            p1.setScore(p1.getScore() + pointPool);
            System.out.println(p1.getName() + " won the war for " + pointPool
                + " points!\n");

        } else if (warVictor == 2) {
            p2.setScore(p2.getScore() + pointPool);
            System.out.println(p2.getName() + " won the war for " + pointPool
                + " points!\n");
        }
        p1.atWar = false;
        p2.atWar = false;

        Thread.sleep(1000);
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
    public void play() throws InterruptedException {

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
                    aiPersonality = 2;
                    aiName = "Random Randy";
                    break;
                default:
                    System.out.println("Only enter 1 or 2!");
                    break;
            }
            Thread.sleep(1000);
        }

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
                Thread.sleep(1000);
            }
        }

        System.out.println("Select your opponent:\n1) Aggressive Andrew (Easy)"
            + "\n2) Random Randy (Easy)\n3) Careful Carl (Medium)"
            + "\n4) Smart Stewart (Hard)\n5) Dominating Diane (Very Hard)");
        while (aiPersonality < 1) {
            aiPersonality = Game.getValidDigit("Choose opponent number: ",
                                               "Please choose from the "
                                               + "listed options", input);
            switch (aiPersonality) {
                case 1:
                    aiName = "Aggressive Andrew";
                    validDifficulty = true; // is there a better way? lol -- Probably instead of boolean; if aiPersonality is between 1 ~ 5
                    break;
                case 2:
                    aiName = "Random Randy";
                    validDifficulty = true;
                    break;
                case 3:
                    aiName = "Careful Carl";
                    validDifficulty = true;
                    break;
                case 4:
                    aiName = "Smart Stewart";
                    validDifficulty = true;
                    break;
                case 5:
                    aiName = "Dominating Diane";
                    validDifficulty = true;
                    break;
                default:
                    System.out.println("Please choose a supported difficulty.");
                    Thread.sleep(1000);
                    break;
            }
        }

        // create players & deck & hands
        this.p1 = new HumanPlayer(playerName);
        this.p2 = new AiOpponent(aiPersonality, aiName);
        this.deck = createDeck();

        p1.setHand(initialDraw());
        p1.getHand().setLimit(handSize);
        p2.setHand(initialDraw());
        p2.getHand().setLimit(handSize);

        Thread.sleep(1000);
        System.out.println("\n****************\n" + p1.getName() + " vs. "
            + p2.getName() + "\n****************\n");
        Thread.sleep(1000);

        while (p1.getHand().getSize() >= 1) {
            round((PlayingCard) p1.chooseCard(), (PlayingCard) p2.chooseCard());
            bothPlayersDrawToFullHand();
        }
        declareWinner();
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
            newHand.addCard(this.deck.removeCard());
        }
        return newHand;
    }

    public void bothPlayersDrawToFullHand() {

        // while player hand is empty OR not full... need to check null to
        // prevent null pointer exception
        // AND deck must have enough cards to draw.
        while (p1.getHand() == null
            || (p1.getHand().getSize() < p1.getHand().getLimit())
            && deck.getSize() >= 2) {

            p1.getHand().addCard(deck.removeCard());
            p2.getHand().addCard(deck.removeCard());
        }
    }

    @Override
    public void declareWinner() {

        System.out.println(p1.getName() + " has " + p1.getScore() + " points.");
        System.out.println(p2.getName() + " has " + p2.getScore() + " points.");

        if (p1.getScore() > p2.getScore()) {
            System.out.print(p1.getName());

        } else if (p2.getScore() > p1.getScore()) {
            System.out.print(p2.getName());
        }

        System.out.println(" wins the game!");
    }
}
