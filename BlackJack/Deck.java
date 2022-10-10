package BlackJack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    public List<Card> cards = new ArrayList<Card>();
    public String[] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
    public String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

    
    /**
     * intializes deck with hard values
     */
    public void initDeck() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                Card newCard = new Card(values[i], suits[j], true);
                cards.add(newCard) ;
            }
        }

        
    }

    /**
     * Shuffles deck with random 
     */
    public void shuffleDeck() {
        Random rand = new Random();
		
		for (int i = 0; i < cards.size(); i++) {
			int randomIndexToSwap = rand.nextInt(cards.size());
			Card temp = cards.get(randomIndexToSwap);
			cards.set(randomIndexToSwap, cards.get(i));
			cards.set(i, temp);
		}
    }

    /**
     * Not used but I kept in it case
     */
    public void reset() {
        initDeck();
        shuffleDeck();
    }

}

