import java.util.Scanner;

public class HumanPlayer extends Player {

    GroupOfCards hand = new GroupOfCards();

    public HumanPlayer(String name) {
        super(name);
    }

    public void surrender() {
        // TODO - implement HumanPlayer.surrender
        throw new UnsupportedOperationException();
    }

    @Override
    public PlayingCard chooseCard() {

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
                inputInt = Integer.parseInt(inputStr);

                // must be LESS than max...if size = 1, then "max index" == 0
                if (inputInt < max) {
                    validInput = true;
                } else {
                    System.out.println("OOPS! Enter between 0 and "
                        + (max - 1) + "!"); // "between 0 and 0" is acceptable
                } // end check for acceptable index
            } else {
                System.out.println("OOPS! Enter a single digit ONLY!");
            } // end check for digit entered
        } // end "while-loop" check for valid input

        // if we're here, we can safely return the selected card
        return (PlayingCard) hand.removeCard(inputInt);
    }
}
