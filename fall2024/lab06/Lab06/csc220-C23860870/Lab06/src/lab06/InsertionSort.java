package lab06;

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
     * For example, if the implementation is O(n^2), use Math.pow(n, 2).
     *
     * @param n the input size
     * @return the value of the function of n inside the O() notation
     */
    public double O(int n) {
        //TODO: Lab Part 4.1
    	return Math.pow(n, 2);
    }

    /**
     * Calculates the constant c using a given input array of type E.
     * Time units should be converted to microseconds for accurate predictions.
     *
     * @param array the input array used for timing the sort
     */
    public void fit(E[] array) {
        //TODO: Lab Part 4.2
    	long startTime = System.nanoTime();  
        
        sort(array);
        
        long endTime = System.nanoTime();  
        long timeTaken = endTime - startTime;  
        
        double timeInMicroseconds = timeTaken / 1000.0;
        
        int n = array.length;
        double O_n = O(n);  
        
        
        this.c = timeInMicroseconds / O_n;
    }

    /**
     * Predicts the running time of an insertion sort for a given input size n.
     *
     * @param n the input size
     * @return the estimated time in microseconds to sort an array of size n
     */
    public double predict(int n) {
        //TODO: Lab Part 4.3
    	double O_n = O(n); 
        return this.c * O_n; 
    }

    /**
     * Sorts the input array using the insertion sort algorithm.
     *
     * @param array the unsorted array
     * @return the sorted array
     */
    public E[] sort(E[] array) {
        //TODO: Lab Part 3

        /* Handle case where length of array is 1 or less */
    	if (array.length <=1) {
    		return array;
    	}

        /* make a copy of the array to sort */
        E[] sorted = array.clone();
       

        /* Perform the insertion sort */
        for(int i=1; i < sorted.length; i++){
        	
            E key = sorted[i];
            int j = i - 1;
            
            while (j >= 0 && ((Comparable<E>) sorted[j]).compareTo(key) > 0) {
            	sorted[j + i] = sorted[j];
            	j = j - 1;
            	
            }
            
        	sorted [j + 1] = key;
        	
        }

        return sorted;
    }

}
