/**
 * AI Opponent plays the game with the player.  It will choose cards on its own.
 * <p>
 * Currently, it has only 5 "Personality types":
 * <p>
 * 1: Always play the highest card it has
 * 2: Play cards at random *** (this is the default "difficulty level")
 * 3: Always play the lowest card it has
 * 4: Play the lowest card it has, except in "War", then it plays the highest
 * 5: Like number 4, but prefers to save Aces even during War.
 * <p>
 * @author Chris Klammer 2021
 */
public class AiOpponent extends Player {

    private int personalityType;

    /**
     * Creates an AiOpponent player with given values. Also known as a
     * Computer Player.
     *
     * @see {@link #chooseCard}
     * @param personalityType A difficulty level/strategy for this player
     * @param name A label (name) for this AI player.
     */
    public AiOpponent(int personalityType, String name) {
        super(name);
        setPersonalityType(personalityType);
    }

    /**
     * Based on the AI player's personality type (difficulty level), it will
     * choose different cards.
     * 1. Always uses the {@link #findHighestCard} method
     * 2. Always selects a card at random (default behaviour/switch case)
     * 3. Always uses the {@link #findLowestCard} method
     * 4. During war, uses {@link #findHighestCard} and {@link #findLowestCard}
     * otherwise
     * 5. Like 4, but has a unique {@link #findTenSaveAce} method.
     *
     * @return A selected playing card based on an algorithm.
     */
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

    /**
     * Searches a group of cards for the highest card, and selects that card.
     *
     * @return The index of the highest value/strength card in a group of cards.
     */
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

    /**
     * Searches a group of cards for the lowest card, and selects that card.
     *
     * @return An index for the lowest value/strength card in a group of cards.
     */
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

    /**
     * Searches a group of cards for a card with a value of 10, and selects it.
     * If there are no 10's, then it selects the highest card.
     *
     * @see {@link #findHighestCard}
     * @return An index for a card with a value/strength of 10, OR the highest
     * value.
     */
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

    /**
     * Retrieve the personality type/difficulty level of this AI player
     *
     * @return The value of the personality type/difficulty level of the player
     */
    public int getPersonalityType() {
        return this.personalityType;
    }

    /**
     * Set the personality type/difficulty level of this AI/computer player.
     *
     * @param personalityType The type/difficulty level of the AI player
     */
    public void setPersonalityType(int personalityType) {

        if (personalityType < 1) {

            // set to low difficulty 
            this.personalityType = 1;
        }
        this.personalityType = personalityType;
    }
}
