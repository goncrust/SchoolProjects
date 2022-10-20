public class App {
    /**
     * @param args
     */
    public static void main(String[] args) {
        TrafficLight light = new TrafficLight();
        System.out.println("Light status: " + light.status());
        light.off();
        light.panic();
        light.on();
        light.tick();
        light.tick();
        light.tick();
        light.tick();
        light.panic();
        light.off();
        light.on();
        light.off();
    }
}
