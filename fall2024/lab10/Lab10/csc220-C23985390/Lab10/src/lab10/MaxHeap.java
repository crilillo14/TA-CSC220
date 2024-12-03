package lab10;


public class MaxHeap {
    
    protected int[] theData;
    
    protected int size;

    public MaxHeap(int maxsize) {
    	initializeHeapArray(maxsize);
        size = 0;
    }

    protected void initializeHeapArray(int maxsize) {
        theData = new int[maxsize];
        for (int i = 0; i < maxsize; i++) {
            theData[i] = 0;
        }
    }


    public MaxHeap(int[] arr){

        this.size = arr.length;
        this.theData = new int[size];
        System.arraycopy(arr, 0, theData, 0, size);

        for (int i = size / 2 - 1; i >= 0; i--) {
            siftDown(i);  
        }
    	
    }


    public int size() {
        return size;
    }


    protected int parent(int pos) {
        return (pos - 1) / 2;
    }


    protected int leftChild(int pos) {
        return 2 * pos + 1;
    }


    protected int rightChild(int pos) {
        return 2 * pos + 2;
    }


    protected boolean isLeaf(int pos) {
        return (pos >= size / 2) && (pos < size);
    }


    protected void swap(int i, int j) {
        int value = theData[i];
        theData[i] = theData[j];
        theData[j] = value;
    }


    public String toString() {
        String s = "";
        for (int i = 0; i < theData.length; i++)
            s += theData[i] + ", ";
        s += "\n";
        return s;
    }


    public void printSideways() {
        printSideways(0, 0);
    }

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

    public void offer(int element) {
        theData[size] = element;
        int current = size;

        size++;

        while (current > 0 && theData[current] > theData[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    
    }

   
    public int poll() {
    	if (size == 0) {
            return -1;
        }

        int max = theData[0];

        theData[0] = theData[size - 1];

        theData[size - 1] = 0;

        size--;

        percDown(0);

        return max;
    }
    protected void percDown(int index) {
        while (true) {
            int left = leftChild(index);
            int right = rightChild(index);
            int largest = index;

            if (left < size && theData[left] > theData[largest]) {
                largest = left;
            }

            if (right < size && theData[right] > theData[largest]) {
                largest = right;
            }

            if (largest == index) {
                break;
            }

            swap(index, largest);
            
            index = largest;
        }
    }


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

 
    protected void heapify(int index) {
        if (index == 0) {
            return;
        }

        for (int i = parent(index); i >= 0; i--) {
            siftDown(i);
        }
    }


    protected void siftDown(int index) {
    	while (true) {
            int left = leftChild(index);    
            int right = rightChild(index);  
            int largest = index;            

            if (left < size && theData[left] > theData[largest]) {
                largest = left;
            }

            if (right < size && theData[right] > theData[largest]) {
                largest = right;
            }

            if (largest == index) {
                break;
            }

            swap(index, largest);

            index = largest;
        }
    	
    	
    }
}
