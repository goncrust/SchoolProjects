
public class AndGate3 extends AndGate2 {
    
    private boolean p3;

    public AndGate3() {
        super();
        this.p3 = false;
    }

    public AndGate3(boolean value) {
        super(value);
        this.p3 = value;
    }

    public AndGate3(boolean value1, boolean value2, boolean value3) {
        super(value1, value2);
        this.p3 = value3;
    }

    public boolean getP3() {
        return this.p3;
    }

    public void setP3(boolean value) {
        this.p3 = value;
    }

    public void setP3s(boolean value1, boolean value2, boolean value3) {
        this.setP2s(value1, value2);
        this.setP3(value3);
    }

    @Override
    public boolean getOutput() {
        return super.getOutput() && this.p3;
    }

    @Override
    public String toString() {
        return super.toString() + " C: " + this.p3;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof AndGate3) {
            AndGate3 ag = (AndGate3) o;
            return super.equals(o) && ag.getP3() == this.p3;
        }
        return false;
    }

}
