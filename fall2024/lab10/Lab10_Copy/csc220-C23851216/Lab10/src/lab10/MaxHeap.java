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
        // TODO for Lab10
    	theData = new int[maxsize];
    	size = 0;
    }

    /** constructor that initializes a heap and put the values of the input
        array in it in such a way that the constructed max heap is valid.
        @param arr the input array
     */
    public MaxHeap(int[] arr){
        // TODO for Assignment10
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


    public String toString(){
        String s = "";
        for (int i = 0; i < theData.length; i++)
            s += theData[i] + ", ";
        s += "\n";
        return s;
    }

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
        // TODO for Lab10
    	if(size == 0) {
    		return -1;
    	}
    	int position = 0;
    	int maximum = theData[position];
    	swap(size - 1 , 0);
    	theData[size - 1] = 0;
    	size--;
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

    /** Insert an element into the heap.
      pre:  theData is in heap order.
      post: The element is in the heap and
            theData is in heap order.
      @param element The element to be inserted
     */
    public void offer(int element) {
        // TODO for Lab10
    	theData[size] = element;
    	int position = size;
    	while(theData[position] > theData[parent(position)]) {
        	swap(position, parent(position)); 
        	position = parent(position);
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


        // TODO for Assignment10
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