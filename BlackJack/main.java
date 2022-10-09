package BlackJack;
import BlackJack.Card;

import java.security.cert.PKIXBuilderParameters;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.event.SwingPropertyChangeSupport;


public class main {
    public static Card[][] players;
    public static int topCard = 0;
    public static Deck deck;
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("How many players, including the dealer (2-7)?");
        Integer nPlayers = sc.nextInt();
        players = new Card[nPlayers][7];
        
        deck = new Deck();
        deck.initDeck();
        deck.shuffleDeck();
        dealCards(nPlayers);
        showAllHands();
        nextTurn();
        sc.close();
    }

    public static void dealCards(int nPlayers) {

        for (int i = 0; i < nPlayers; i++) {
            Boolean vis = true;
            if (i == 0) {
                vis = false;
            }
            players[i][0] = new Card(deck.cards[topCard].value, deck.cards[topCard].suit, true);
            players[i][1] = new Card(deck.cards[topCard+1].value, deck.cards[topCard+1].suit, vis);
            topCard += 2;
        }
    }

    public static void viewHand(int pNum) {
        for (int i = 0; i < Arrays.stream(players[pNum]).filter(e -> e != null).count(); i++) {
            System.out.println(players[pNum][i].visRep());
        }
    }

    public static void showAllHands() {
        for (int i = 0; i < players.length; i++) {
            System.out.println("Player " + (i+1));
            viewHand(i);
        }
    }

    public static void nextTurn() {
        System.out.println("Dealer goes last.");
        for (int i = 0; i < players.length; i++) {
            if (i == players.length-1) {
                System.out.println("Dealer's turn");
            } else {
                System.out.println("Player " + (i+1) + ", it is your turn. Do you want to hit or stand? Type deck to view your deck.");
                String res = sc.nextLine();
                if (res.contains("hit")) {
                    System.out.println("HIT");
                } else if (res.contains("fold")) {
                    System.out.println("FOLD");
                } else if (res.contains("deck")) {
                    viewHand(i);
                    i --;
                    continue;
                } else {
                    i --;
                    continue;
                }
            }

        }
    }
    
}
