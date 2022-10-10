package BlackJack;

import java.util.Scanner;

public class Player extends Person {
    public Boolean busted = false;
    public Boolean blackjacked = false;
    public Boolean stood = false;
    public Player() {
        super();
        
    }

    public void setBusted() {
        busted = true;
    }

    public void reset() {
        busted = false;
        blackjacked = false;
    }
    
}
