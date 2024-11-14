package lab06;


public class MergeSort<E extends Comparable<E>> {

    /** The constant used in the time formula t = c * O() */
    private double c;

   
    public double O(int n) {
        // Merge Sort has time complexity O(n log n)
        return n * Math.log(n) / Math.log(2); // Log base 2
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
        // Use the calculated constant c to predict runtime for input size n
        return c * O(n);
    }

   
    public E[] sort(E[] array) {
        if (array.length <= 1)
            return array;
        E[] sorted = array.clone();
        E[] array2 = sorted.clone();
        sort(sorted, array2, 0, array.length - 1);
        return sorted;
    }

 void sort(E[] array1, E[] array2, int first, int last) {
        if (first >= last)
            return;

        int middle = (first + last) / 2;
        sort(array1, array2, first, middle);
        sort(array1, array2, middle + 1, last);

        int i = first;
        int a = first;
        int b = middle + 1;

        // Merge the two halves
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

        // Copy the remaining elements from the first half, if any
        while (a <= middle) {
            array2[i] = array1[a];
            a++;
            i++;
        }

        // Copy the remaining elements from the second half, if any
        while (b <= last) {
            array2[i] = array1[b];
            b++;
            i++;
        }

        // Copy the merged subarrays back into array1
        System.arraycopy(array2, first, array1, first, last - first + 1);
    }
}
