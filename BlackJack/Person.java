package BlackJack;

import java.util.Scanner;

public class Person {
    public int wins = 0;
    public String name;
    public Hand hand;

    public void setHand(Hand newHand) {
        hand = newHand;
    }

    public void addCardToHand(Card newCard) {
        hand.addCard(newCard);
    }

    public int getHandValue() {
        return hand.getValue();
    }
}
