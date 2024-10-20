package lab05;


public class BinarySearchSet {
	private int capacity;
	private int numItems;
	public double[] storage;
	
	public BinarySearchSet(){
		capacity = 6;
		storage = new double[6];
		numItems = 0;
	}
	
	public BinarySearchSet(double[] input){
		this();
		
		for (int i = 0; i < input.length; i++)
			binary_add(input[i]);
	}	
	
	public boolean MoveAndInsert(double newVal, int index){
		if (storage[index] == newVal)
			return false;
		
		for (int i = numItems-1; i >= index; i--)
			storage[i+1] = storage[i];
		
		storage[index] = newVal;
		numItems++;
		return true;
	}
	
	public boolean BinaryInsertIndex(double target, int start, int end){		
		if (start == end)
			return (target > storage[start]) ? MoveAndInsert(target, start+1) : MoveAndInsert(target, start);
		
		if (start > end)
			return (target > storage[start]) ? MoveAndInsert(target, end) : MoveAndInsert(target, start);
		
		if (target > storage[end])
			return MoveAndInsert(target, end+1);
		
		if (target <= storage[start])
			return MoveAndInsert(target, start);
		
		int mid = (start + end)/2;
		
		if (target == storage[mid])
			return MoveAndInsert(target, mid);
		else if (target < storage[mid])
			return BinaryInsertIndex(target, start, mid-1);
		else // target > storage[mid
			return BinaryInsertIndex(target, mid+1, end);
	}
	
	public boolean binary_add(double newVal){
		if ((capacity - numItems) < 1)
			grow();
		
		if (numItems == 0){
			storage[0] = newVal;
			numItems++;
			return true;
		}		
		
		return BinaryInsertIndex(newVal, 0, numItems-1);
	}
	
	public String toString(){
		String toReturn = ("capacity: " + capacity + " numItems: " + numItems + "\n");
		for (int i = 0; i < numItems; i++){
			toReturn += (Double.toString(storage[i]) + ", ");
		}
		return toReturn;
	}	
	
	public boolean remove(double element){
		int index = FindIndex(element);
		if (index == -1)
			return false;
		
		for (int i = index; i < numItems-1; i++)
			storage[i] = storage[i+1];
		
		storage[(numItems--)-1] = 0.0;
		return true;
	}	
	
	public boolean containsAll(double[] elements){
		if (elements.length == 0) return false;
		
		if (numItems == 0 || numItems < elements.length) return false;
		
		for (int i = 0; i < elements.length; i++){
			if (FindIndex(elements[i]) < 0)
				return false;
		}
		return true;
	}	
	
	public boolean contains(double element){
		if (numItems == 0){
			return false;
		}else{
			return FindIndex(element) >= 0;
		}
	}	
	
	public int FindIndex(double target){
		// simple binary search!
		if (numItems == 0) {
			return -1;
		}
		
		int min = 0;
		int max = numItems-1;
		while (min <= max){
			int mid = (max + min)/2;
			if (storage[mid] == target){
				return mid;  
			}else if (storage[mid] < target){
				min = mid + 1; 
			}else{ 
				max = mid - 1; 
			}
		}
		return -1;
	}	
	
	public void grow(){
		double[] temp = new double[2*capacity];
		
		for (int i = 0; i < capacity; i++)
			temp[i] = storage[i];
		
		storage = temp;
		
		capacity *= 2;
	}	
		
	public boolean isEmpty(){
		if (numItems == 0)
			return true;
		else
			return false;
	}
		
	public int size(){
		return numItems;
	}
	
	public void clear(){
		if (numItems == 0)
			return;
		
		for (int i = 0; i < numItems; i++)
			storage[i] = 0.0;
		
		numItems = 0;
	}		
}