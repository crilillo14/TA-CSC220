package lab12;

public class MaxHeap {
    public int[] heap;
    public int size; // store the number of current elements inside the heap
    
 
    public MaxHeap(int[] arr){
    		this(arr.length);
    		for(int i=0; i<heap.length; i++) {
    			insert(arr[i]);
    		}
    	// FILL IN
    }
    
    public int parent(int pos) {
    	return (pos-1)/2;
    } 
    
    public int leftchild(int pos) {
    	return 2*pos + 1;
    }

    public int rightchild(int pos) {
    	return 2*pos + 2;
    }   
    
    public boolean isLeaf(int pos)
    { return (pos >= size/2) && (pos < size); }

	/**create the array (heap) with the specified size and initialize all	of its elements to be zero.
	 * You need to be careful about whether	any	other field	needs to be	initialized	at this	stage
	 * (hint: think about what the value of size should be).	
	 */

    public MaxHeap(int maxsize)
    {
    		heap = new int[maxsize];
    		for(int i = 0; i < heap.length; i++) {
    			heap[i] = 0;
    		}    		
    		size = 0;   	// FILL IN
    }   
    
    
    /**	remove the maximum value stored in the max heap (only the maximum and nothing else!)
     * 	start by	swapping	 the	 root with the last leaf, sift the new root down	to restore heap	property
     * @return -1 if empty, returns value that was removed
     */
    public int removemax() {
    		if(size == 0) {
    			return -1;
    		}
    		
    		int pos;
    		int max = heap[0];
    		
    		swap(size-1,0);//switch last with first
    		heap[size-1] = 0;//remove max
    		pos = 0;
    		size--;
    		
    		while(pos < size && (heap[pos] < heap[rightchild(pos)] || heap[pos] < heap[leftchild(pos)])) {
    			if(heap[pos] < heap[leftchild(pos)]) {
    				swap(leftchild(pos),pos);
    				pos = leftchild(pos);
    			} else {
    				swap(rightchild(pos),pos);
    				pos = rightchild(pos);
    			}
    		}
       		
    	// FILL IN - DONOT RETURN -1 UNLESS NECESSARY   		
    		return max;
    }
    
    /**function	inserts the given value (element) into the heap and positions	 it	into the correct location
     * The element is supposed to be	added to the latest position	available in	 the	 heap array	
     * make proper changes to the heap structure	to maintain	the	max	heap	 property
     * DO NOT forget	to update the size value
     * encouraged to	write helper	 functions to make your code	more	 comprehensible (not required)
     */
    public void insert(int element)
    {
    		int pos = size;
    		heap[pos] = element;
    		
    		while(heap[pos]>heap[parent(pos)]) {
    			swap(pos, parent(pos));
    			pos = parent(pos);
    		}
    		
    		size++;
    	// FILL IN
    } 

    // This function implements the heap sort
    /**swap values till the array has proper	heap	 structure (i.e., it is a valid max heap)
     * start at the last	 parent	node and	 it	continually sifts down the smallest descendant at	 each level.
     * If a	child has a	larger value	 than the parent, the largest child	value is swapped	 with the parent
     * That	node	 is	then	 sifted	down	 again until	 no	sift	occurs,	or there	 are	 no	more	 children
     * Each parent node is sifted in	the	same	 manner	from	 the	 last until the	root, at	 which point the heap structure is valid
     * heapify routine uses siftDown to generate	a valid	max	heap	 where	the	largest	value will be in	 the	 root of	 the	 tree,
     * and every	 parent	will	 have a	value greater than or equal to its descendants
     * 
     */
    public void sort()
    {       
    		
    		if(size==0) {
    			return;
    		}
    		
    		heapify(size);
    		int sortAmount = size;
    		while(sortAmount!=0) {
    			swap(sortAmount, 0);//swap with max
    			sortAmount--;
    			heapify(sortAmount);//create valid heap without the element swapped		
    		}
    		
    	// FILL IN
    }  
    
    
	// DO NOT CHANGE THIS FUNCTION!
	public void printArray(){
		for (int i = 0; i < heap.length; i++)
			System.out.print(heap[i] + ", ");
		System.out.println();
	} 
	
