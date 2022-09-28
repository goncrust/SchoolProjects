package arabiannights;

public class RecyclableDemon extends Genie {

    private boolean used;

    public RecyclableDemon() {
        super(0);
        this.used = false;
    }

    @Override
    public boolean grantWish() {
        if (!this.used) {
            this.incrementWish();
            return true;
        } else {
            return false;
        }
    }

    public void use() {
        this.used = true;
    }

    @Override
    public String toString() {
        if (this.used) {
            return "Demon has been recycled.";
        } else {
            return "Recyclable demon has granted " + this.getGrantedWishes() + " wishes.";
        }
    }
}
