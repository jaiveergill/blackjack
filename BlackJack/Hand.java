package BlackJack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    public List<Card> cards = new ArrayList<Card>();

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    /**
     * display all cards in hand
     */
    public void displayCards() {
        for (int i = 0; i < this.cards.size(); i++) {
            System.out.println(this.cards);
            System.out.println(this.cards.get(i).visRep());
        }
    }

    /**
     * @param card Card to add to hand
     */
    public void addCard(Card card) {
        this.cards.add(card);
    }

    /**
     * @return an Integer value of sum of all cards in the Hand, including aces
     */
    public Integer getValue() {
        int c = 0;
        int aces = 0; // special case for Aces because the can be 1 or 11
        for (int i = 0; i < this.cards.size(); i++) {
            try {
                c += Integer.parseInt(this.cards.get(i).value);
            } catch(Exception NumberFormatException) {
                if (this.cards.get(i).value.equals("Ace")) {
                    aces += 1;
                } else {
                    c += 10;
                }
            }
        }

        while (aces > 0) {
            if (c + 11 <= 21) {
                c += 11;
            } else {
                c ++;
            }
            aces -= 1;
        }
        return c;
    }

}
