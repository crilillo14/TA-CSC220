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

	// head node over here
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
    	// just gets length
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
    	// adds to the end of linkedlist
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
    	// loops until it finds the index at next
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
    	// adds at index
        if (index == 0) {
            front = new ListNode(value, front);
        } else if (index == size()) {
            add(value);
        } else if (index > size()) {
            return;
        } else {
        	// i wanna insert node69 at index 1
        	// node1 ---> node2 ---> node3
        	// node1 ---> node69 ---> node2 ---> node3

        	// current is node1
        	// current's next is node2

            ListNode current = nodeAt(index - 1);
            current.next = new ListNode(value, current.next);
        }
    }






    // *************************************************
    // LAB STARTS HERE!!!

    /**
     * Finds the index of the last occurrence of the given value in the list.
     *
     * @param value the integer value to search for
     * @return the index of the last occurrence of the value, or -1 if the value is not found
     */
    public int lastIndexOf(int value) {
        // TODO: Lab part 2.1

    	// just in case linkedlist has nothing
    	if (size() == 0) {
    		return -1;
    	}

    	int foundIndex = -1;
    	int index = 0;

    	ListNode current = front;

    	// going through entire linkedlist
    	while (current != null) {
    		if (current.data == value) {
    			foundIndex = index;
    		}

    		current = current.next;
    		index += 1;
    	}

    	return foundIndex;

    }

    /**
     * Removes all occurrences of the given value from the list.
     *
     * @param value the integer value to remove from the list
     */
    public void removeAll(int value) {
        // TODO: Lab part 2.2

    	ListNode current = front;
    	ListNode prev = front;

    	while (current != null) {

    		// if you find that value, set the previous node to node's next
    		// lemme depict it rq

    		// value = 420

    		// 1 ---> 2 ---> 420 ---> 3 ---> 420 ---> 4 ---> 5
    		//                ^
    		// 420's prev is 2
    		// now we set 2's next to 420's next
    		// but now 420 which is our current is gone

    		// 1 ---> 2 ---> 3 ---> 420 ---> 4 ---> 5
    		//               ^
    		// so our new current is basically prev's next

    		// 420 ---> 1 ---> 2 ---> 3
    		//  ^
    		// check if both current's and prev's are the same
    		// if they are, set the front equal to the next one
    		// 1 ---> 2 ---> 3

    		if (current.data == value && prev.data != value) {
    			prev.next = current.next;
    			current = prev.next;
    		}
    		else if (current.data == value && prev.data == value) {
    			front = current.next;
    			current = current.next;
    		}
    		else {
				prev = current;
				current = current.next;
    		}
    	}

    }








    // ****************************************************
    // ASSIGNMENT STUFF

    /**
     * Doubles the size of the list by replacing each integer in the list with two of that integer.
     * If the list is empty, this method does nothing.
     */
    public void stutter() {
        // TODO: Assignment part 2.1

    	// [1, 8, 19, 4, 17]
    	// stutter ->
    	// [1, 1, 8, 8, 19, 19, 4, 4, 17, 17]

    	if (size() == 0) {
    		return;
    	}

    	ListNode current = front;

    	int index = 0;

    	// dude just think about it
    	// its so trivial
    	while (current != null) {
    		add(index + 1, current.data);
    		current = current.next.next;
    		index += 2;
    	}

    }

    /**
     * Rearranges the list by moving all values at odd-numbered positions to the end of the list.
     * The original order of the elements is otherwise preserved.
     *
     * <p>This method must not create any new nodes or use any auxiliary data structures.
     * The links of the list must be rearranged to achieve the desired result.</p>
     */
    public void shift() {
        // TODO: Assignment part 2.2

    	// [10, 31, 42, 23, 44, 75, 86]
    	// shift ->
    	// [10, 42, 44, 86, 31, 23, 75]

    	// 10 ---> 31 ---> 42 ---> 23 ---> 44 ---> 75 ---> 86
    	// 0       1       2       3       4       5       6
    	// 10 ---> 31 ---> 42 ---> 23 ---> 44 ---> 75 ---> 86
    	//         1               3               5
    	// 10 ---> 42 ---> 23 ---> 44 ---> 75 ---> 86 ---> 31
    	// move first odd to the end

    	if (size() == 0) {
    		return;
    	}

    	ListNode current = front;
    	ListNode prev = front;

    	int index = 0;
    	while (index < size()) {

    		// if index is odd
    		if (index % 2 == 1) {
    			prev.next = current.next;
    			add(current.data); // this method adds a number to new node at the end
    		}

    		prev = current;
    		current = current.next;
    		index += 1;

    	}

    }


}
