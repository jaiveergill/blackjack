package BlackJack;

import java.util.Random;

public class Deck {
    public Card[] cards = new Card[52];
    public String[] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
    public String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

    
    public void initDeck() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                cards[(i)*4 + (j)] = new Card(values[j], suits[i]);
            }
        }
    }

    public void shuffleDeck() {
        Random rand = new Random();
		
		for (int i = 0; i < cards.length; i++) {
			int randomIndexToSwap = rand.nextInt(cards.length);
			Card temp = cards[randomIndexToSwap];
			cards[randomIndexToSwap] = cards[i];
			cards[i] = temp;
		}
    }
}

