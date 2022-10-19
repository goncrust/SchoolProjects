
import java.util.Comparator;

public class Teste implements Comparable<Integer> {

    public final static TesteComparator TC = new TesteComparator();

    public static class TesteComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2) {
            return i1 - i2;        
        }
    }

    @Override
    public int compareTo(Integer i) {
        return i - 1;
    }

    public static void main(String[] args) {
        System.out.println(Teste.TC.compare(1, 2));

        Teste t = new Teste();
        System.out.println(t.compareTo(10));
    }

}
