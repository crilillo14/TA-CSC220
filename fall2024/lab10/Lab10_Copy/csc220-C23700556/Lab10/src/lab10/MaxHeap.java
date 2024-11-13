package lab10;

/**
 * MaxHeap class represents a max heap, a complete binary tree where each node's
 * value is greater than or equal to the values of its children. This class
 * provides methods to perform heap operations like insertion, deletion, and
 * sorting.
 */
public class MaxHeap {
    protected int[] theData;
    protected int size;

   
    public MaxHeap(int maxsize) {
    	theData = new int[maxsize];
    	size = 0;
    }

    
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

    
    public void offer(int element) {
        
    	theData[size] = element;
    	int position = size;
    	while(theData[position] > theData[parent(position)]) {
        	swap(position, parent(position)); 
        	position = parent(position);
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