	// DO NOT CHANGE THIS FUNCTION!
	// post: prints the tree contents, one per line, following an inorder traversal and using indentation to 
	//indicate node depth; prints right to left so that it looks correct when the output is rotated.
	public void printSideways(){
		printSideways(0, 0);
	}
	
	// DO NOT CHANGE THIS FUNCTION!
	// post: prints in reversed preorder the tree with given root, indenting each line to the given level
	private void printSideways(int root_indx, int level){
		if (root_indx < heap.length){
			printSideways(rightchild(root_indx), level+1);
			for (int i = 0; i < level; i++){
				System.out.print("       ");
			}
			System.out.println(heap[root_indx]);
			printSideways(leftchild(root_indx), level+1);
		}
	}
	
	
	// DO NOT CHANGE THIS FUNCTION!
	public boolean IsEqual(int[] arr){
		if (arr.length != heap.length)
			return false;

		if (arr.length == 0)
			return true;
		
		for (int i = 0; i < arr.length; i++)
			if (arr[i] != heap[i])
				return false;
		
		return true;
	}
	
	public void swap(int pos, int parent) {
		int value = heap[pos];
		heap[pos] = heap[parent];
		heap[parent] = value;
	}
	
	public void heapify(int index) {
		
		if(index == 0) {
			return;
		}
		
		int parent = parent(index);
		
		while(parent>0) {
			siftDown(parent, index);
			parent = parent(parent);
		}
		
		siftDown(0,index);
		
	}
	
	public void siftDown(int pos, int amount) {
		
		if(isLeaf(pos)|| pos>amount) {
			return;
		}
		
		
		if(rightchild(pos) <= amount && leftchild(pos)<=amount) {
			
			if(heap[pos] < heap[rightchild(pos)] && heap[pos] < heap[leftchild(pos)]) {
				int child = getLarger(leftchild(pos),rightchild(pos));
				swap(child, pos);
				siftDown(child, amount);
			}
			
			if(heap[pos] < heap[rightchild(pos)]){
				 swap(rightchild(pos), pos);
				 siftDown(rightchild(pos), amount);
			 }
			
			if(heap[pos] < heap[leftchild(pos)]) {
				swap(leftchild(pos), pos);
				siftDown(leftchild(pos), amount);
			}
				
		}
								
	}
	
	public int getLarger(int left, int right) {
		
		int largest = Math.max(heap[left], heap[right]);
		
		if(largest == heap[left]) {
			return left;
		} 
		
		return right;
	}
    
