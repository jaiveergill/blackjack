package BlackJack;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BlackJackGame {
    public static Deck deck = new Deck(); // initialize instance of Deck
    public static Scanner sc = new Scanner(System.in); // Scanner to take input 
    public static Boolean gameNotOver = true; // main loop
    public static List<Player> players = new ArrayList<Player>(); // all players
    public static Dealer dealer = new Dealer();

    public static void main(String[] args) {
        initPlayers();
        deck.initDeck();
        deck.shuffleDeck();
        dealCards();

        // just show everyone's hands at beginning
        for (Player p: players) {
            displayHand(p);
        }

        displayHand(dealer); 
        
        // MAIN LOOP
        while (gameNotOver) {
            System.out.println("Dealer hand: ");
            displayHand(dealer); // show dealer hand because players usually would't know it
            nextTurn();
            try {
                Thread.sleep(1000); // pause so they can see results
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // new game process
        System.out.println("GAME OVER ");
        List<Player> copyList = new ArrayList<Player>(players);
        for (Player p: players) {
            System.out.println(p.name + ", would you like to play again? (Y/N)"); // making sure everyone still wants to play
            String res = sc.next(); 
            
            if (res.equals("N")) {
                copyList.remove(p);
            }
        }
        players = copyList;
        System.out.println("Would any new players like to join? (Y/N)"); // new players?
        String res = sc.next();
        if (res.equals("Y")) {
            initPlayers();
            gameNotOver = true;
            main(args);
        } else if (players.size() == 0) {
            System.out.println("Thanks! That's it for blackjack!"); // no players left
        } else {
            gameNotOver = true;
            main(args);
        }
        
    }

    /**
     * Used to see how many players are playing and get all of their names
     * Used at the start and when checking for new players
     */
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
    
    /**
     * Give all players two cards from the top of the deck and remove the cards from 
     */
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

    /**
     * Main turn loop function for each player's turn
     * also check's if game is over
     */
    public static void nextTurn() {
        for (int i = 0; i < players.size(); i++) {
            Boolean isNotOver = isGameOver();
            if (!isNotOver) {
                return;
            }
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
                System.out.println("Your hand value is " + players.get(i).hand.getValue());
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
            Card newCard = new Card(deck.cards.get(0).value, deck.cards.get(0).suit, true);
            System.out.println("Dealer hits " + newCard.toString() + ". Total hand value: " + dealer.hand.getValue());
            dealer.hand.addCard(newCard);
            deck.cards.remove(0);
        }
    }

    /**
     * @param p, Person being displayed
     * useful function to show each player's hand
     */
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

    /**
     * @return Boolean value for whether game is over
     * checks many scenarios for whether game is over
     */
    public static Boolean isGameOver() {
        if (dealer.hand.getValue() == 21) {
            for (Player p: players) {
                if (p.hand.getValue() == 21) {
                    System.out.println(p.name + " and the dealer tie!");
                    p.wins += 0.5;
                    gameNotOver = false;
                    return false;
                } else {
                    System.out.println("Dealer blackjacks! They had " + dealer.hand.getValue());
                    gameNotOver = false;
                    return false;
                }
            }
        } else if (dealer.hand.getValue() < 21) {
            for (Player p: players) {
                if (p.hand.getValue() == 21) {
                    System.out.println(p.name + " blackjacks! They had " + p.hand.getValue());
                    p.blackjacked = true;
                    p.wins ++;
                    gameNotOver = false;
                    return false;
                } else if (p.hand.getValue() > 21) {
                    System.out.println(p.name + " busts!");
                    p.setBusted();
                }
            }
        } else if (dealer.hand.getValue() > 21) {
            System.out.println("Dealer busts!");
            System.out.println("They had " + dealer.hand.getValue());
            String winner = "No one";
            int max = 0;
            for (Player p: players) {
                if (p.hand.getValue() > max && !p.busted) {
                    max = p.hand.getValue();
                    winner = p.name;
                }
            }
            System.out.println(winner + " `!");
            gameNotOver = false;
            return false;
        }

        int busted = 0;
        for (Player p: players) {
            if (p.busted) {
                busted ++;
            }
        }
        
        if (busted == players.size()) {
            System.out.println("All players have busted! Dealer wins!");
        }
        
        
        int stood = 0;
        for (Player p: players) {
            if (p.stood && !dealer.shouldDraw()) {
                continue;
            } else {
                if (p.busted) {
                    continue;
                }
                return true;
            }
        }

        System.out.println("All players have stood!");
        for (Player p:players) {
            if (dealer.hand.getValue() > p.hand.getValue()) {
                System.out.println("Dealer wins!");
                System.out.println("Dealer had a hand of " + dealer.hand.getValue());
                for (Player pl: players) {
                    System.out.println(pl.name + " had a hand of " + pl.hand.getValue());
                }
                gameNotOver = false;
                return false;
            } else {
                System.out.println(p.name + " has beat the dealer!");
                p.wins ++;
                gameNotOver = false;
                return false;
            }
        }
        return true;

    }

}


