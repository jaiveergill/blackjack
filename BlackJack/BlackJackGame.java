package BlackJack;
import BlackJack.Card;

import java.security.cert.PKIXBuilderParameters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.security.sasl.SaslException;
import javax.swing.event.SwingPropertyChangeSupport;
import javax.swing.plaf.synth.SynthSeparatorUI;


public class BlackJackGame {
    // public static Card[][] players;
    public static int topCard = 0;
    public static Deck deck = new Deck();
    public static Scanner sc = new Scanner(System.in);
    public static Integer dealerHand = 0;
    public static Boolean gameNotOver = true;
    public static List<Player> players = new ArrayList<Player>();
    public static Dealer dealer = new Dealer();

    public static void main(String[] args) {
        initPlayers();
        deck.initDeck();
        deck.shuffleDeck();
        dealCards();

        for (Player p: players) {
            displayHand(p);
        }

        displayHand(dealer);
        
        while (gameNotOver) {
            System.out.println(gameNotOver);
            nextTurn();
            System.out.println("Dealer hand: ");
            displayHand(dealer);
        }

        System.out.println(players.get(0).hand.cards);
        
    }

    public static void initPlayers() {
        System.out.println("How many players want to play?");
        Integer nPlayers = sc.nextInt();
        for (int i = 0; i < nPlayers; i++) {
            Player p = new Player();
            players.add(p);
            System.out.println("What is your name?");
            p.name = sc.next();
        }
    }
    
    public static void dealCards() {
        for (int i = 0; i < players.size(); i++) {
            List<Card> twoCards = new ArrayList<Card>();
            twoCards.add(new Card(deck.cards.get(0).value, deck.cards.get(0).suit, true));
            twoCards.add(new Card(deck.cards.get(1).value, deck.cards.get(1).suit, true));
            players.get(i).setHand(new Hand(twoCards));
            deck.cards.remove(0);
            deck.cards.remove(0);
        }
        List<Card> twoCards = new ArrayList<Card>();
        twoCards.add(new Card(deck.cards.get(0).value, deck.cards.get(0).suit, true));
        twoCards.add(new Card(deck.cards.get(1).value, deck.cards.get(1).suit, false));
        dealer.setHand(new Hand(twoCards));
    }

    public static void nextTurn() {
        for (int i = 0; i < players.size(); i++) {
            isGameOver();
            if (players.get(i).busted || players.get(i).stood) {
                continue;
            }
            System.out.println(players.get(i).name + ", it's your turn. You can hit, stand, or say deck to see your deck.");
            String res = sc.next();
            if (res.contains("hit")) {
                Card newCard = new Card(deck.cards.get(0).value, deck.cards.get(0).suit, true);
                players.get(i).hand.addCard(newCard);
                deck.cards.remove(0);
                System.out.println("You have hit a " + newCard);
            } else if (res.contains("stand")) {
                players.get(i).stood = true;
                System.out.println("You have stood.");
            } else if (res.contains("deck")) {
                players.get(i).hand.displayCards();
                i --;
                continue;
            } else {
                System.out.println("Please enter hit, stand, or deck");
                i --;
                continue;
            }
        }
        if (dealer.shouldDraw()) {
            System.out.println("Dealer hits!");
            Card newCard = new Card(deck.cards.get(0).value, deck.cards.get(0).suit, true);
            dealer.hand.addCard(newCard);
            deck.cards.remove(0);
        }
    }

    public static void displayHand(Person p) {
        if (p.name == null) {
            System.out.println("Dealer");
        } else {
            System.out.println("Player " + p.name);
        }
        for (Card c: p.hand.cards) {
            System.out.println(c.visRep());
        }
    }

