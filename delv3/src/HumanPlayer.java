import java.util.Scanner;

/**
 * Represents a player controlled by a human. It might be you!
 *
 * @author Chris Klammer
 * @author Lucas Donegan
 * @author Kemon Brown
 */
public class HumanPlayer extends Player {

    /**
     * Creates a Human Player.
     *
     * @param name The name of the human player.
     */
    public HumanPlayer(String name) {
        super(name);
    }

    /**
     * Allows a human player to select a card from their hand. The method
     * reads input from the player and allows them to choose a card, or to
     * surrender/give up.
     * <p>
     * Warning: Cancelling a surrender causes the player to play their first
     * card (index 0). This is a bug fix/TODO for another day!
     *
     * @return A card that was chosen by the player
     * @throws InterruptedException
     */
    @Override
    public PlayingCard chooseCard() throws InterruptedException {

        boolean validInput = false;
        int max = hand.getSize();
        String inputStr;
        int inputInt = 0; // not ideal; but compiler is happier
        Scanner sc = new Scanner(System.in);

        while (!validInput) {
            this.hand.showCards();
            System.out.println("Choose a card from the above");

            inputStr = Game.cleanStringInput(sc.nextLine());

            // only supports 0 ~ 9
            if (inputStr.matches("\\d")) {

                if (inputStr.matches("0")) {
                    surrender(sc);
                    inputStr = "1"; // to throw away last card
                }

                // - 1 is to allow usage of 1 - 9 for card selection instead
                // of 0 - 8...this converts card "1" into index 0.
                inputInt = (Integer.parseInt(inputStr)) - 1;

                // must be LESS than max...if size = 1, then "max index" == 0
                if (inputInt < max && inputInt >= 0) {
                    validInput = true;
                } else {
                    System.out.println("OOPS! Enter between 1 and "
                        + (max) + "!"); // "between 1 and 1" is acceptable
                    Thread.sleep(1000);

                } // end check for acceptable index
            } else {
                System.out.println("OOPS! Enter a single digit ONLY!");
                Thread.sleep(1000);
            } // end check for digit entered
        } // end "while-loop" check for valid input

        // if we're here, we can safely return the selected card
        return (PlayingCard) hand.removeCard(inputInt);
    }

    /**
     * A human player can surrender, giving up their cards and their points,
     * losing the game. To allow for a graceful end from the "card-selection"
     * screen, the player throws away all of their cards except 1, then plays
     * their last card. This allows for a graceful exit back to the war,
     * allows for the end of the turn, and the game will end because the player
     * has no cards left.
     * <p>
     * To prevent a scenario where the player can win by surrendering on the
     * first turn and winning that single round, the score is set to -2.
     * <p>
     * This happened the first time I successfully tested the method.
     *
     * @param sc A scanner to receive user input
     * @throws InterruptedException
     */
    public void surrender(Scanner sc) throws InterruptedException {

        System.out.println("Do you REALLY want to surrender? Y/N");
        String exitInput = Game.cleanStringInput(sc.nextLine());

        // only accepts "Y" to prevent accidents
        if (exitInput.matches("[Yy]")) {

            this.setScore(-2);

            // throw away cards...this will help trigger end of game
            while (this.hand.getCards().size() > 1) {
                this.hand.removeCard();
            }

            // Not the best way, but it works
            System.out.println(this.getName() + " throws away all their cards!"
                + "\nBut plays their last card anyway.");
            Thread.sleep(1000);

        } else {
            System.out.println("Then the war rages on!");
            Thread.sleep(1000);
        }
    }
}
