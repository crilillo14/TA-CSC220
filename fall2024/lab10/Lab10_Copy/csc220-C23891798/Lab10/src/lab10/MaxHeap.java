package lab10;

/**
 * MaxHeap class represents a max heap, a complete binary tree where each node's
 * value is greater than or equal to the values of its children. This class
 * provides methods to perform heap operations like insertion, deletion, and
 * sorting.
 */
public class MaxHeap {
	
    
    /** Array to store the elements of the heap */
    protected int[] theData; 
    
    /** Number of current elements inside the heap */
    protected int size; // tells you whats in the heap 

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
    	// contruct an empty array that can hold up a max amount of elements 
        // TODO Lab Part 2
    }

    /**
     * Constructor that initializes a heap and organizes the input array to
     * form a valid max heap.
     * 
     * @param arr the input array to be organized into a max heap
     */
    public MaxHeap(int[] arr){
    	size = 0;
    	theData = new int[arr.length];
    	for(int i = 0; i < arr.length; i++) {
    		offer(arr[i]);
    	}
        // TODO for Assignment Part 2
    }

    /**
     * Returns the current number of elements in the heap.
     * @return the current size of the heap
     */
    public int size() {
        return size;
    }

    /**
     * Returns the index of the parent node for a given position.
     * @param pos the position of the child node
     * @return the index of the parent node
     */
    protected int parent(int pos) {
        return (pos - 1) / 2; // gets the parent 
    }

    /**
     * Returns the index of the left child for a given position.
     * @param pos the position of the parent node
     * @return the index of the left child node
     */
    protected int leftChild(int pos) {
        return 2 * pos + 1;
    }

    /**
     * Returns the index of the right child for a given position.
     * @param pos the position of the parent node
     * @return the index of the right child node
     */
    protected int rightChild(int pos) {
        return 2 * pos + 2;
    }

    /**
     * Determines if the node at the given position is a leaf.
     * @param pos the position of the node in the heap
     * @return true if the node is a leaf, false otherwise
     */
    protected boolean isLeaf(int pos) {
        return (pos >= size / 2) && (pos < size);
    }

    /**
     * Swaps the elements at two specified positions in the heap array.
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
     * @return a comma-separated string of the heap elements
     * NOTE this method should *not* be modified.
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < theData.length; i++)
            s += theData[i] + ", ";
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
     * Recursively prints the subtree rooted at the specified index, using indentation
     * to indicate the level of each node.
     * @param root_indx the index of the root of the subtree
     * @param level the depth level for indentation
     * NOTE this method should not be modified.
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
     * @param arr the array to compare with the heap's data
     * @return true if the heap data matches the input array, false otherwise
     * NOTE this method should not be modified.
     */
    public boolean IsEqual(int[] arr) {
        if (arr.length != theData.length)
            return false;

        if (arr.length == 0)
            return true;

        for (int i = 0; i < arr.length; i++)
            if (arr[i] != theData[i])
                return false;

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
    	// adds to the heap; add to the end of the heap and swap upwards with the parent if 
    	// element added is bigger than the  maxheap; swap until the problem is resolved 
        // TODO for Lab Part 3
    	
    	theData[size] = element;
    	int position = size; 
    	while(theData[position] > theData[parent(position)]){
    		swap(position, parent(position)); // change the current parent to the new element offered
    		position = parent(position); 
    		
    	}
    	size++; 
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
    public int poll() { // remove first from the heap and return it 
    	// replace it with the last thing 
    	// set last element to zero 
        // TODO for Lab Part 4
    	if (size == 0) {
    		return -1; 
    	}
    	
    	int position = 0; 
    	int maximum = theData[position]; 
    	swap(size - 1, 0); 
    	theData[size - 1] = 0; 
    	size --; 
    	while(theData[position] < theData[leftChild(position)] || theData[position] < theData[leftChild(position)]) {
    		if(theData[position] < theData[leftChild(position)]) {
            	swap(position, leftChild(position)); 
            	position = leftChild(position);
    		}
    		else if(theData[position] < theData[rightChild(position)]) {
               	swap(position, rightChild(position)); 
            	position = rightChild(position);
    		}
    	}
    	return maximum;
    	
        
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
    protected void siftDown(int index) {
        // TODO Assignment Part 1
    	if(!isLeaf(index) && leftChild(index) < size() && rightChild(index) < size()) {
			if(theData[index] < theData[leftChild(index)] || theData[index] < theData[rightChild(index)]) {
				if(theData[leftChild(index)] > theData[rightChild(index)]) {
					swap(index, leftChild(index));
				}
				else {
					swap(index, rightChild(index));
				}
				siftDown(leftChild(index));
				siftDown(rightChild(index));
			}
		}
	}
   
}