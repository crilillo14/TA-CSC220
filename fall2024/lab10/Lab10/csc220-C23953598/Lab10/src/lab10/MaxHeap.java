package lab10;

public class MaxHeap {
    /** internal representation of the heap */
    protected int[] theData;
    /** store the number of current elements inside the heap */
    protected int size;

    /** constructor to initialize the status of the objects of this class
        based on the input parameter (i.e., size)
        @param maxsize the size for the heap
     */
    public MaxHeap(int maxsize) {
        size = 0;
        theData = new int[maxsize];
        
        
    }

    /** constructor that initializes a heap and put the values of the input
        array in it in such a way that the constructed max heap is valid.
        @param arr the input array
     */
    public MaxHeap(int[] arr){
        size = 0;
        theData = new int[arr.length];
        
        for(int i = 0; i < arr.length; i++) {
        	offer(arr[i]);
        }
        
    }

    public int size() {
        return size;
    }

    protected int parent(int pos) {
    	return (pos-1)/2;
    }

    protected int leftChild(int pos) {
    	return 2*pos + 1;
    }

    protected int rightChild(int pos) {
    	return 2*pos + 2;
    }

    protected boolean isLeaf(int pos) {
        return (pos >= size/2) && (pos < size);
    }


    /** Swap the items with index i and index j in the heap array.
        @param i index of first item in heap
        @param j index of second item in theData
     */
    protected void swap(int i, int j) {
        int value = theData[i];
        theData[i] = theData[j];
        theData[j] = value;
    }


    /** Returns a string containing contents of the heap as an array
        NOTE this method should *not* be modified.
     */
    public String toString(){
        String s = "";
        for (int i = 0; i < theData.length; i++)
            s += theData[i] + ", ";
        s += "\n";
        return s;
    }

    /** Prints the tree contents, one per line, following an inorder traversal
        and using indentation to indicate node depth; prints right to left so
        that it looks correct when the output is rotated;
        NOTE this method should not be modified.
     */
    public void printSideways(){
        printSideways(0, 0);
    }

    /** Prints in reversed preorder the tree with given root, indenting each
        line to the given level
        @param root_indx the given root
        @param level indentation level
        NOTE this method should not be modified.
     */
    protected void printSideways(int root_indx, int level){
        if (root_indx < theData.length){
            printSideways(rightChild(root_indx), level+1);
            for (int i = 0; i < level; i++){
                System.out.print("       ");
            }
            System.out.println(theData[root_indx]);
            printSideways(leftChild(root_indx), level+1);
        }
    }

    /**
     tests if the contents of the heap are equal to an input array
     @param arr the input array
     NOTE this method should not be modified.
    */
    public boolean IsEqual(int[] arr){
        if (arr.length != theData.length)
            return false;

        if (arr.length == 0)
            return true;

        for (int i = 0; i < arr.length; i++)
            if (arr[i] != theData[i])
                return false;

        return true;
    }


    /** Remove an item from the heap.
      pre: theData is in heap order.
      post: Removed maximum item, theData is in heap order.
      @return The item with the maximum value or -1 if empty.
     */
    public int poll() {
        if(theData[0] == 0) {
        	return -1;
        }
    	int heapRoot = theData[0];
        int pos = 0;
        swap(size-1,0);
        theData[size - 1] = 0;
        size--;
        
        while(theData[pos] < theData[leftChild(pos)] || theData[pos] < theData[rightChild(pos)]) {
        	
        	if(theData[leftChild(pos)] > theData[rightChild(pos)]) {     // checks if the left is > than right
        		swap(pos,leftChild(pos));                                // if so, swaps pos with left
        		pos = leftChild(pos);
        	}
        	if(theData[leftChild(pos)] < theData[rightChild(pos)]) {       // vice versa
        		swap(pos,rightChild(pos));
        		pos = rightChild(pos);
        	}
        }
        return heapRoot;
    }

    /** Insert an element into the heap.
      pre:  theData is in heap order.
      post: The element is in the heap and
            theData is in heap order.
      @param element The element to be inserted
     */
    public void offer(int element) {
    	theData[size] = element;
    	int pos = size;
    	while(theData[pos] > theData[parent(pos)]) {
    		swap(pos,parent(pos));
    		pos = parent(pos);
    	}
        size++;
    }


    /** in-place heap sort algorithm.
        @param arr the input (unsorted) array to be sorted.
        TODO finish writing siftDown() for Assignment10
        NOTE do *not* modify the signatures of sort(), heapify(), or siftDown()
     */
    public void sort(int[] arr) {
		this.theData = arr;
        this.size = arr.length;

        heapify(size-1);
		while(size > 1) {
			swap(0, size-1);
			size--;
            siftDown(0);
		}
    }


	protected void heapify(int index) {

		if(index == 0) {
			return;
		}

		for (int i = parent(index); i >= 0; i--) {
			siftDown(i);
		}

	}

	protected void siftDown(int index) {

        // While a child has a larger value than its parent, the largest child
        // value (either leftChild(index) or rightChild(index)) is swapped with
        // the parent (element at index). index should be updated accordingly
        // before going round the loop again.
		
		if(isLeaf(index)) {
			return;
		}
		
		if(leftChild(index) < size && rightChild(index) < size) {
			
			if(theData[index] < theData[leftChild(index)] && theData[leftChild(index)] > theData[rightChild(index)]) {
				swap(index,leftChild(index));
			}
			if(theData[index] < theData[rightChild(index)] && theData[leftChild(index)] < theData[rightChild(index)]) {
				swap(index,rightChild(index));
			}
			siftDown(leftChild(index));
			siftDown(rightChild(index));
			
		
		}
        // TODO for Assignment10
	}

}