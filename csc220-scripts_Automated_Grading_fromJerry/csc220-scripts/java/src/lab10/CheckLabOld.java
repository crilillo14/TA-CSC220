package lab12;

public class CheckLabOld {
	public static void main(String[] args){
		
		double []gradePoint = {0,0,0,0,0};
	    double []givenPoint = {9,14,14,14,14};
	    String output = "" ;
		try{
			
			double const2=4, insert=11, remove=3, sort=3;
			MaxHeap maxHeap = new MaxHeap(15);
			Solution sol = new Solution(15);
			
			try{
				if(maxHeap.heap.length == 15){
					gradePoint[0] = 100;
				}else{
					output+="TEST FAILED: constructor(int) failed to create heap array\n";
				}
			}catch(Exception ex){
				output+="TEST FAILED: constructor(int) throws " + ex + "\n";
			}
			
			maxHeap.heap = sol.heap.clone();
			maxHeap.size = sol.size;
	        
			try {
		        int[] arr1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		        if (!maxHeap.IsEqual(arr1))
		        	output+="TEST FAILED: insert 0\n";
		        else
		        	gradePoint[1] += 100/insert;
			} catch (Exception e) {
				output+="TEST FAILED: insert 0 throws " + e + "\n";
			}
	        
			sol.insert(5);
	        try { 
	        	maxHeap.insert(5); 
		        int[] arr2 = {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		        if (!maxHeap.IsEqual(arr2))
		        	output+="TEST FAILED: insert 1\n";
		        else
		        	gradePoint[1] += 100/insert;
	        } catch (Exception e) {
	        	output+="TEST FAILED: insert 1 throws " + e + "\n";
	        }
	        
	        maxHeap.heap = sol.heap.clone();
	        maxHeap.size = sol.size;
	            
	        sol.insert(3);
	        try { 
	        	 maxHeap.insert(3); 
		        int[] arr3 = {5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		        if (!maxHeap.IsEqual(arr3))
		        	output+="TEST FAILED: insert 2\n";
		        else
		        	gradePoint[1] += 100/insert;
	        } catch (Exception e) {
	        	output+="TEST FAILED: insert 2 throws " + e + "\n";
	        }
	                
	        maxHeap.heap = sol.heap.clone();
	        maxHeap.size = sol.size;
	        
	        sol.insert(17);
	        try { 
	        	 maxHeap.insert(17); 
		        int[] arr4 = {17, 3, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		        if (!maxHeap.IsEqual(arr4))
		        	output+="TEST FAILED: insert 3\n";
		        else
		        	gradePoint[1] += 100/insert;
	        } catch (Exception e) {
	        	output+="TEST FAILED: insert 3 throws " + e + "\n";
	        }
	                
	        maxHeap.heap = sol.heap.clone();
	        maxHeap.size = sol.size;
	        
	        sol.insert(10);
	        try { 
	        	maxHeap.insert(10); 
		        int[] arr5 = {17, 10, 5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		        if (!maxHeap.IsEqual(arr5))
		        	output+="TEST FAILED: insert 4\n";
		        else
		        	gradePoint[1] += 100/insert;
	        } catch (Exception e) {
	        	output+="TEST FAILED: insert 4 throws " + e + "\n";
	        }
	        
	        maxHeap.heap = sol.heap.clone();
	        maxHeap.size = sol.size;
	        
	        sol.insert(84);
	        try { 
	        	maxHeap.insert(84); 
		        int[] arr6 = {84, 17, 5, 3, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		        if (!maxHeap.IsEqual(arr6))
		        	output+="TEST FAILED: insert 5\n";
		        else
		        	gradePoint[1] += 100/insert;
	        } catch (Exception e) {
	        	output+="TEST FAILED: insert 5 throws " + e + "\n";
	        }
	      
	        maxHeap.heap = sol.heap.clone();
	        maxHeap.size = sol.size;
	        
	        sol.insert(19);
	        try { 
	        	maxHeap.insert(19); 
		        int[] arr7 = {84, 17, 19, 3, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		        if (!maxHeap.IsEqual(arr7))
		        	output+="TEST FAILED: insert 6\n";
		        else
		        	gradePoint[1] += 100/insert;
	        } catch (Exception e) {
	        	output+="TEST FAILED: insert 6 throws " + e + "\n";
	        }
	                
	        maxHeap.heap = sol.heap.clone();
	        maxHeap.size = sol.size;
	        
	        sol.insert(6);
	        try { 
	        	maxHeap.insert(6); 
		        int[] arr8 = {84, 17, 19, 3, 10, 5, 6, 0, 0, 0, 0, 0, 0, 0, 0};
		        if (!maxHeap.IsEqual(arr8))
		        	output+="TEST FAILED: insert 7\n";
		        else
		        	gradePoint[1] += 100/insert;
	        } catch (Exception e) {
	        	output+="TEST FAILED: insert 7 throws " + e + "\n";
	        }
	                
	        maxHeap.heap = sol.heap.clone();
	        maxHeap.size = sol.size;
	        
	        sol.insert(22);
	        try { 
	        	maxHeap.insert(22); 
		        int[] arr9 = {84, 22, 19, 17, 10, 5, 6, 3, 0, 0, 0, 0, 0, 0, 0};
		        if (!maxHeap.IsEqual(arr9))
		        	output+="TEST FAILED: insert 8\n";
		        else
		        	gradePoint[1] += 100/insert;
	        } catch (Exception e) {
	        	output+="TEST FAILED: insert 8 throws " + e + "\n";
	        }
	                
	        maxHeap.heap = sol.heap.clone();
	        maxHeap.size = sol.size;
	        
	        sol.insert(9);
	        try { 
	        	maxHeap.insert(9); 
		        int[] arr10 = {84, 22, 19, 17, 10, 5, 6, 3, 9, 0, 0, 0, 0, 0, 0};
		        if (!maxHeap.IsEqual(arr10))
		        	output+="TEST FAILED: insert 9\n";
		        else
		        	gradePoint[1] += 100/insert;
	        } catch (Exception e) {
	        	output+="TEST FAILED: insert 9 throws " + e + "\n";
	        }
	        
	        MaxHeap maxHeap1 = new MaxHeap(15); // assumes correct implementation
	        
	        sol.insert(97);
	        sol.insert(93);
	        sol.insert(87);
	        sol.insert(90);
	        sol.insert(89);
	        sol.insert(83); 
	        try { 
		        maxHeap1.insert(97);
		        maxHeap1.insert(93);
		        maxHeap1.insert(87);
		        maxHeap1.insert(90);
		        maxHeap1.insert(89);
		        maxHeap1.insert(83);    
		        
		        int[] arr11 = {97, 93, 87, 90, 89, 83, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		        if (!maxHeap1.IsEqual(arr11))
		        	output+="TEST FAILED: insert 10\n";
		        else
		        	gradePoint[1] += 100/insert;
	        } catch (Exception e) {
	        	output+="TEST FAILED: insert 10 throws " + e + "\n";
	        }
	        
	        maxHeap.heap = sol.heap.clone();
	        maxHeap.size = sol.size;
	        
	        // remove tests  
	        sol.removemax();
	        try { 
		        int maxVal1 = maxHeap1.removemax();
		        
		        int[] arr12 = {93, 90, 87, 83, 89, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		        if (maxVal1 != 97 || !maxHeap1.IsEqual(arr12))
		        	output+="TEST FAILED: remove 1\n";
		        else
		        	gradePoint[2] += 100/remove;
	        } catch (Exception e) {
	        	output+="TEST FAILED: remove 1 throws " + e + "\n";
	        }
	        
	        maxHeap.heap = sol.heap.clone();
	        maxHeap.size = sol.size;
	                
	        sol.removemax();
	        try {
		        int maxVal2 = maxHeap1.removemax();
		        
		        int[] arr13 = {90, 89, 87, 83, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		        if (maxVal2 != 93 || !maxHeap1.IsEqual(arr13))
		        	output+="TEST FAILED: remove 2\n";
		        else
		        	gradePoint[2] += 100/remove;
	        } catch (Exception e) {
	        	output+="TEST FAILED: remove 2 throws " + e + "\n";
	        }
	        
	        maxHeap.heap = sol.heap.clone();
	        maxHeap.size = sol.size;
	        
	        sol.removemax();
	        try { 
	        	MaxHeap maxHeap2 = new MaxHeap(15); 
		        if (maxHeap2.removemax() != -1)
		        	output+="TEST FAILED: remove 0\n"; 
		        else
		        	gradePoint[2] += 100/remove;
	        } catch (Exception e) {
	        	output+="TEST FAILED: remove 0 throws " + e + "\n";
	        }
	      
	        // write tests for sort
	        int[] arr15 = {3};
	        int[] arr14 = {};
	        int[] arr16 = {3, 22};//{22, 3};
	        int[] arr16_2 = {3, 22};
	        int[] arr17 = {3, 5, 6, 9, 10, 17, 19, 22, 84};//{84, 22, 19, 17, 10, 9, 6, 5, 3}; //
	        int[] arr17_2 = {5, 3, 17, 10, 84, 19, 6, 22, 9};
	        
	        try { 
		        MaxHeap maxHeap3 = new MaxHeap(arr14);
		        
		        if (!maxHeap3.IsEqual(arr14)){
		        	output+="TEST FAILED: constructor 0\n";
		        	MaxHeap maxHeap7 = new MaxHeap(0);
			        maxHeap7.heap = arr15;
			        maxHeap7.sort();
			        if (!maxHeap7.IsEqual(arr15))
			        	output+="TEST FAILED: sort 1\n";
			        else{
			        	gradePoint[4] += 100/sort;
			        }
		        }
		        else{
		        	gradePoint[3] += 100/const2;
		        	gradePoint[4] += 100/sort;
		        }
		        
	        } catch (Exception e) {
	        	output+="TEST FAILED: constructor or sort throws " + e + "\n";
	        }

	        try { 
		        MaxHeap maxHeap4 = new MaxHeap(arr15);
		        if (!maxHeap4.IsEqual(arr15)){
		        	output+="TEST FAILED: constructor 1\n";
		        }
		        else{
		        	gradePoint[3] += 100/const2;
		        }
	        } catch (Exception e) {
	        	output+="TEST FAILED: constructor throws " + e + "\n";
	        }
	        
	        
	        try { 
		        MaxHeap maxHeap5 = new MaxHeap(arr16_2);
		        if (!maxHeap5.IsEqual(arr16)){
		        	output+="TEST FAILED: constructor 2\n";
			        MaxHeap maxHeap8 = new MaxHeap(2);
			        maxHeap8.heap = arr16_2;
			        maxHeap8.size = arr16_2.length;
			        maxHeap8.sort();
			        if (!maxHeap8.IsEqual(arr16))
			        	output+="TEST FAILED: sort 2\n";
			        else{
			        	gradePoint[4] += 100/sort;
			        }
		        }
		        else{
		        	gradePoint[3] += 100/const2;
		        	gradePoint[4] += 100/sort;
		        }
		        
	        } catch (Exception e) {
	        	output+="TEST FAILED: constructor or sort throws " + e + "\n";
	        }
	                
	        try { 
		        MaxHeap maxHeap6 = new MaxHeap(arr17_2);
		        if (!maxHeap6.IsEqual(arr17)){
		        	output+="TEST FAILED: constructor 3\n";
		        	MaxHeap maxHeap9 = new MaxHeap(9);
			        maxHeap9.heap = arr17_2;
			        maxHeap9.size = arr17_2.length;
			        
			        maxHeap9.sort();
			        if (!maxHeap9.IsEqual(arr17))
			        	output+="TEST FAILED: sort 3\n";
			        else{
			        	gradePoint[4] += 100/sort;
			        }
		        }
		        else{
		        	gradePoint[3] += 100/const2;
		        	gradePoint[4] += 100/sort;
		        }
		        
	        } catch (Exception e) {
	        	output+="TEST FAILED: constructor or sort throws " + e + "\n";
	        }
	        
	        
		}catch(Exception ex){
			int endLen = ex.toString().length()<50?ex.toString().length():50;
			output+="## program threw " + ex.toString().substring(0, endLen) + "\n";
		}
		
		arrayTestPrint(gradePoint,givenPoint);
	    System.out.println(output);
	}
	
	public static void arrayTestPrint(double []arr,double []givenpoint){
		  System.out.print("$$" ) ;
		  int i=0;
		  for(double r: arr){
			  //System.out.println(givenpoint[i]);
			  System.out.print(Math.round((r*givenpoint[i])/100.0));
			  System.out.print("$$" ) ;
			  i++;
		  }
	  }
}

class Solution {
	public int[] heap;
    public int size;
    
    public Solution(int[] arr){
    	// FILL IN
    	//heap = arr;
    	//size = arr.length-1;
    	//sort();
    	this.size = 0;
    	heap = new int[arr.length];
    	for (int i = 0; i < arr.length; i++)
    		this.insert(arr[i]);
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

    public Solution(int maxsize)
    {
    	// FILL IN
        this.size = 0; 
        heap = new int[maxsize];
    }   
    
    public void swap(int i, int j)
    {
    	// helper function
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp; 
    }
    
    // put element in its correct place
    private void siftdown(int pos) {
      while (!isLeaf(pos)) {
        int j = leftchild(pos);
        if ((j<(size-1)) && heap[j] < heap[j+1])
          j++; // j is now index of child with greater value
        if (heap[pos] >= heap[j]) return;
        	swap(pos, j);
        pos = j;  // Move down
      }
    }
    
    
    public int removemax() {
    	if (size <= 0)
    		return -1;
    	// FILL IN
    	
    	swap(0, --size);
    	//printArray();
        if (size != 0)      // Not on last element
        	siftdown(0);   // Put new heap root value in correct place
        int toReturn = heap[size];
        heap[size] = 0;
        return toReturn;
    }
    
    
    public void insert(int element)
    {
    	// FILL IN
        heap[size++] = element;
        int current = size-1;
         
        while(heap[current] > heap[parent(current)])
        {
            swap(current,parent(current));
            current = parent(current);
        }	
    } 

    // This function implements the heap sort
    public void sort()
    {       
    	// FILL IN
        heapify();
        for (int i = size; i > 0; i--)
        {
            swap(0, i);
            size = size - 1;
            maxheap(0);
        }
    }  
    
    public void heapify()
    {
    	// function to build a heap - helper function
        for (int i = size/2; i >= 0; i--)
            maxheap(i);        
    }

    public void maxheap(int i)
    {
    	// helper function
    	// function to swap largest element in the heap
        int left = 2*i ;
        int right = 2*i + 1;
        int max = i;
        if (left <= size && heap[left] > heap[i])
            max = left;
        if (right <= size && heap[right] > heap[max])        
            max = right;
 
        if (max != i)
        {
            swap(i, max);
            maxheap(max);
        }
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
}

