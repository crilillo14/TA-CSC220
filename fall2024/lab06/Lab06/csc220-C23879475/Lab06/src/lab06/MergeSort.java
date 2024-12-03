package lab06;

/**
 * Implementation of the Merge Sort algorithm with performance prediction functionality.
 * The class allows sorting of generic elements that implement the Comparable interface
 * and includes methods to estimate running time based on input size.
 *
 * @param <E> the type of elements to be sorted, which must implement Comparable
 */
public class MergeSort<E extends Comparable<E>> {

    /** The constant used in the time formula t = c * O() */
    private double c;

    /**
     * Calculates the order O() of the implementation.
     * For example, if the implementation is O(n log n), return n * log(n).
     *
     * @param n the input size
     * @return the value of the function of n inside the O() notation
     */
    public double O(int n) {
        return n * Math.log(n);  // O(n log n)
    }

    /**
     * Calculates the constant c using a given input array of type E.
     * Time units should be converted to microseconds for accurate predictions.
     *
     * @param array the input array used for timing the sort
     */
    public void fit(E[] array) {
        double start = System.nanoTime();
        sort(array);  // Sort the array to measure the time
        double end = System.nanoTime();
        double timeElapsed = (end - start) / 1000.0;  // Convert to microseconds
        c = timeElapsed / O(array.length);  // Calculate constant c
    }

    /**
     * Predicts the running time of a merge sort for a given input size n.
     *
     * @param n the input size
     * @return the estimated time in microseconds to sort an array of size n
     */
    public double predict(int n) {
        return c * O(n);  // t = c * O(n)
    }

    /**
     * Sorts the input array using the merge sort algorithm.
     *
     * @param array the unsorted array
     * @return the sorted array
     */
    public E[] sort(E[] array) {
        if (array.length <= 1)
            return array;
        E[] sorted = array.clone();  // Make a copy of the array
        E[] auxArray = sorted.clone();  // Auxiliary array for merging
        sort(sorted, auxArray, 0, array.length - 1);  // Recursive sort
        return sorted;
    }

    /**
     * Recursively sorts the input array using merge sort.
     *
     * @param array1 the input array to be sorted
     * @param array2 a temporary array used for merging
     * @param first the starting index of the range to sort
     * @param last the ending index of the range to sort
     */
    private void sort(E[] array1, E[] array2, int first, int last) {
        if (first >= last)
            return;

        int middle = (first + last) / 2;
        sort(array1, array2, first, middle);  // Sort the first half
        sort(array1, array2, middle + 1, last);  // Sort the second half

        int i = first;
        int a = first;
        int b = middle + 1;

        // Merge two sorted halves into array2
        while (a <= middle && b <= last) {
            if (array1[a].compareTo(array1[b]) <= 0) {
                array2[i] = array1[a];
                a++;
            } else {
                array2[i] = array1[b];
                b++;
            }
            i++;
        }

        // Copy remaining elements from the first half
        while (a <= middle) {
            array2[i] = array1[a];
            a++;
            i++;
        }

        // Copy remaining elements from the second half
        while (b <= last) {
            array2[i] = array1[b];
            b++;
            i++;
        }

        // Copy the merged subarrays back into array1
        System.arraycopy(array2, first, array1, first, last - first + 1);
    }
}

