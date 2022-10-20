public abstract class State {

    protected TrafficLight tl;

    public State(TrafficLight tl) {
        this.tl = tl;
    }
    
    public abstract void tick();

    public abstract void panic();

    public abstract void off();

    public abstract void on();
}
