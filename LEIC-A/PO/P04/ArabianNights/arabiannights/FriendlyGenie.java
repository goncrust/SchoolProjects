package arabiannights;

public class FriendlyGenie extends Genie {

    public FriendlyGenie(int wishes) {
        super(wishes);
    }

    @Override
    public String toString() {
        return "Frienldy genie has granted " + this.getGrantedWishes() + " wishes and still has " + this.getRemainingWishes() + " to grant.";
    }

}
