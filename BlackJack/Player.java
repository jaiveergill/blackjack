package BlackJack;

import java.util.Scanner;

public class Player extends Person {

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
    
}
