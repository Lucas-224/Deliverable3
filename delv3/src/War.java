import java.util.ArrayList;
import static java.util.Collections.shuffle;
import java.util.Scanner;

/**
 * For playing the game of war. This method is too big and does too much at
 * the moment, but it works, and it only runs functions related to running
 * the game of war.
 *
 * @author Chris Klammer
 * @author Lucas Donegan
 * @author Kemon Brown
 */
public class War extends Game {

    private static War instance = null;
    private GroupOfCards deck;
    private int handSize;
    private Player p1;
    private Player p2;
    private Scanner scanner = new Scanner(System.in);

    /**
     * Creates an instance of the game of war.
     *
     * @param name The name of the game.
     */
    private War(String name) {
        super(name);
    }

    /**
     * Allows for a singleton design; only 1 "war" game can exist at once.
     *
     * @return An instance of the game of war.
     */
    public static War getInstance() {
        if (instance == null) {
            instance = new War("war");
        }
        return instance;
    }

    /**
     * Contains the logic for running a single round of the game. Receives
     * a card from each player, and will determine game-flow based on the
     * outcome (round ends with scores assigned, or leads to war).
     *
     * @param p1Card Player 1's chosen card
     * @param p2Card Player 2's chosen card
     */
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

    /**
     * A "war round" which takes place following a draw. Proceeds like normal
     * rounds, except the players do not draw cards after playing them.
     * <p>
     * If the players run out of cards and no winner has been decided at any
     * point during the war, neither player earns any points during the "war
     * round".
     *
     * @throws InterruptedException
     */
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

    /**
     * Compares cards from two players. It returns an integer representing
     * the winning player (either player *1* or player *2*), or 0 for a draw
     * (meaning NO winner).
     *
     * @param p1Card Player 1's chosen card
     * @param p2Card Player 2's chosen card
     * @return An integer representing the winning player (1 or 2) or a draw (0)
     */
    public int compareCards(PlayingCard p1Card, PlayingCard p2Card) {
        if (p1Card.getStrength() > p2Card.getStrength()) {
            return 1;

        } else if (p1Card.getStrength() < p2Card.getStrength()) {
            return 2;

        } else { // draw
            return 0;
        }
    }

    /**
     * Creates a standard 52-card deck as a group of cards. 13 values, 4
     * suits, one of each card.
     *
     * @return A group of cards, filled with the 52-card standard deck.
     */
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

    /**
     * Retrieve a GroupOfCards, initialized, to be given to each player at the
     * start of the game.
     *
     * @return a group of cards for a player.
     */
    public GroupOfCards initialDraw() {
        GroupOfCards newHand = new GroupOfCards();

        for (int i = 0; i < handSize; i++) {
            newHand.addCard(this.deck.removeCard());
        }
        return newHand;
    }

    /**
     * Players draw from the deck until their hands are full, or until the deck
     * doesn't have enough cards to give each player a card.
     * <p>
     * Generally done a the start of a turn. Players draw up until the limit,
     * or the end of the deck.
     * <p>
     * Players draw one card at a time, and should always have the same number
     * of cards as each other.
     */
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

