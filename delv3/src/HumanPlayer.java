

public class HumanPlayer extends Player {

    public void viewHand() {
        // TODO - implement HumanPlayer.viewHand
        throw new UnsupportedOperationException();
    }

    public void surrender() {
        // TODO - implement HumanPlayer.surrender
        throw new UnsupportedOperationException();
    }

    public void helpMe() {
        System.out.println("----------How to Play----------\n");
        System.out.println("""
        \u201cWar\u201d is a two-player game wherein players choose a card from 
        their hand and reveal their values at the same time as one another.  

        Whoever has the highest value card wins the \u201cbattle\u201d, and gets to keep the pair of cards (or, gets a point).  

        If there is a draw, players each choose another card to reveal at the same time, and this continues until one player wins 

        the \u201cbattle\u201d.  Whoever has the most points in the end wins the game of war.""");
    }

}
