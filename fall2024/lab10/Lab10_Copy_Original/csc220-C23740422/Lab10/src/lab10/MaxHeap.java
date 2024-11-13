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
        this.theData = new int[maxsize];
        this.size = 0;  // Initialize the heap size to 0
    }

    /**
     * Constructor that initializes a heap and organizes the input array to
     * form a valid max heap.
     * 
     * @param arr the input array to be organized into a max heap
     */
    public MaxHeap(int[] arr){
        this.theData = arr.clone();
        this.size = arr.length;
        buildHeap();
    }

    /**
     * Returns the current number of elements in the heap.
     * @return the current size of the heap
     */
    public int size() {
        return size;
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
            throw new IllegalStateException("Heap is full");
        }
        theData[size] = element;
        size++;
        heapifyUp(size - 1);  // Restore heap property
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
        if (size == 0) return -1; // If the heap is empty, return -1

        int max = theData[0];  // Store the max value to return
        theData[0] = theData[size - 1];  // Move last element to root
        size--;  // Reduce heap size
        siftDown(0);  // Restore heap property by sifting down
        return max;
    }

    /**
     * Sorts the given array in place using the heap sort algorithm.
     * @param arr the array to be sorted
     * pre: the array is unordered
     * post: the array is sorted in ascending order
     * NOTE do *not* modify the signatures of sort(), heapify(), or siftDown()
     */
    public void sort(int[] arr) {
        this.theData = arr;
        this.size = arr.length;

        heapify(size - 1);  // Build the max heap from the array
        while (size > 1) {
            swap(0, size - 1);  // Move max to the end
            size--;  // Reduce heap size for the next iteration
            siftDown(0);  // Restore heap property
        }
    }

    /**
     * Helper method to reorganize the array into a valid max heap structure.
     * Starts at the last parent node and calls siftDown() on each parent node
     * until the root.
     * @param index the index of the last node to consider for heapification
     */
    private void heapify(int index) {
        for (int i = parent(index); i >= 0; i--) {
            siftDown(i);  // Sift down each internal node
        }
    }

    /**
     * Helper method for sort; recursively sifts down a node to maintain
     * the max-heap property. 
     * 
     * If a child (left or right child of the index) has a larger value than 
     * the parent, the largest child value is swapped with the parent, 
     * and the node is sifted down until no sifting is needed 
     * or there are no more children.
     * 
     * @param index the index of the node to sift down
     * TODO Assignment Part 1
     */
    private void siftDown(int index) {
        while (index < size / 2) {  // While index has at least one child
            int leftChild = leftChild(index);
            int rightChild = rightChild(index);
            int largestChild = leftChild;

            // Check if the right child exists and is greater than left child
            if (rightChild < size && theData[rightChild] > theData[leftChild]) {
                largestChild = rightChild;
            }

            // If the largest child is less than or equal to the parent, we're done
            if (theData[index] >= theData[largestChild]) break;

            // Swap the current node with the largest child
            swap(index, largestChild);
            index = largestChild;  // Move index to the child's position
        }
    }

    /**
     * Helper method to sift up a node to maintain the max-heap property.
     * @param index the index of the node to sift up
     */
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = parent(index);
            if (theData[index] <= theData[parentIndex]) break;
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    /**
     * Helper method to build the max heap from an arbitrary array.
     */
    private void buildHeap() {
        for (int i = parent(size - 1); i >= 0; i--) {
            siftDown(i);
        }
    }
}
