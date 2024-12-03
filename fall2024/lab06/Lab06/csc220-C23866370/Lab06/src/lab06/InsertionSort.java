package lab06;

import java.util.concurrent.TimeUnit;

/**
 * Implementation of the Insertion Sort algorithm with performance prediction functionality.
 * The class allows sorting of generic elements that implement the Comparable interface
 * and includes methods to estimate running time based on input size.
 *
 * @param <E> the type of elements to be sorted, which must implement Comparable
 */
public class InsertionSort<E extends Comparable<E>> {

    /** The constant used in the time formula t = c * O() */
    private double c;

    /**
     * Calculates the order O() of the implementation.
     * Since insertion sort is O(n^2), we use Math.pow(n, 2).
     *
     * @param n the input size
     * @return the value of the function of n inside the O() notation
     */
    public double O(int n) {
        return Math.pow(n, 2); // Insertion sort is O(n^2)
    }

    /**
     * Calculates the constant c using a given input array of type E.
     * Measures the time taken to sort the array and calculates c based on the runtime.
     * Time units are converted to microseconds for accurate predictions.
     *
     * @param array the input array used for timing the sort
     */
    public void fit(E[] array) {
        // Measure the start time
        long startTime = System.nanoTime();
        
        // Sort the array using insertion sort
        sort(array);

        // Measure the end time
        long endTime = System.nanoTime();

        // Calculate the time in microseconds
        long timeElapsed = TimeUnit.NANOSECONDS.toMicros(endTime - startTime);

        // Calculate c using the formula: time = c * O(n)
        int n = array.length;
        c = (double) timeElapsed / O(n);  // Divide time by n^2 to get the constant c
    }

    /**
     * Predicts the running time of an insertion sort for a given input size n.
     *
     * @param n the input size
     * @return the estimated time in microseconds to sort an array of size n
     */
    public double predict(int n) {
        // Predict time based on the constant c and O(n) = n^2
        return c * O(n);
    }

    /**
     * Sorts the input array using the insertion sort algorithm.
     *
     * @param array the unsorted array
     * @return the sorted array
     */
    public E[] sort(E[] array) {
        // Handle case where length of array is 1 or less
        if (array.length <= 1) {
            return array;
        }

        // Make a copy of the array to sort, as we must not modify the original array
        E[] sorted = array.clone();

        // Perform the insertion sort algorithm
        for (int i = 1; i < sorted.length; i++) {
            E current = sorted[i];
            int j = i - 1;

            // Shift larger elements one position to the right
            while (j >= 0 && sorted[j].compareTo(current) > 0) {
                sorted[j + 1] = sorted[j];
                j--;
            }

            // Place the current element in its correct position
            sorted[j + 1] = current;
        }

        return sorted;
    }
}


