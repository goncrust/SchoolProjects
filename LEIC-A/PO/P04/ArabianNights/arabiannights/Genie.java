package arabiannights;

public class Genie {
    private int totalWishes;
    private int currWishes;

    public Genie(int wishes) {
        this.totalWishes = wishes;
        this.currWishes = 0;
    }

    public boolean grantWish() {
        if (getRemainingWishes() == 0) {
            return false;
        } else {
            this.incrementWish();
            return true;
        }
    }

    public int getGrantedWishes() {
        return this.currWishes;
    }

    public int getTotalWishes() {
        return this.totalWishes;
    }

    public int getRemainingWishes() {
        return this.getTotalWishes() - this.getGrantedWishes();
    }

    public void incrementWish() {
        this.currWishes++;
    }
}