    public static void main(String[] arg)
    {
    	// ********************* TESTS FOR LAB ****************************//
    	
         MaxHeap maxHeap = new MaxHeap(15);
         
         int[] arr1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
         if (!maxHeap.IsEqual(arr1))
        	System.err.println("TEST FAILED: insert 0");
         
         maxHeap.insert(5);
         
         int[] arr2 = {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
         if (!maxHeap.IsEqual(arr2))
        	System.err.println("TEST FAILED: insert 1");
         
         maxHeap.insert(3);
         
         int[] arr3 = {5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
         if (!maxHeap.IsEqual(arr3))
        	System.err.println("TEST FAILED: insert 2");
         
         maxHeap.insert(17);
         
         int[] arr4 = {17, 3, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
         if (!maxHeap.IsEqual(arr4))
        	System.err.println("TEST FAILED: insert 3");
         
         maxHeap.insert(10);
         int[] arr5 = {17, 10, 5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
         if (!maxHeap.IsEqual(arr5))
        	System.err.println("TEST FAILED: insert 4");
         
         maxHeap.insert(84);
         int[] arr6 = {84, 17, 5, 3, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
         if (!maxHeap.IsEqual(arr6))
        	System.err.println("TEST FAILED: insert 5");
         
         maxHeap.insert(19);
         int[] arr7 = {84, 17, 19, 3, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0};
         if (!maxHeap.IsEqual(arr7))
        	System.err.println("TEST FAILED: insert 6");
         
         maxHeap.insert(6);
         int[] arr8 = {84, 17, 19, 3, 10, 5, 6, 0, 0, 0, 0, 0, 0, 0, 0};
         if (!maxHeap.IsEqual(arr8))
        	System.err.println("TEST FAILED: insert 7");
         
         maxHeap.insert(22);
         int[] arr9 = {84, 22, 19, 17, 10, 5, 6, 3, 0, 0, 0, 0, 0, 0, 0};
         if (!maxHeap.IsEqual(arr9))
        	System.err.println("TEST FAILED: insert 8");
         
         maxHeap.insert(9);
         int[] arr10 = {84, 22, 19, 17, 10, 5, 6, 3, 9, 0, 0, 0, 0, 0, 0};
         if (!maxHeap.IsEqual(arr10))
        	System.err.println("TEST FAILED: insert 9");
         
         MaxHeap maxHeap1 = new MaxHeap(15);
         
         maxHeap1.insert(97);
         maxHeap1.insert(93);
         maxHeap1.insert(87);
         maxHeap1.insert(90);
         maxHeap1.insert(89);
         maxHeap1.insert(83);
         
         int[] arr11 = {97, 93, 87, 90, 89, 83, 0, 0, 0, 0, 0, 0, 0, 0, 0};
         if (!maxHeap1.IsEqual(arr11))
        	System.err.println("TEST FAILED: insert 10");
         
         // remove tests
         int maxVal1 = maxHeap1.removemax();
         
         int[] arr12 = {93, 90, 87, 83, 89, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
         if (maxVal1 != 97 || !maxHeap1.IsEqual(arr12))
        	System.err.println("TEST FAILED: remove 1");
         
         int maxVal2 = maxHeap1.removemax();
         
         int[] arr13 = {90, 89, 87, 83, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
         if (maxVal2 != 93 || !maxHeap1.IsEqual(arr13))
        	System.err.println("TEST FAILED: remove 2");
         
         MaxHeap maxHeap2 = new MaxHeap(15);
         if (maxHeap2.removemax() != -1)
        	System.err.println("TEST FAILED: remove 0"); 
         System.out.println("Testing Done (lab) !!!");
        
        
     // ********************* TESTS FOR ASSIGNMENT ****************************//
        
         int[] arr14 = {};
         MaxHeap maxHeap3 = new MaxHeap(arr14);
         
         if (!maxHeap3.IsEqual(arr14))
        	System.err.println("TEST FAILED: constructor 0");
         
         int[] arr15 = {3};
         MaxHeap maxHeap4 = new MaxHeap(arr15);
         if (!maxHeap4.IsEqual(arr15))
        	System.err.println("TEST FAILED: constructor 1");
         
         int[] arr16 = {22, 3};
         int[] arr16_2 = {3, 22};
         MaxHeap maxHeap5 = new MaxHeap(arr16_2);
         if (!maxHeap5.IsEqual(arr16))
        	System.err.println("TEST FAILED: constructor 2");
         
         int[] arr17 = {84, 22, 19, 17, 10, 5, 6, 3, 9};
         int[] arr17_2 = {5, 3, 17, 10, 84, 19, 6, 22, 9};
         MaxHeap maxHeap6 = new MaxHeap(arr17_2);
         
         if (!maxHeap6.IsEqual(arr17))
        	System.err.println("TEST FAILED: constructor 3");
         
         MaxHeap maxHeap7 = new MaxHeap(0);
         maxHeap7.heap = arr15;
         maxHeap7.size = 0;
         maxHeap7.sort();
         if (!maxHeap7.IsEqual(arr15))
        	System.err.println("TEST FAILED: sort 1");
         
         MaxHeap maxHeap8 = new MaxHeap(2);
         maxHeap8.heap = arr16;
         maxHeap8.size = arr16.length-1;
         maxHeap8.sort();
         if (!maxHeap8.IsEqual(arr16_2))
        	System.err.println("TEST FAILED: sort 2");
         
         MaxHeap maxHeap9 = new MaxHeap(9);
         maxHeap9.heap = arr17_2;
         maxHeap9.size = arr17_2.length-1;
         maxHeap9.sort();
         int[] arr18 = {3, 5, 6, 9, 10, 17, 19, 22, 84};
         if (!maxHeap9.IsEqual(arr18))
        	System.err.println("TEST FAILED: sort 3");
        
        System.out.println("Testing Done (assignment) !!!");
        
                
    }
}
