package arabiannights;

public class GrumpyGenie extends Genie {

    public GrumpyGenie() {
        super(1);
    }

    @Override
    public String toString() {
        if (this.getRemainingWishes() == 0) {
            return "Grumpy genie has granted a wish.";
        } else {
            return "Grumpy genie has a wish to grant";
        }
    }
    
}
