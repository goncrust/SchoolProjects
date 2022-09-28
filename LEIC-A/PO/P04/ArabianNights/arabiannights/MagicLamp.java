package arabiannights;

public class MagicLamp {

    private int genies;
    private int currGenies;
    private int demons = 0;

    public MagicLamp(int genies) {
        this.genies = genies;  
        this.currGenies = genies;
    }

    public Genie rub(int wishes) {
        
        if (this.currGenies == 0) {
            return new RecyclableDemon();
        }

        if ((this.genies - this.currGenies) % 2 == 0) {
            this.currGenies--;
            return new FriendlyGenie(wishes);
        } else {
            this.currGenies--;
            return new GrumpyGenie();
        }

    }

    public void feedDemon(RecyclableDemon demon) {
        this.currGenies = genies;
        this.demons++;
        demon.use();
    }

    public int getGenies() {
        return this.currGenies;
    }

    public int getDemons() {
        return this.demons;
    }

    public int getCapacity() {
        return this.genies;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof MagicLamp) {
            MagicLamp ml = (MagicLamp) o;
            return ml.getDemons() == this.demons && ml.getGenies() == this.currGenies && ml.getCapacity() == this.genies;
        }
        return false;
    }
}
