import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    public void surrender() {
        // TODO - implement HumanPlayer.surrender
        throw new UnsupportedOperationException();
    }

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

            inputStr = Game.cleanStringInput(sc.next());

            // only supports 0 ~ 9
            if (inputStr.matches("\\d")) {

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
}
