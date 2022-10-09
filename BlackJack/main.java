package BlackJack;
import BlackJack.Card;
import java.util.Scanner;

import javax.swing.event.SwingPropertyChangeSupport;


public class main {
    public static Card[][] players;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many players, including the dealer (2-7)?");
        Integer nPlayers = sc.nextInt();
        players = new Card[nPlayers][7];
        
        Deck deck = new Deck();
        deck.initDeck();
        deck.shuffleDeck();
        System.out.println(deck.cards);
        sc.close()
    }

    
}
