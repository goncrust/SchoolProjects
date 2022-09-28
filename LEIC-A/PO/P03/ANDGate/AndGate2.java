
public class AndGate2 {

    private boolean p1;
    private boolean p2;

    public AndGate2() {
        this.p1 = false;
        this.p2 = false;
    }

    public AndGate2(boolean value) {
        this.p1 = value;
        this.p2 = value;
    }

    public AndGate2(boolean value1, boolean value2) {
        this.p1 = value1;
        this.p2 = value2;
    }

    public boolean getP1() {
        return this.p1;
    }

    public boolean getP2() {
        return this.p2;
    }

    public void setP1(boolean value) {
        this.p1 = value;
    }

    public void setP2(boolean value) {
        this.p2 = value;
    }

    public void setP2s(boolean value1, boolean value2) {
        this.setP1(value1);
        this.setP2(value2);
    }

    public boolean getOutput() {
        return this.p1 && this.p2;
    }

    @Override
    public String toString() {
        return "A: " + this.p1 + " B: " + this.p2;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof AndGate2) {
            AndGate2 ag = (AndGate2) o;
            return ag.getP1() == this.p1 && ag.getP2() == this.p2;
        }
        return false;
    }
}
