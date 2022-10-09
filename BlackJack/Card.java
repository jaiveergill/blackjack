package BlackJack;


public class Card {
    public String value;
    public String suit;

    public Card(String value, String suit) {
        this.value=value;
        this.suit=suit;
    }

    public String toString() {
        return value + " of " + suit; 
    }

    public String visRep() {
        String str = String.format(" ___________" +
                     "|%s         %s|" +
                     "|           |" +
                     "|           |" +
                     "|           |" +
                     "|           |" +
                     "|           |" +
                     "|___________|", this.value, this.suit.indexOf(0));

        return str;
    }

}
