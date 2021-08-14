/**
 * I have marked my territory.  It still works. - Chris
 *
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



}