    public static void isGameOver() {
        if (dealer.hand.getValue() == 21) {
            for (Player p: players) {
                if (p.hand.getValue() == 21) {
                    System.out.println(p.name + " and the dealer tie!");
                    p.wins += 0.5;
                    gameNotOver = false;
                    return;
                } else {
                    System.out.println("Dealer blackjacks!");
                    gameNotOver = false;
                }
            }
        } else if (dealer.hand.getValue() < 21) {
            for (Player p: players) {
                if (p.hand.getValue() == 21) {
                    System.out.println(p.name + " blackjacks!");
                    p.blackjacked = true;
                    p.wins ++;
                    gameNotOver = false;
                    return;
                } else if (p.hand.getValue() > 21) {
                    System.out.println(p.name + " busts!");
                    p.setBusted();
                }
            }
        } else if (dealer.hand.getValue() > 21) {
            System.out.println("Dealer busts!");
            System.out.println("They had " + dealer.hand.cards);
            gameNotOver = false;
            return;
        }

        for (Player p: players) {
            if (p.busted) {
                continue;
            } else {
                return;
            }
        }
        System.out.println("All players have busted! Dealer wins!");
        gameNotOver = false;


    }

}











// public static void dealCards(int nPlayers) {

//     for (int i = 0; i < nPlayers; i++) {
//         Boolean vis = true;
//         if (i == nPlayers-1) {
//             vis = false;
//         }
//         players[i][0] = new Card(deck.cards[topCard].value, deck.cards[topCard].suit, true);
//         players[i][1] = new Card(deck.cards[topCard+1].value, deck.cards[topCard+1].suit, vis);
//         topCard += 2;
//     }
// }




    // public static void viewHand(int pNum) {
    //     for (int i = 0; i < Arrays.stream(players[pNum]).filter(e -> e != null).count(); i++) {
    //         System.out.println(players[pNum][i].visRep());
    //     }
    // }

    // public static void showAllHands() {
    //     for (int i = 0; i < players.length; i++) {
    //         System.out.println("Player " + (i+1));
    //         viewHand(i);
    //     }
    // }

    // public static void nextTurn() {
    //     System.out.println("Dealer goes last.");
    //     for (int i = 0; i < players.length; i++) {
    //         Integer num = (int) Arrays.stream(players[i]).filter(e -> e != null).count();
    //         if (i == players.length-1) {
    //             System.out.println("Dealer's turn");
    //             if (dealerHand < 17) {
    //                 System.out.println("Dealer hits.");
    //                 players[players.length-1][num+1] = deck.cards[topCard];
    //                 try {
    //                     dealerHand += Integer.parseInt(deck.cards[topCard].value);
    //                 } catch(Exception NumberFormatException) {
    //                     if (deck.cards[topCard].value == "Ace") {
    //                         if (dealerHand < 10) {
    //                             dealerHand++;
    //                         } else {
    //                             dealerHand += 11;
    //                         }
    //                     } else {
    //                         dealerHand += 10;
    //                     }
    //                 }
    //                 checkDealerHand();
    //                 topCard++;
    //             }
    //         } else {
    //             System.out.println("Player " + (i+1) + ", it is your turn. Do you want to hit or stand? Type deck to view your deck.");
    //             String res = sc.nextLine();
    //             if (res.contains("hit")) {
    //                 players[i][num] = deck.cards[topCard];
    //                 players[i][num].visible = false;
    //                 topCard++;
    //                 System.out.println("You have been dealt a card.");
    //             } else if (res.contains("stand")) {
    //                 continue;
    //             } else if (res.contains("deck")) {
    //                 viewHand(i);
    //                 i --;
    //                 continue;
    //             } else {
    //                 i --;
    //                 continue;
    //             }
    //         }

    //     }
    // }

    // public static Integer handValue(Card[] playerHand) {
    //     Integer c = 0;
    //     System.out.println(playerHand[0].value);
    //     Integer num = (int) Arrays.stream(playerHand).filter(e -> e != null).count();
    //     for (int i = 0; i < num; i++) {
    //         System.out.println(i);
    //         try {
    //             Integer convertedValue = Integer.parseInt(playerHand[i].value);
    //             c += convertedValue;
    //         } catch(Exception NumberFormatException) {
    //             c += 10;
    //         }
    //     }
    //     System.out.println("LOOK");
    //     System.out.println(c);

    //     return c;
    // }

    // public static void checkDealerHand() {
    //     if (dealerHand == 21) {
    //         System.out.println("Dealer wins!");
    //     } else if (dealerHand > 21) {
    //         System.out.println("Dealer busts!");
    //     }
    // }
    
    // public static void isGameOver() {
    //     for (int i = 0; i < players.length; i++) {

    //     }
    // }
// }
