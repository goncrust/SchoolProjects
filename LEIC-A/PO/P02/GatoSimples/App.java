public class App {
    public static void main(String[] args) {
        Cat catSweet = new Cat("Sweet", 13, 2.12);
        Cat catSweet2 = new Cat("Sweet", 13, 2.12);

        String s = "Sweet";
        System.out.println(s.equals(catSweet.getName()));

        System.out.println(catSweet.equals(catSweet2));
        System.out.println(catSweet.equals(new Cat("Tareco", 12, 1.3)));
        System.out.println(catSweet);
    }
}
