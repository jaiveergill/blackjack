package BlackJack;

import java.util.Scanner;

public class Person {
    public int wins = 0;
    public String name;
    public Hand hand;

    /**
     * @param newHand the hand to set to the person
     */
    public void setHand(Hand newHand) {
        hand = newHand;
    }

    /**
     * @param newCard card to add to the hand
     */
    public void addCardToHand(Card newCard) {
        hand.addCard(newCard);
    }

    /**
     * @return get the value of the person's hand
     */
    public int getHandValue() {
        return hand.getValue();
    }
}
