package BlackJack;

public class Dealer extends Person {

    public Dealer() {
        super();
        //TODO Auto-generated constructor stub
    }

    public Boolean shouldDraw() {
        if (hand.getValue() < 17) {
            return true;
        }
        return false;
    }
}