    /**
     * Plays through a game of War from start to finish, including setup and
     * initializing game scenario. After the game begins, it only checks for
     * the "end game" condition of the player running out of cards. Logic/flow
     * is generally handled by other methods such as {@link round}
     *
     * @see round() method
     * @throws InterruptedException
     */
    @Override
    public void play() throws InterruptedException {

        // set-up vars + scanner
        Scanner input = new Scanner(System.in);
        boolean startGame = false;
        boolean validHandSize = false;
        boolean validDifficulty = false;
        String aiName = "";
        String playerName = "";
        int aiPersonality = 0;

        // introduction
        System.out.println("----------WELCOME TO WAR----------");
        while (!startGame) {
            // Maybe "What is War?" + "Controls" ++ messages should easily fit in CLI)
            System.out.println("Select from menu:\n"
                + "1: Start Playing\n"
                + "2: What is War?\n"
                + "3: Quick Tips\n"
                + "4: Controls\n"
                + "5: Credits\n"
                + "0: Quick Start (I just wanna play!)");

            // user chooses option
            int optionChoice = Game.getValidDigit("Enter a number to make a "
                + "choice: ", "Only enter a single digit.", input);

            switch (optionChoice) {
                case 1:
                    System.out.println("Game Starts -- WAR!");
                    startGame = true;
                    break;
                case 2:
                    System.out.println("----------How to Play----------");
                    System.out.println("War is a two-player game wherein"
                        + "\nplayers choose a card from their hand, and reveal"
                        + "\ntheir cards' values at the same time as one "
                        + "another."
                        + "\nWhoever has the highest value card wins the"
                        + "\nbattle and gets to keep the pair of cards"
                        + "\n(or, gets 2 points)."
                        + "\n\nIf there is a draw, a \"WAR\" is declared!"
                        + "\nPlayers each choose another card to reveal at the"
                        + "\nsame time, and this continues until one player"
                        + "\nwins the battle."
                        + "\n\nPlayers DO NOT draw cards during a WAR!"
                        + "\nIf players run out of cards in the middle of a"
                        + "\nWAR, then points for that battle are thrown away!"
                        + "\nWhoever has the most points in the end wins the"
                        + "\ngame of war.\n");
                    break;
                case 3:
                    System.out.println("Ace is the best card!\nIt has a "
                        + "value of 11!"
                        + "\nJack, Queen, and King all have values of 10."
                        + "\n\nYou might want to save some of these cards for a"
                        + "\nWAR!");
                    break;
                case 4:
                    System.out.println("Select your card by entering a number."
                        + "\nConfirm your choice by pressing your Enter key."
                        + "\n\nYou may forfeit the game by entering 0!");
                    break;
                case 5:
                    System.out.println("Developed by Abstract Warriors"
                        + "Software, 2021"
                        + "\nChris Klammer, Lucas Donegan, Kemon Brown");
                    break;
                case 0:
                    validHandSize = true;
                    validDifficulty = true;
                    startGame = true;
                    setHandSize(5);
                    aiPersonality = 2;
                    aiName = "Random Randy";
                    playerName = "Player 1";
                    break;
                default:
                    System.out.println("Only enter 1, 2, 3, 4, 5, or 0!");
                    break;
            }
            Thread.sleep(1000);
        }

        // Get player name
        if (playerName == "") {
            System.out.println("Hello, please enter your name.");
            playerName = Game.cleanStringInput(input.nextLine());
        }

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

        if (aiPersonality == 0) {
            System.out.println("Select your opponent:\n"
                + "1) Aggressive Andrew (Easy)\n2) Random Randy (Easy)"
                + "\n3) Careful Carl (Medium)\n4) Smart Stewart (Hard)"
                + "\n5) Dominating Diane (Very Hard)");
        }
        while (!validDifficulty) {
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

        ArrayList<Player> players = new ArrayList(2);
        players.add(p1);
        players.add(p2);
        this.setPlayers(players);

        Thread.sleep(1000);
        System.out.println("\n****************\n" + p1.getName() + " vs. "
            + p2.getName() + "\n****************\n");
        Thread.sleep(1000);

        while (p1.getHand().getSize() >= 1) {
            bothPlayersDrawToFullHand();
            round((PlayingCard) p1.chooseCard(), (PlayingCard) p2.chooseCard());
        }
        declareWinner();
    }

    /**
     * Compares scores to determine a winner. Displays scores and announces a
     * winner.
     */
    @Override
    public void declareWinner() {

        if (p1.getScore() < 0) {
            p1.setScore(0);
        }
        if (p2.getScore() < 0) {
            p2.setScore(0);
        }

        System.out.println(p1.getName() + " has " + p1.getScore() + " points.");
        System.out.println(p2.getName() + " has " + p2.getScore() + " points.");

        if (p1.getScore() > p2.getScore()) {
            System.out.print(p1.getName());

        } else if (p2.getScore() > p1.getScore()) {
            System.out.print(p2.getName());
        }

        System.out.println(" wins the game!");
    }

    /**
     * Retrieves the group of cards that represents the game's main deck.
     *
     * @return The game deck as a group of cards.
     */
    public GroupOfCards getDeck() {
        return this.deck;
    }

    /**
     * Assigns a group of cards to the game's main deck.
     *
     * @param deck A group of cards.
     */
    public void setDeck(GroupOfCards deck) {
        this.deck = deck;
    }

    /**
     * Retrieves the hand size limit for the game.
     *
     * @return The hand size (maximum allowable cards at a time per one player)
     */
    public int getHandSize() {
        return this.handSize;
    }

    /**
     * Determines the hand size limit for the game.
     *
     * @param handSize The maximum number of cards allowed for a player to have.
     */
    public void setHandSize(int handSize) {
        this.handSize = handSize;
    }
}
