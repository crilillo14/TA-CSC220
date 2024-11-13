package lab10;

/**
 * MaxHeap class represents a max heap, a complete binary tree where each node's
 * value is greater than or equal to the values of its children. This class
 * provides methods to perform heap operations like insertion, deletion, and
 * sorting.
 */
public class MaxHeap {
    /** Array to store the elements of the heap */
    private int[] theData;

    /** Number of current elements inside the heap */
    private int size;

    /**
     * Constructor to initialize the heap array with a specified size.
     *
     * This function will create the array (heap) with the specified size
     * and initialize all of its elements to be zero. You need to be careful
     * about whether any other field needs to be initialized at this stage
     *
     * @param maxsize the size for the heap
     */
    public MaxHeap(int maxsize) {
        theData = new int[maxsize];
        size = 0;
    }

    /**
     * Constructor that initializes a heap and organizes the input array to
     * form a valid max heap.
     *
     * @param arr the input array to be organized into a max heap
     */
    public MaxHeap(int[] arr) {
        theData = arr.clone(); // Clone the array to avoid modifying the original
        size = arr.length;
        buildMaxHeap();
    }

    /**
     * Checks if the contents of the heap array match the specified array.
     * @param arr the array to compare with the heap's data
     * @return true if the heap data matches the input array, false otherwise
     */
    public boolean IsEqual(int[] arr) {
        if (arr.length != theData.length) { // Compare the entire array length
            return false;
        }
        for (int i = 0; i < theData.length; i++) {
            if (arr[i] != theData[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Inserts an element into the heap, maintaining the max-heap property.
     *
     * Adds element to the latest available position in the heap array,
     * then adjusts the heap to maintain the max heap property
     *
     * @param element the element to be added to the heap
     * pre: theData is in heap order
     * post: the element is added and theData is in heap order
     */
    public void offer(int element) {
        if (size >= theData.length) {
            throw new IndexOutOfBoundsException("Heap is full");
        }
        theData[size] = element;
        int current = size;
        size++;

        while (current > 0 && theData[current] > theData[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    /**
     * Removes and returns the maximum element in the heap (root).
     *
     * To maintain the Heap Property:
     * Swap the root with the last leaf,
     * then sift the new root down to restore heap property
     *
     * If the heap is empty, returns -1.
     *
     * @return the maximum element in the heap, or -1 if empty
     * pre: theData is in heap order
     * post: the max item is removed, and theData is in heap order
     */
    public int poll() {
        if (size == 0) {
            return -1;
        }
        int maxValue = theData[0];
        theData[0] = theData[size - 1];
        theData[size - 1] = 0; // Clear the last element
        size--;
        siftDown(0);
        return maxValue;
    }

    /**
     * Sorts the given array in place using the heap sort algorithm.
     * @param arr the array to be sorted
     * pre: the array is unordered
     * post: the array is sorted in ascending order
     * NOTE do *not* modify the signatures of sort(), heapify(), or siftDown()
     */
    public void sort(int[] arr) {
        theData = arr;
        size = arr.length;

        buildMaxHeap();
        for (int i = size - 1; i > 0; i--) {
            swap(0, i);
            size--;
            siftDown(0, size);
        }
    }

    /**
     * Helper method to reorganize the array into a valid max heap structure.
     * Starts at the last parent node and calls siftDown() on each parent node
     * until the root.
     */
    private void buildMaxHeap() {
        for (int i = (size / 2) - 1; i >= 0; i--) {
            siftDown(i, size);
        }
    }

    /**
     * Helper method for sort; iteratively sifts down a node to maintain
     * the max-heap property.
     *
     * If a child (left or right child of the index) has a larger value than
     * the parent, the largest child value is swapped with the parent,
     * and the node is sifted down until no sifting is needed
     * or there are no more children.
     *
     * @param index the index of the node to sift down
     */
    private void siftDown(int index) {
        siftDown(index, size);
    }

    /**
     * Helper method for sort; iteratively sifts down a node to maintain
     * the max-heap property within a specific boundary.
     *
     * @param index the index of the node to sift down
     * @param boundary the boundary limit for sifting down (used in sorting)
     */
    private void siftDown(int index, int boundary) {
        while (index < boundary / 2) { // Only non-leaf nodes need sifting down
            int left = leftChild(index);
            int right = rightChild(index);
            int largest = index;

            // Corrected comparison logic to properly select the largest child to swap
            if (left < boundary && theData[left] > theData[largest]) {
                largest = left;
            }
            if (right < boundary && theData[right] > theData[largest]) {
                largest = right;
            }

            // If the largest is still the current index, the heap property is satisfied
            if (largest == index) {
                break;
            }

            // Swap with the largest child and continue
            swap(index, largest);
            index = largest;
        }
    }

    /**
     * Returns the index of the parent node for a given position.
     * @param pos the position of the child node
     * @return the index of the parent node
     */
    private int parent(int pos) {
        return (pos - 1) / 2;
    }

    /**
     * Returns the index of the left child for a given position.
     * @param pos the position of the parent node
     * @return the index of the left child node
     */
    private int leftChild(int pos) {
        return 2 * pos + 1;
    }

    /**
     * Returns the index of the right child for a given position.
     * @param pos the position of the parent node
     * @return the index of the right child node
     */
    private int rightChild(int pos) {
        return 2 * pos + 2;
    }

    /**
     * Swaps the elements at two specified positions in the heap array.
     * @param i the index of the first element
     * @param j the index of the second element
     */
    private void swap(int i, int j) {
        int temp = theData[i];
        theData[i] = theData[j];
        theData[j] = temp;
    }
}