/**
 * To play the game of war, run this method!
 *
 * @author Lucas Donegan
 * @author Chris Klammer
 * @author Kemon Brown
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {

        War war = War.getInstance();
        war.play();

    }
}
