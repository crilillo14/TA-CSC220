package lab06;

import java.util.concurrent.TimeUnit;

public class MergeSort<E extends Comparable<E>> {

    /** The constant used in the time formula t = c * O() */
    private double c;

    /**
     * Sorts the input array using the merge sort algorithm.
     *
     * @param array the unsorted array
     * @return the sorted array
     */
    public E[] sort(E[] array) {
        if (array.length <= 1) {
            return array;
        }

        E[] sorted = array.clone();
        E[] tempArray = array.clone();  // Temporary array for merging
        mergeSort(sorted, tempArray, 0, array.length - 1);

        return sorted;
    }

    /**
     * Recursively splits the array into two halves and merges them.
     */
    private void mergeSort(E[] array, E[] tempArray, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, tempArray, left, middle);
            mergeSort(array, tempArray, middle + 1, right);
            merge(array, tempArray, left, middle, right);
        }
    }

    /**
     * Merges two sorted subarrays into a single sorted array.
     */
    private void merge(E[] array, E[] tempArray, int left, int middle, int right) {
        for (int i = left; i <= right; i++) {
            tempArray[i] = array[i];
        }

        int i = left, j = middle + 1, k = left;

        while (i <= middle && j <= right) {
            if (tempArray[i].compareTo(tempArray[j]) <= 0) {
                array[k] = tempArray[i];
                i++;
            } else {
                array[k] = tempArray[j];
                j++;
            }
            k++;
        }

        while (i <= middle) {
            array[k] = tempArray[i];
            i++;
            k++;
        }
    }

    /**
     * Calculates the order O() of the MergeSort implementation.
     * MergeSort is O(n log n).
     *
     * @param n the input size
     * @return the value of the function n * log(n)
     */
    public double O(int n) {
        return n * Math.log(n) / Math.log(2);  // Base-2 logarithm
    }

    /**
     * Measures the runtime and calculates the constant c using the given input array.
     * Time units are converted to microseconds.
     *
     * @param array the input array used for timing the sort
     */
    public void fit(E[] array) {
        // Measure the start time
        long startTime = System.nanoTime();

        // Sort the array
        sort(array);

        // Measure the end time
        long endTime = System.nanoTime();

        // Calculate the time in microseconds
        long timeElapsed = TimeUnit.NANOSECONDS.toMicros(endTime - startTime);

        // Calculate the constant c using the formula: time = c * O(n)
        int n = array.length;
        c = (double) timeElapsed / O(n);
    }

    /**
     * Predicts the running time of merge sort for a given input size n.
     *
     * @param n the input size
     * @return the estimated time in microseconds to sort an array of size n
     */
    public double predict(int n) {
        return c * O(n);
    }
}

