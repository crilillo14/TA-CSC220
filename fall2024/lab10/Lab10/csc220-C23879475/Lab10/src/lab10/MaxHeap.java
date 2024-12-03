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
    	theData = new int[maxsize];
        this.size = 0; 
    }

    /** constructor that initializes a heap and put the values of the input
        array in it in such a way that the constructed max heap is valid.
        @param arr the input array
     */
    public MaxHeap(int[] arr){
    	this.size = arr.length;
        theData = new int[arr.length];
        for (int i=0; i<arr.length; i++) {
        	theData[i] = arr[i];
        	heapify(i);
        }
        //sort(theData);
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

    public void printSideways(){
        printSideways(0, 0);
    }

   
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

    public int poll() {
    	if (size == 0) {
    		return -1;
    	} else {
    		int maxVal = theData[0];
    		swap(0, --size);
    		theData[size] = 0;
    		int current = 0;
    		
    		while ((current < size) && ((theData[current] < theData[leftChild(current)])||(theData[current] < theData[rightChild(current)]))) {
    			if (theData[leftChild(current)]> theData[rightChild(current)]){
    				swap(current, leftChild(current));
        			current = leftChild(current);   				
    			} else { 	
    				swap(current, rightChild(current));
        			current = rightChild(current);
    			}
    		}
    		return maxVal;
    	}   	
    }

    public void offer(int element) {
    	if (size >= theData.length) {
    		return;
    	}
    	theData[size] = element;
    	int current = size;
    	
    	while (theData[current] > theData[parent(current)]) {
    		swap(current, parent(current));
    		current = parent(current);
    		
    	}
    	size++;
    	
    }


  
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
		
		//int current = 0;
		int maxIndex = index;
		
		if((leftChild(index) < size) && (theData[leftChild(index)] > theData[maxIndex])){
			maxIndex = leftChild(index);			
		}
		
		if((rightChild(index) < size) && (theData[rightChild(index)] > theData[maxIndex]) ) {
			maxIndex = rightChild(index);
		}
		
		if (index != maxIndex) {
			swap(index, maxIndex);
			siftDown(maxIndex);				

		}
		

     	}

	
}
	
