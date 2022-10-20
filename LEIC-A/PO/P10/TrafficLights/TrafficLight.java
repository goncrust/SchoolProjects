public class TrafficLight {
    
    private State state;

    private Green greenState = new Green(this);
    private Yellow yellowState = new Yellow(this);
    private Red redState = new Red(this);
    private Off offState = new Off(this);
    private Panic panicState = new Panic(this);

    public TrafficLight() {
        this.state = offState;
    }

    public String status() {
        return this.state.toString();
    }

    public void tick() {
        this.state.tick();
    }

    public void on() {
        this.state.on();
    }

    public void panic() {
        this.state.panic();
    }

    public void off() {
        this.state.off();
    }

    public void setState(State state) {
        this.state = state;
    }

    public Green getGreenState() {
        return greenState;
    }

    public Yellow getYellowState() {
        return yellowState;
    }

    public Red getRedState() {
        return redState;
    }

    public Off getOffState() {
        return offState;
    }

    public Panic getPanicState() {
        return panicState;
    }



}
