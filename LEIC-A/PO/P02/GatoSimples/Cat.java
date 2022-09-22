public class Cat {

    private String name;
    private int age;
    private double weight;

    public Cat(String name, int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Cat) {
            Cat otherCat = (Cat) o;
            return this.name.equals(otherCat.name) && this.age == otherCat.age &&
                this.weight == otherCat.weight;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.name + " (cat) (" + this.age + ":" + this.weight + ")";
    }
}
