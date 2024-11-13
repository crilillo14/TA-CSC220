package lab10;

public class MaxHeap {
    private int[] theData;
    private int size;

    public MaxHeap(int maxsize) {
        theData = new int[maxsize];
        size = 0;
    }

    public MaxHeap(int[] arr) {
        theData = new int[arr.length];
        System.arraycopy(arr, 0, theData, 0, arr.length);
        size = arr.length;
        heapify(size - 1);
    }

    public void offer(int element) {
        if (size == theData.length) {
            resize();
        }
        theData[size] = element;
        size++;
        siftUp(size - 1);
    }

    public int poll() {
        if (size == 0) {
            return -1;
        }

        int root = theData[0];
        theData[0] = theData[size - 1];
        size--;
        siftDown(0);
        return root;
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

    private void heapify(int index) {
        if (index == 0) {
            return;
        }

        for (int i = parent(index); i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftDown(int index) {
        int maxIndex = index;
        int leftChild = leftChild(index);
        int rightChild = rightChild(index);

        if (leftChild < size && theData[leftChild] > theData[maxIndex]) {
            maxIndex = leftChild;
        }

        if (rightChild < size && theData[rightChild] > theData[maxIndex]) {
            maxIndex = rightChild;
        }

        if (index != maxIndex) {
            swap(index, maxIndex);
            siftDown(maxIndex);
        }
    }

    private void siftUp(int index) {
        int parentIndex = parent(index);
        while (index > 0 && theData[index] > theData[parentIndex]) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = parent(index);
        }
    }

    private void swap(int i, int j) {
        int temp = theData[i];
        theData[i] = theData[j];
        theData[j] = temp;
    }

    private void resize() {
        int[] newArray = new int[theData.length * 2];
        System.arraycopy(theData, 0, newArray, 0, theData.length);
        theData = newArray;
    }

    private int parent(int pos) {
        return (pos - 1) / 2;
    }

    private int leftChild(int pos) {
        return 2 * pos + 1;
    }

    private int rightChild(int pos) {
        return 2 * pos + 2;
    }

    public boolean IsEqual(int[] arr) {
        if (arr.length != theData.length)
            return false;

        for (int i = 0; i < arr.length; i++)
            if (arr[i] != theData[i])
                return false;

        return true;
    }
}
