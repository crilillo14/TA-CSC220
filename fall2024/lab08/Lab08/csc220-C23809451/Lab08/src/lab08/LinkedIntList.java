package lab08;

/**
 * The {@code LinkedIntList} class represents a singly linked list of integers.
 * It provides methods to manipulate the list, including adding, removing, and searching for elements.
 *
 * The linked list is constructed using instances of the {@code ListNode} class.
 *
 * @see ListNode
 * @see LinkedIntListTester
 */
public class LinkedIntList {
    public ListNode front; // Reference to the first node in the list

    /**
     * Constructs an empty linked list.
     */
    public LinkedIntList() {
        front = null;
    }

    /**
     * Constructs a linked list from an array of integers.
     *
     * @param arr the array of integers to initialize the list
     */
    public LinkedIntList(int[] arr) {
        if (arr.length == 0) {
            front = null;
        } else {
            front = new ListNode(arr[0]);
            ListNode current = front;
            for (int i = 1; i < arr.length; i++) {
                current.next = new ListNode(arr[i]);
                current = current.next;
            }
        }
    }

    /**
     * Duplicates every integer in the list by replacing each with two of that integer.
     * For example, if the list is [1, 2, 3], it becomes [1, 1, 2, 2, 3, 3].
     */
    public void stutter() {
        // If the list is empty, return immediately
        if (front == null) {
            return;
        }

        ListNode current = front;

        // Traverse the list and duplicate each node
        while (current != null) {
            ListNode duplicate = new ListNode(current.data); // Create a duplicate node
            duplicate.next = current.next; // Link duplicate to the next node
            current.next = duplicate; // Link current node to the duplicate
            current = duplicate.next; // Move to the next original node
        }
    }

    /**
     * Rearranges the elements of the list by moving all values that are in odd-numbered positions
     * to the end of the list while preserving the order of all elements.
     */
    public void shift() {
        if (front == null || front.next == null) {
            return; // If the list is empty or has one node, no shifting is needed
        }

        ListNode oddHead = front.next; // Head of the odd-indexed nodes
        ListNode evenTail = front; // Tail of the even-indexed nodes
        ListNode oddCurrent = oddHead; // Current node in the odd indexed nodes

        // Traverse through the list, separating odd and even indexed nodes
        while (oddCurrent != null && oddCurrent.next != null) {
            evenTail.next = oddCurrent.next; // Link the even node to the next even node
            evenTail = evenTail.next; // Move to the new tail
            oddCurrent.next = oddCurrent.next.next; // Skip the next odd node
            oddCurrent = oddCurrent.next; // Move to the next odd node
        }

      /*  evenTail.next = null; // Terminate the list at the end of even nodes
        oddCurrent = oddHead; // Reset to the head of the odd nodes
        front = oddHead; // The new front of the list is now the head of the odd nodes

        // Link the last even node to the start of the odd nodes
        while (evenTail.next != null) {
            evenTail = evenTail.next; // Move to the end of the even nodes
        } */
        evenTail.next = oddHead; // Connect the last even node to the odd nodes
    }

    /**
     * Returns a string representation of the linked list.
     *
     * @return a string representing the contents of the list
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        ListNode current = front;

        while (current != null) {
            result.append(current.data);
            if (current.next != null) {
                result.append(", ");
            }
            current = current.next;
        }

        result.append("]");
        return result.toString();
    }
    public void removeAll(int value){
		// @TA: There are many ways to do this. A cleaner way to do this is to write a helper function to find index first and then delete
		//* FILL IN
		if (front == null){
			return;
		}else{
			while (front.data == value)
				front = front.next;
			ListNode current = front;
			while (current.next != null){
				while (current.next.data == value){
					if (current.next.next == null){ 
						// this case only happens if we are removing from the end of the list
						current.next = null;
						return;
					}else{
						current.next = current.next.next;
					}
				}
				current = current.next;
			}
		}
	}
    
    public int lastIndexOf(int value){
		//* FILL IN - first thing to implement
		int index = 0, index_toReturn = -1;
		if (front == null){
			return index_toReturn;
		}else{
			ListNode current = front;
			while (current != null){
				if (current.data == value)
					index_toReturn = index;
				current = current.next;
				index++;
			}
		}
		return index_toReturn;
	}	
}
