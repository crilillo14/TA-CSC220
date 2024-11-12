package lab08;

/**
 * The {@code LinkedIntList} class represents a singly linked list of integers.
 * It supports basic operations like adding elements, finding the size of the list,
 * and printing the list in a comma-separated, bracketed format. 
 * This modified version of the linked list class also includes placeholders for additional
 * functionality such as shifting and stuttering elements in the list, and removing specific elements.
 * 
 * <h3>Usage Example:</h3>
 * <pre>
 * LinkedIntList list = new LinkedIntList();
 * list.add(5);        // Adds 5 to the list
 * list.add(2, 10);    // Adds 10 at index 2
 * System.out.println(list.size());  // Prints the size of the list
 * System.out.println(list);         // Prints the list
 * </pre>
 * 
 * @see ListNode
 * @author Your Name
 */
public class LinkedIntList {
    /**
     * A reference to the first node in the list.
     */
    public ListNode front; 

    /**
     * Constructs an empty linked list.
     * The {@code front} reference is set to {@code null}.
     */
    public LinkedIntList() {
        front = null;
    }

    /**
     * Constructs a linked list from an array of integers.
     * The elements of the array are added to the list in the order they appear.
     * 
     * @param arr the array of integers to be added to the list
     */
    public LinkedIntList(int[] arr) {
        this();
        for (int i = 0; i < arr.length; i++) {
            add(arr[i]);
        }
    }

    /**
     * Returns the number of elements in the list.
     * 
     * @return the number of elements in the list
     */
    public int size() {
        int count = 0;
        ListNode current = front;
        while (current != null) {
            current = current.next;
            count++;
        }
        return count;
    }

    /**
     * Returns a string representation of the list in a comma-separated, bracketed format.
     * For example, a list containing the elements [1, 2, 3] will be returned as "[1, 2, 3]".
     * 
     * @return the string representation of the list
     */
    public String toString() {
        if (front == null) {
            return "[]";
        } else {
            String result = "[" + front.data;
            ListNode current = front.next;
            while (current != null) {
                result += ", " + current.data;
                current = current.next;
            }
            result += "]";
            return result;
        }
    }

    /**
     * Adds a new value to the end of the list.
     * 
     * @param value the integer value to be added to the list
     */
    public void add(int value) {
        if (front == null) {
            front = new ListNode(value);
        } else {
            ListNode current = front;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new ListNode(value);
        }
    }

