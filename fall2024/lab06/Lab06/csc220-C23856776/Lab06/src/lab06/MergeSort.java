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
     * For example, if the implementation is O(n^2), use Math.pow(n, 2).
     *
     * @param n the input size
     * @return the value of the function of n inside the O() notation
     */
    
    
    public double O(int n) {
    	
    	//MergeSort has a time complexity of O(n log n)
    	
    	return n * Math.log(n) / Math.log(2);
    	
    	
    }
    

    /**
     * Calculates the constant c using a given input array of type E.
     * Time units should be converted to microseconds for accurate predictions.
     *
     * @param array the input array used for timing the sort
     */
    
    
    public void fit(E[] array) {
    	
    	
        long startTime = System.nanoTime();
        
        sort(array); 
        
        long endTime = System.nanoTime();
        
        double elapsedTimeInSeconds = (endTime - startTime) / 1_000_000.0; // convert nano to milliseconds
        
        double nLogN = O(array.length);
        
        this.c = elapsedTimeInSeconds / nLogN; // calculate c 
        
        		
        		
    }

    /**
     * Predicts the running time of a merge sort for a given input size n.
     *
     * @param n the input size
     * @return the estimated time in microseconds to sort an array of size n
     */
    
    
    public double predict(int n) {
    	
    	
    	return c * O(n); // use c and the time complexity to return

    }

    /**
     * Sorts the input array using the merge sort algorithm.
     *
     * @param array the unsorted array
     * @return the sorted array
     */
    
    
    public E[] sort(E[] array) {
    	
        if (array.length <= 1)
        	
            return array; // means that it has already been sorted
        
        E[] sorted = array.clone(); // clone
        
        E[] array2 = sorted.clone(); // TEMP ARRAY
        
        sort(sorted, array2, 0, array.length - 1); // RECURSIVE SORTING
        
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
    	
    	
    	
    	
        // TODO: Assignment Part 2
        if (first >= last) {
        	
            return;
        
    }

        int middle = (first + last) / 2; // middle 
        
        sort(array1, array2, first, middle); // sorting the 1st half
        
        sort(array1, array2, middle + 1, last); // sorting the 2nd half
        
        merge(array1, array2, first, middle, last); // MERGE
        
}
         
       private void merge(E[] array1, E[]array2, int first, int middle, int last) {
    	  

        int i = first;
        
        int a = first;
        
        int b = middle + 1;
        
        while (a <= middle && b <= last) {
        	
        	
        	if (array1[a].compareTo(array1[b]) <= 0) {
        		
        		array2[i++] = array1[a++]; // if element is smaller in 1st array
        		
        		array2[i++] = array1[b++]; // otherwise, take it from the 2nd array 
        		
        	} else {
        		
        		array2[i++] = array1[b++]; 
        		
        	}
        	
        	
        }
        
        while (a <= middle) {
        	
        	array2[i++] = array1[a++];
        	
        	
        }
        
        while (b <= last) {
        	
        	array2[i++] = array1[b++];
        	
        	
        }
        
        System.arraycopy(array2, first, array1, first, last - first + 1);
    }
}
