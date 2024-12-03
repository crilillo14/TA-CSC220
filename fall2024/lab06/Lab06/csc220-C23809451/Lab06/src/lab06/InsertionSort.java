package lab06;

public class InsertionSort<E extends Comparable<E>> {

    /** The constant used in the time formula t = c * O() */
    private double c;

    /**
     * Calculates the order O() of the implementation.
     * For example, if the implementation is O(n^2), use Math.pow(n, 2).
     *
     * @param n the input size
     * @return the value of the function of n inside the O() notation
     */
    public double O(int n) {
        return Math.pow(n, 2);
    }

    /**
     * Calculates the constant c using a given input array of type E.
     * Time units should be converted to microseconds for accurate predictions.
     *
     * @param array the input array used for timing the sort
     */
    public void fit(E[] array) {
    	int n = array.length;
    	
        long startTime = System.nanoTime();
        this.sort(array);
        long endTime = System.nanoTime();
        
        double timeTaken = (endTime - startTime) * (Math.pow(10, -3)); // Convert to microseconds
        
    	this.c = timeTaken / this.O(n);
    }

    /**
     * Predicts the running time of an insertion sort for a given input size n.
     *
     * @param n the input size
     * @return the estimated time in microseconds to sort an array of size n
     */
    public double predict(int n) {
        return this.c * this.O(n);
    }

    public static <E extends Comparable<E>> E[] sort(E[] array) {
        E[] sortedArray = array.clone(); // Create a copy of the input array

        for (int i = 1; i < sortedArray.length; i++) {
            E key = sortedArray[i];
            int j = i - 1;

            // Move elements of sortedArray[0..i-1] that are greater than key
            while (j >= 0 && sortedArray[j].compareTo(key) > 0) {
                sortedArray[j + 1] = sortedArray[j];
                j--;
            }
            sortedArray[j + 1] = key;
        }
        return sortedArray;
    }
}
