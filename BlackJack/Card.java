package BlackJack;


public class Card {
    public String value;
    public String suit;
    public Boolean visible;

    public Card(String value, String suit, Boolean visible) {
        this.value=value;
        this.suit=suit;
        this.visible=visible;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return value + " of " + suit; 
    }

    /**
     * @return a String for visual representation of each card
     */
    public String visRep() {
        String str = " ___________\n" +
                     "|░░░░░░░░░░░|\n" +
                     "|░░░░░░░░░░░|\n" +
                     "|░░░░░░░░░░░|\n" +
                     "|░░░░░░░░░░░|\n" +
                     "|░░░░░░░░░░░|\n" +
                     "|░░░░░░░░░░░|\n" +
                     "|░░░░░░░░░░░|\n";
        if (visible) {
            String val = this.value;
            try {
                int x = Integer.parseInt(val);
            } catch (Exception numberFormaException) {
                val = String.valueOf(val.charAt(0));
            }
            str = String.format(" ___________\n" +
                                "|%s          |\n" +
                                "|           |\n" +
                                "|           |\n" +
                                "|     %s     |\n" +
                                "|           |\n" +
                                "|           |\n" +
                                "|__________%s|\n", val, this.suit.charAt(0), val);
           
        }

        return str;
    }

}
