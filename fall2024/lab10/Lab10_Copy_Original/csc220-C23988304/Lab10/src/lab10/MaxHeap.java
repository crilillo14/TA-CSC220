package lab10;
// TODO left child is (2* index) + 1
// 		right child is (2 * index) + 2
// find parent by (index - 1) / 2



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
        // TODO Lab Part 2
    	this.theData = new int[maxsize];
    	this.size = 0;
    }

    /**
     * Constructor that initializes a heap and organizes the input array to
     * form a valid max heap.
     *<b>i hate this lab <b>
     * @param arr the input array to be organized into a max heap
     */
    public MaxHeap(int[] arr){
    	this(arr.length);
        
    	for(int index = 0; index < arr.length; index++) {
    		this.offer(arr[index]);
    	}// end of for 
    	
    	
    	
//    	
//    	this.sort(arr);
//    	
//    	this.size = arr.length;
//    	
//    	if (this.size == 0) {
//    		this.theData = arr;
//    		return;
//    	}
//    	for (int index = 0; index < this.size; index++) {
//    		if (index == 0) {
//    			this.theData[index] = arr[index];
//    		}
//    		this.offer(arr[index]);
//    	}
    	
//    	for(int index = 0; index < this.size / 2; index ++) {
//    		int jdex = 0;
//    		int temp;
//    		
//    		temp = this.theData[index];
//    		this.theData[index] = this.theData[jdex];
//    		this.theData[jdex] = temp;
//    		
//    		
//    		
//    		
//    	}// end of for 
    	
        
        
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
     * Determines if the node at the given position is a leaf.
     * @param pos the position of the node in the heap
     * @return true if the node is a leaf, false otherwise
     */
    private boolean isLeaf(int pos) {
        return (pos >= size / 2) && (pos < size);
    }

    /**
     * Swaps the elements at two specified positions in the heap array.
     * @param i the index of the first element
     * @param j the index of the second element
     */
    private void swap(int i, int j) {
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
    private void printSideways(int root_indx, int level) {
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
        // TODO for Lab Part 3
    	if(this.size +1 > this.theData.length) {
    		System.out.println("trying to add to a full tree!, error in offer method");
    	}
    
    	int currIndex = this.size;
    	
    	
    	//initial insertion
    	this.theData[currIndex] = element;
    	
    	//need to check parent
    	int parentIndex = this.parent(currIndex);
    	
    	while(this.theData[parentIndex] < this.theData[currIndex]) {
    			this.swap(currIndex, parentIndex);
    		
    			currIndex = this.parent(currIndex);
    			parentIndex = this.parent(parentIndex);
    		
    	}// end of while
    	
    	
    	
    	
    	this.size += 1;
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
    	if (this.size == 0) {
    		return -1;
    	}
        // TODO for Lab Part 4
    	
    	int returnVal = this.theData[0];
    	System.out.println("this is whats being returned " + returnVal);
    	this.theData[0] = 0;
    	
    	
    	
    	
    	//swap last with empty first
    	this.swap(0, size - 1);
    	this.size--;
    	int currIndex = 0;
    	
    	int leftIndex = this.leftChild(currIndex);
    	int rightIndex = this.rightChild(currIndex);
    	
    	int maxChildIndex;
    	if (this.theData[leftIndex] > this.theData[rightIndex] ) {
    		maxChildIndex = leftIndex;
    	}else {
    		maxChildIndex = rightIndex;
    	}
    	
    	
    	while ( this.theData[currIndex] < this.theData[maxChildIndex] ) {
    		this.swap(currIndex, maxChildIndex);
    		currIndex = maxChildIndex;
    		 leftIndex = this.leftChild(currIndex);
        	 rightIndex = this.rightChild(currIndex);
        	
        	 
        	 
        	 
        	 
        	if (this.theData[leftIndex] > this.theData[rightIndex] ) {
        		maxChildIndex = leftIndex;
        	}else {
        		maxChildIndex = rightIndex;
        	}

			
			
    
    	}// end of while loop
    	
    	
    	
        return returnVal ; // Placeholder
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
    private void heapify(int index) {
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
     * the parent, the largest child value is swapped with the parent, and the node is sifted down until no sifting is needed or there are no more children.
     * 
     * @param index the index of the node to sift down
     */
    private void siftDown(int index) {
    	if (this.size == 0) {
    		return;
    	}
    	
    	
    	
    	int currIndex = index;
    	int leftIndex = this.leftChild(currIndex);
    	int rightIndex = this.rightChild(currIndex);
    	
    	int maxChildIndex;
    	if (leftIndex < size && rightIndex < size && this.theData[leftIndex] > this.theData[rightIndex] ) {
    		maxChildIndex = leftIndex;
    	}else {
    		maxChildIndex = rightIndex;
    	}
    	
    	
    	while (leftIndex < size && rightIndex < size && this.theData[currIndex] < this.theData[maxChildIndex] ) {
    		this.swap(currIndex, maxChildIndex);
    		currIndex = maxChildIndex;
    		 leftIndex = this.leftChild(currIndex);
        	 rightIndex = this.rightChild(currIndex);
        	
        	 
        	 
        	 
        	 
        	if (leftIndex < size && rightIndex < size && this.theData[leftIndex] > this.theData[rightIndex] ) {
        		maxChildIndex = leftIndex;
        	}else {
        		maxChildIndex = rightIndex;
        	}

    	}// end of while 
    	
    	
    	
    	
//         int leftChildIndex = this.leftChild(index);
//         int rightChildIndex = this.rightChild(index);
//         int maxIndex = -1;
//         
//          
//          if (leftChildIndex < this.size && rightChildIndex < this.size && this.theData[leftChildIndex] >= this.theData[rightChildIndex] && this.theData[leftChildIndex] > this.theData[index]){
//          maxIndex = leftChildIndex;
//
//          }// end of if
//
//          if (leftChildIndex < this.size && rightChildIndex < this.size &&this.theData[rightChildIndex] >= this.theData[leftChildIndex] && this.theData[rightChildIndex] > this.theData[index]){
//          maxIndex = rightChildIndex;
//
//          }// end of if
         //TODO might break change le to l
          //while (leftChildIndex < this.size && rightChildIndex < this.size &&leftChildIndex <= size && rightChildIndex <= size && maxIndex != -1){
//          swap(index, maxIndex);
//          index = maxIndex;
//
//          leftChildIndex = this.rightChild(index);
//         rightChildIndex = this.rightChild(index);
//           
//          if (leftChildIndex < this.size && rightChildIndex < this.size &&this.theData[leftChildIndex] >= this.theData[rightChildIndex] && this.theData[leftChildIndex] > this.theData[index]){
//          maxIndex = leftChildIndex;
//
//          }// end of if
//
//          if (leftChildIndex < this.size && rightChildIndex < this.size && this.theData[rightChildIndex] >= this.theData[leftChildIndex] && this.theData[rightChildIndex] > this.theData[index]){
//          maxIndex = rightChildIndex;
//
//          }else {
//        	  maxIndex = -1;
//          }


         // }// end of while 


//  left child is (2* index) + 1
// 		right child is (2 * index) + 2
// find parent by (index - 1) / 2


        
        
    }
}
