
import java.util.Iterator;

public class TesteIterator implements Iterable<Integer> {
    
    Integer _nums[];

    public TesteIterator(int size) {
        _nums = new Integer[size];

        for (int i = 0; i < size; i++) {
            _nums[i] = i;
        }
    }

    public class TesteIteratorIterator implements Iterator<Integer> {

        TesteIterator ti;
        int index;

        public TesteIteratorIterator(TesteIterator ti) {
            this.ti = ti;
            this.index = 0;

        }

        @Override
        public boolean hasNext() {
            return index < ti._nums.length;
        }

        @Override
        public Integer next() {
            return ti._nums[index++];
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new TesteIteratorIterator(this);
    }

    public static void main(String[] args) {
        TesteIterator ti = new TesteIterator(9);

        for (Integer i : ti) {
            System.out.println(i);
        }
    }

}
