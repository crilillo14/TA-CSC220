 package lab10;

/**
 * MaxHeap class represents a max heap, a complete binary tree where each node's
 * value is greater than or equal to the values of its children. This class
 * provides methods to perform heap operations like insertion, deletion, and
 * sorting.
 */
public class MaxHeap {

    /**
     * Array to store the elements of the heap
     */
    protected int[] theData;

    /**
     * Number of current elements inside the heap
     */
    protected int size;

    /**
     * Creates a heap with specified capacity.
     *
     * Initializes an empty heap array of given size with all elements set to
     * zero.
     *
     * @param capacity maximum size of the heap
     */
    public MaxHeap(int capacity) {
        size = 0;
        theData = new int[capacity];
    }

    /**
     * Creates a heap from an existing array.
     *
     * Builds a valid max heap by inserting array elements sequentially.
     *
     * @param inputArr the array to convert into a heap
     */
    public MaxHeap(int[] inputArr) {
        size = 0;
        theData = new int[inputArr.length];

        for (int i = 0; i < inputArr.length; i++) {
            offer(inputArr[i]);
        }
    }

    /**
     * Returns the current number of elements in the heap.
     *
     * @return the current size of the heap
     */
    public int size() {
        return size;
    }

    /**
     * Returns the index of the parent node for a given position.
     *
     * @param pos the position of the child node
     * @return the index of the parent node
     */
    protected int parent(int pos) {
        return (pos - 1) / 2;
    }

    /**
     * Returns the index of the left child for a given position.
     *
     * @param pos the position of the parent node
     * @return the index of the left child node
     */
    protected int leftChild(int pos) {
        return 2 * pos + 1;
    }

    /**
     * Returns the index of the right child for a given position.
     *
     * @param pos the position of the parent node
     * @return the index of the right child node
     */
    protected int rightChild(int pos) {
        return 2 * pos + 2;
    }

    /**
     * Determines if the node at the given position is a leaf.
     *
     * @param pos the position of the node in the heap
     * @return true if the node is a leaf, false otherwise
     */
    @SuppressWarnings("unused")
	protected boolean isLeaf(int pos) {
        return (pos >= size / 2) && (pos < size);
    }

    /**
     * Swaps the elements at two specified positions in the heap array.
     *
     * @param i the index of the first element
     * @param j the index of the second element
     */
    protected void swap(int i, int j) {
        int value = theData[i];
        theData[i] = theData[j];
        theData[j] = value;
    }

    /**
     * Returns a string representation of the heap in array form.
     *
     * @return a comma-separated string of the heap elements NOTE this method
     * should *not* be modified.
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < theData.length; i++) {
            s += theData[i] + ", ";
        }
        s += "\n";
        return s;
    }

    /**
     * Prints the tree structure of the heap with indentation indicating depth.
     * NOTE this method should not be modified.
     */
    public void printSideways() {
        printSideways(0, 0);
    }

    /**
     * Recursively prints the subtree rooted at the specified index, using
     * indentation to indicate the level of each node.
     *
     * @param root_indx the index of the root of the subtree
     * @param level the depth level for indentation NOTE this method should not
     * be modified.
     */
    protected void printSideways(int root_indx, int level) {
        if (root_indx < theData.length) {
            printSideways(rightChild(root_indx), level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("       ");
            }
            System.out.println(theData[root_indx]);
            printSideways(leftChild(root_indx), level + 1);
        }
    }

    /**
     * Checks if the contents of the heap array match the specified array.
     *
     * @param arr the array to compare with the heap's data
     * @return true if the heap data matches the input array, false otherwise
     * NOTE this method should not be modified.
     */
    public boolean IsEqual(int[] arr) {
        if (arr.length != theData.length) {
            return false;
        }

        if (arr.length == 0) {
            return true;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != theData[i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Adds new element to heap.
     *
     * Places element at end and bubbles up to maintain heap order.
     *
     * @param newElement the element to add
     */
    public void offer(int newElement) {
        if (size >= theData.length) {
            return;
        }

        theData[size] = newElement;
        int currentPos = size;

        while (currentPos > 0) {
            int parentPos = parent(currentPos);
            if (theData[parentPos] < theData[currentPos]) {
                swap(currentPos, parentPos);
                currentPos = parentPos;
            } else {
                break;
            }
        }

        size++;
    }

    /**
     * Removes and returns the maximum element.
     *
     * Moves last element to root and sifts down to restore heap order.
     *
     * @return maximum element or -1 if empty
     */
    public int poll() {
        if (size == 0) {
            return -1;
        }

        int maxVal = theData[0];
        theData[0] = theData[size - 1];
        theData[size - 1] = 0;
        size--;

        if (size > 0) {
            siftDown(0);
        }

        return maxVal;
    }

    /**
     * Sorts array using heap sort algorithm.
     *
     * Heapifies array then repeatedly extracts max element.
     *
     * @param inputArr array to be sorted
     */
    public void sort(int[] inputArr) {
        this.theData = inputArr;
        this.size = inputArr.length;

        heapify(size - 1);
        while (size > 1) {
            swap(0, size - 1);
            size--;
            siftDown(0);
        }
    }

    /**
     * Helper method to reorganize the array into a valid max heap structure.
     * Starts at the last parent node and calls siftDown() on each parent node
     * until the root.
     *
     * @param index the index of the last node to consider for heapification
     */
    protected void heapify(int index) {
        if (index == 0) {
            return;
        }

        for (int i = parent(index); i >= 0; i--) {
            siftDown(i);
        }
    }

    /**
     * Helper method for sort; recursively sifts down a node to maintain the
     * max-heap property.
     *
     * If a child (left or right child of the index) has a larger value than the
     * parent, the largest child value is swapped with the parent, and the node
     * is sifted down until no sifting is needed or there are no more children.
     *
     * @param index the index of the node to sift down TODO Assignment Part 1
     */
    protected void siftDown(int index) {
        // TODO Assignment Part 1

        // Get the left child of current index
        int left = leftChild(index);

        // Continue while there is at least a left child (if left >= size, node is a
        // leaf)
        while (left < size) {
            // Get the right child index
            int right = rightChild(index);
            // Variable to store index of the larger child
            int max;

            // Find which child is larger
            // First check if right child exists (right < size)
            // Then compare the values at right and left
            if (right < size && theData[right] > theData[left]) {
                max = right; // Right child is larger
            } else {
                max = left; // Left child is larger (or right child doesn't exist)
            }

            // If current node is >= the larger child, heap property is satisfied
            if (theData[index] >= theData[max]) {
                break; // Exit loop as no more sifting needed
            }

            // Otherwise, swap current node with the larger child
            swap(index, max);

            // Move down to the child we swapped with
            index = max;

            // Update left child for next iteration
            left = leftChild(index);

        }

    }
}
