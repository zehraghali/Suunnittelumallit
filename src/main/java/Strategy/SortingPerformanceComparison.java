package Strategy;

import org.junit.runner.manipulation.Sorter;

import java.util.Random;

public class SortingPerformanceComparison {
    public static void main(String[] args) {

        int smallArraySize = 30;
        int largeArraySize = 100_000;

        int[] smallArray = generateRandomArray(smallArraySize);
        int[] largeArray = generateRandomArray(largeArraySize);

        Sorter sorter = new Sorter();

        System.out.println("Sorting small array:");
        measurePerformance(sorter, smallArray);

        System.out.println("Sorting large array:");
        measurePerformance(sorter, largeArray);
    }

    private static int[] generateRandomArray(int size) {
        Random rand = new Random();
        return rand.ints(size, 0, 1000).toArray(); // Random integers between 0 and 999
    }

    private static void measurePerformance(Sorter sorter, int[] array) {
        SortStrategy[] strategies = { new BubbleSort(), new QuickSort(), new MergeSort() };

        for (SortStrategy strategy : strategies) {
            int[] copyArray = array.clone();
            sorter.toString(strategy);
            long startTime = System.nanoTime();
            sorter.sort(copyArray);
            long endTime = System.nanoTime();
            System.out.printf("%s took %d nanoseconds%n", strategy.getClass().getSimpleName(), (endTime - startTime));
        }
    }
}
