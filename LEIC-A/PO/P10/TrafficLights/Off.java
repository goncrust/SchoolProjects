public class Off extends State {

    public Off(TrafficLight tl) {
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
        tl.setState(tl.getPanicState());
        System.out.println("[" + toString() + "] --(panic)-> [" + tl.getPanicState().toString() + "]");
    }

    @Override
    public void off() {
        System.out.println("[" + toString() + "] --(off)-> [" + toString() + "]");
    }

    @Override
    public String toString() {
        return "Blinking";
    }
}
