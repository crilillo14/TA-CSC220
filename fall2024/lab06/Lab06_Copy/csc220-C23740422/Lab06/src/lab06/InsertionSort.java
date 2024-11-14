package lab06;


public class InsertionSort<E extends Comparable<E>> {

    /** The constant used in the time formula t = c * O() */
    private double c;

    
    public double O(int n) {
        return Math.pow(n, 2); 
    }

    
    public void fit(E[] array) {
        // Clone the array to avoid sorting the original
        E[] clone = array.clone();

        // Measure start time
        long startTime = System.nanoTime();
        
        // Sort the array
        sort(clone);
        
        // Measure end time
        long endTime = System.nanoTime();

        // Calculate elapsed time in microseconds
        double elapsedTime = (endTime - startTime) / 1000.0;

        // Calculate c by dividing the elapsed time by O(n)
        int n = array.length;
        c = elapsedTime / O(n);
    }

    
    public double predict(int n) {
        
        return c * O(n);
    }

   
    public E[] sort(E[] array) {
        // Handle case where length of array is 1 or less
        if (array.length <= 1) {
            return array;
        }

        // Make a copy of the array to avoid modifying the original
        E[] sorted = array.clone();

        // Perform the insertion sort
        for (int i = 1; i < sorted.length; i++) {
            E key = sorted[i];
            int j = i - 1;

            // Move elements that are greater than key to one position ahead
            while (j >= 0 && sorted[j].compareTo(key) > 0) {
                sorted[j + 1] = sorted[j];
                j--;
            }
            sorted[j + 1] = key;
        }

        return sorted;
    }
}
