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

            // 1 = Always plays highest card
            case 1:
                indexToReturn = findHighestCard();
                break;
            // Case 2 left blank -- uses "default" of random card
            // 3 = Always plays lowest card...will make a late-game comeback!
            case 3:
                indexToReturn = findLowestCard();
                break;
            // 4 will play low cards UNLESS there's war
            case 4:
                if (this.atWar) {
                    indexToReturn = findHighestCard();

                    // else (if NOT at war)
                } else {
                    indexToReturn = findLowestCard();
                }
                break;
            // 5 is like 4 (play low UNLESS at war), but will save Aces
            case 5:
                if (this.atWar) {
                    indexToReturn = findTenSaveAce();

                    // else (if NOT at war)
                } else {
                    indexToReturn = findLowestCard();
                }
                break;
            default:
                this.getHand().shuffle(); // 2 = random card
        }
        return (PlayingCard) getHand().removeCard(indexToReturn);
    }

    public int findHighestCard() {

        int indexToReturn = 0;
        int bestStrength = 1;

        // find highest card
        for (int i = 0; i < this.getHand().getSize(); i++) {

            // create temp card for each card in the hand
            PlayingCard card
                = (PlayingCard) this.getHand().getCards().get(i);

            // get the strength of the card, compare to previous
            int thisValue = card.getStrength();
            if (thisValue > bestStrength) {

                /* if it's the highest so far, favour this card...
                 * but keep checking!
                 */
                bestStrength = card.getStrength();
                indexToReturn = i;
            }
        }
        return indexToReturn;
    }

    public int findLowestCard() {

        int worstStrength = 11;
        int indexToReturn = 0;

        // find lowest card
        for (int i = 0; i < this.getHand().getSize(); i++) {

            // create temp card for each card in the hand
            PlayingCard card
                = (PlayingCard) this.getHand().getCards().get(i);

            // get the strength of the card, compare to previous
            int thisValue = card.getStrength();
            if (thisValue < worstStrength) {

                /* if it's the lowest so far, favour this card...
                 * but keep checking!
                 */
                worstStrength = card.getStrength();
                indexToReturn = i;
            }
        }
        return indexToReturn;
    }

    public int findTenSaveAce() {

        int indexToReturn = -1; // DON'T LET IT OUT LIKE THIS!  GAME BROKEN!

        // find highest card
        for (int i = 0; i < this.getHand().getSize(); i++) {

            // create temp card for each card in the hand
            PlayingCard card
                = (PlayingCard) this.getHand().getCards().get(i);

            if (card.getStrength() == 10) {
                indexToReturn = i;
            }
        }

        // if indexToReturn is under 0, then there are no 10's.  Find highest!
        if (indexToReturn < 0) {
            indexToReturn = findHighestCard();
        }

        // this should never happen, but JUST IN CASE...
        if (indexToReturn < 0) {
            indexToReturn = 0;
        }
        return indexToReturn;
    }
}
