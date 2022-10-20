public class Yellow extends State {

    public Yellow(TrafficLight tl) {
        super(tl);
    }

    @Override
    public void tick() {
        tl.setState(tl.getRedState());
        System.out.println("[" + toString() + "] --(tick)-> [" + tl.getRedState().toString() + "]");
    }

    @Override
    public void on() {
        System.out.println("[" + toString() + "] --(on)-> [" + toString() + "]");
    }

    @Override
    public void panic() {
        tl.setState(tl.getPanicState());
        System.out.println("[" + toString() + "] --(panic)-> [" + tl.getPanicState().toString() + "]");
    }

    @Override
    public void off() {
        tl.setState(tl.getOffState());
        System.out.println("[" + toString() + "] --(off)-> [" + tl.getOffState().toString() + "]");
    }

    @Override
    public String toString() {
        return "Yellow";
    }
}