    /**
     * Returns the node at the specified index.
     * 
     * @param index the index of the node to return
     * @return the {@code ListNode} at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 or index >= size())
     */
    private ListNode nodeAt(int index) {
        ListNode current = front;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    /**
     * Inserts a new value at the specified index in the list.
     * 
     * @param index the position at which to insert the value
     * @param value the integer value to insert
     */
    public void add(int index, int value) {
        if (index == 0) {
            front = new ListNode(value, front);
        } else if (index == size()) {
            add(value);
        } else if (index > size()) {
            return;
        } else {
            ListNode current = nodeAt(index - 1);
            current.next = new ListNode(value, current.next);
        }
    }

    // *************************************************

    /**
     * Finds the index of the last occurrence of the given value in the list.
     * 
     * @param value the integer value to search for
     * @return the index of the last occurrence of the value, or -1 if the value is not found
     */
    public int lastIndexOf(int value) {
        // TODO: Lab part 2.1
    	ListNode current = front;
    	int index = 0;
    	int last_index = index;
    //	System.out.println("** log 10 **  initial last_index = " + last_index);
    	
    	if (current == null || current.next == null) {//empty link list
    	//	System.out.println("** log 11 ** empty link list return last_index = " + "-1");
    		return -1;
    	}
    	while (current.next != null) {
    		if (value == current.data) {    			
    			last_index = index;
    			//display the message 
			//	System.out.println("** log 12 ** changed last_index = " + last_index +
			//						" current.data = " + current.data);
    		}
    	
    		index = index + 1; 	
    		current= current.next;
    //		System.out.println("** log 13 ** index = " + index + 
    //				                    " current.data = " + current.data);
    	} //end while current.next != null
    	
    //	System.out.println("** log 14 ** last_index = " + last_index +
    //			" value = " + value + 
    //			" index = " + index + 
	//			" current.data = " + current.data);
    	
    	if (value == current.data) {// here end of the Linklist where value = current.data at the last position
    	  	last_index = index;
    	//	System.out.println("** log 15 **  changed last_index = " + last_index +
		//			" current.data = " + current.data);
    	}
    	
    	if (last_index > 0) {
    	//	System.out.println("** log 16 **  return last_index = " + last_index);
    		return last_index;
    	}
    	else {// here last_index = 0, hence could not find the value therefore return -1
    	//	System.out.println("** log 17 ** the value is not found return last_index = " + "-1");
    		return -1;
    	}
    }
    
    
    /**
     * Removes all occurrences of the given value from the list.
     * 
     * @param value the integer value to remove from the list
     */
    public void removeAll(int value) {
        // TODO: Lab part 2.2
    	ListNode current = front;  // Start from the head node
        ListNode previous = null;  // Previous node starts as null

        // Case 1: Remove occurrences of the value at the head (front) of the list
        while (current != null && current.data == value) {
            front = current.next;  // Move head to the next node
            current = front;       // Update current to the new head
        //  System.out.println("**log** " + value + " found and removed at the head");
        }

        // Case 2: Traverse the rest of the list to remove occurrences of the value
        while (current != null) {
            if (current.data == value) {
                // Remove the current node by linking the previous node to the next node
                previous.next = current.next;
          //    System.out.println("**log** " + value + " found and removed in the list");
            } else {
                // Only update the previous if no deletion occurs
                previous = current;
            }
            // Move current to the next node
            current = current.next;
        }

        // Case 3: If value was not present, this will naturally result
        //         in no output after traversal.

    } //end removeAll method
       

    /**
     * Doubles the size of the list by replacing each integer in the list with two of that integer.
     * If the list is empty, this method does nothing.
     */
    public void stutter() {
        // TODO: Assignment part 2.1 public void stutter() {
    	 if( front != null) {             
             ListNode current=front; //starting from first node
             ListNode prevNode=null; // this is the node which we will create and add in the list by copying the values of nodes
             while(current.next !=null){
                     prevNode=new ListNode(current.data); // creating the new prev node with current node data
                     prevNode.next=current.next; //linking prev node with the next node of original node
                     current.next=prevNode; // linking original node with prev node
                     current=prevNode.next; // making current node as next of prev node
             }
             current.next=new ListNode(current.data);   //creating and linking last node of the list
    	 }
    }

    /**
     * Rearranges the list by moving all values at odd-numbered positions to the end of the list.
     * The original order of the elements is otherwise preserved.
     * 
     * <p>This method must not create any new nodes or use any auxiliary data structures.
     * The links of the list must be rearranged to achieve the desired result.</p>
     */
    public void shift() { // Shift method to move odd-index nods to the end
        // TODO: Assignment part 2.2
    	   if (front == null || front.next == null) {
               return; 
           }

           ListNode evenHead = front;          
           ListNode oddHead = front.next;     
           ListNode evenTail = evenHead;    
           ListNode oddTail = oddHead;        

           ListNode current = oddHead.next;    
           int index = 2;                     

           while (current != null) {
                 if (index % 2 == 0) {
                      evenTail.next = current;  
                      evenTail = evenTail.next;
                 } else {
                     oddTail.next = current;    
                     oddTail = oddTail.next;
                 }
                 current = current.next;
                 index++;
           }

           // Terminate both lists proprly
           evenTail.next = oddHead;
           oddTail.next = null;    

           // Update  front to point to the new head (evenHead)
          front = evenHead;
    } // end shift method  
} // end LinkedIntList
