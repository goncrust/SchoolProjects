public class Panic extends State {

    public Panic(TrafficLight tl) {
        super(tl);
    }

    @Override
    public void tick() {
        System.out.println("[" + toString() + "] --(tick)-> [" + toString() + "]");
    }

    @Override
    public void on() {
        tl.setState(tl.getRedState());
        System.out.println("[" + toString() + "] --(on)-> [" + tl.getRedState().toString() + "]");
    }

    @Override
    public void panic() {
        System.out.println("[" + toString() + "] --(panic)-> [" + toString() + "]");
    }

    @Override
    public void off() {
        System.out.println("[" + toString() + "] --(off)-> [" + toString() + "]");
    }

    @Override
    public String toString() {
        return "Panic";
    }
}
