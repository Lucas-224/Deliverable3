/**
 * AI Opponent plays the game with the player.  It will choose cards on its own.
 * <p>
 * Currently, it has only one "Personality type": Choosing cards at random.
 * <p>
 */
public class AiOpponent extends Player {

    private int personalityType;


    public AiOpponent(int personalityType, String name) {
        super(name);
        setPersonalityType(personalityType);
    }

    public int getPersonalityType() {
        return this.personalityType;
    }

    /**
     *
     * @param personalityType
     */
    public void setPersonalityType(int personalityType) {

        if (personalityType < 1) {

            // set to low difficulty 
            this.personalityType = 1;
        }
        this.personalityType = personalityType;
    }

    @Override
    public PlayingCard chooseCard() {

        int indexToReturn = 0;

        switch (personalityType) {

            // 1 should be PURPOSELY bad
            case 1:
                this.getHand().shuffle(); // 2 = random card
                break;
            default:
                this.getHand().shuffle(); // 2 = random card
                break;
        }
        return (PlayingCard) getHand().removeCard(indexToReturn);
    }
}
