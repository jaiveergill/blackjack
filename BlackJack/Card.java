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

    public String toString() {
        return value + " of " + suit; 
    }

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
            str = String.format(" ___________\n" +
                                "|%s          |\n" +
                                "|           |\n" +
                                "|           |\n" +
                                "|     %s     |\n" +
                                "|           |\n" +
                                "|           |\n" +
                                "|__________%s|\n", this.value, this.suit.charAt(0), this.value);
        }

        return str;
    }

}
