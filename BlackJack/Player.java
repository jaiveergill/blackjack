package BlackJack;

import java.util.Scanner;

public class Player extends Person {
    public Boolean busted = false;
    public Boolean blackjacked = false;
    public Boolean stood = false;
    public Player() {
        super();
        
    }

    public String getInput(String msg) {
        Scanner sc = new Scanner(System.in);
        if (name != null) { 
            System.out.println(name + ", " + msg);
        } else {
            System.out.println(msg);
        }
        String response = "";
        response = sc.next();
        sc.close();
        return response;
    }

    public void setBusted() {
        busted = true;
    }

    public void reset() {
        busted = false;
        blackjacked = false;
    }
    
}
