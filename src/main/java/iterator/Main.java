import java.util.Iterator;

interface Sequence {
    Iterator<Integer> iterator();
}
class FibonacciIterator implements Iterator<Integer> {
    private int previous = 0;
    private int current = 1;

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        int nextValue = previous + current;
        previous = current;
        current = nextValue;
        return previous;
    }
}

class FibonacciSequence implements Sequence {
    @Override
    public Iterator<Integer> iterator() {
        return new FibonacciIterator();
    }
}

public class Main {
    public static void main(String[] args) {
        Sequence fibonacciSequence = new FibonacciSequence();

        Iterator<Integer> iterator = fibonacciSequence.iterator();

        for (int i = 0; i < 10; i++) {
            System.out.println(iterator.next());
        }
    }
}
