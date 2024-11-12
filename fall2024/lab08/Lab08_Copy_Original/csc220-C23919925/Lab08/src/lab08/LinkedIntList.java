package lab08;

/**
 * The {@code LinkedIntList} class represents a singly linked list of integers.
 * It supports basic operations like adding elements, finding the size of the
 * list, and printing the list in a comma-separated, bracketed format. This
 * modified version of the linked list class also includes placeholders for
 * additional functionality such as shifting and stuttering elements in the
 * list, and removing specific elements.
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
     * Constructs an empty linked list. The {@code front} reference is set to
     * {@code null}.
     */
    public LinkedIntList() {
        front = null;
    }

    /**
     * Constructs a linked list from an array of integers. The elements of the
     * array are added to the list in the order they appear.
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
     * Returns a string representation of the list in a comma-separated,
     * bracketed format. For example, a list containing the elements [1, 2, 3]
     * will be returned as "[1, 2, 3]".
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
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 or index
     * >= size())
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
     * @return the index of the last occurrence of the value, or -1 if the value
     * is not found
     */
    public int lastIndexOf(int value) {
        // DONE: Lab part 2.1
        // Initialize the index to return and current index  
        int idx = -1;
        int currIdx = 0;

        // Initialize the node that we use to traverse the list
        ListNode curr = front;
        if (curr == null) {
            return idx;
        }
        // Traverse the entire list
        while (curr != null) {
            // Update the last occurrence index if data == value
            if (curr.data == value) {
                idx = currIdx;
            }
            // Move to the next node
            curr = curr.next;
            currIdx += 1;
        }
        return idx;
    }

    /**
     * Removes all occurrences of the given value from the list.
     *
     * @param value the integer value to remove from the list
     */
    public void removeAll(int value) {
        // DONE: Lab part 2.2
        // Initialize the previous and current nodes
        ListNode prev = null;
        ListNode curr = front;
        boolean first = true;

        // Traverse the list
        while (curr != null) {
            // If the value is found, remove it
            if (curr.data == value) {
                // If the first node is the value, update the front
                if (first) {
                    front = curr.next;
                    curr = front;
                } else {
                    prev.next = curr.next;
                    curr = curr.next;
                }
            } else {
                // Move to the next node
                prev = curr;
                curr = curr.next;
                first = false;
            }
        }
    }

    /**
     * Doubles the size of the list by replacing each integer in the list with
     * two of that integer. If the list is empty, this method does nothing.
     */
    public void stutter() {
        // DONE: Assignment part 2.1
        // Initialize the current node
        ListNode curr = front;
        // If the list is empty, do nothing
        if (curr == null) {
            return;
        }
        // Traverse the list
        while (curr != null) {
            // Create a new node with the same data as the current node
            ListNode newNode = new ListNode(curr.data);
            // Insert the new node after the current node
            newNode.next = curr.next;
            curr.next = newNode;
            // Move to the next node
            curr = newNode.next;
        }
    }

    /**
     * Rearranges the list by moving all values at odd-numbered positions to the
     * end of the list. The original order of the elements is otherwise
     * preserved.
     *
     * <p>
     * This method must not create any new nodes or use any auxiliary data
     * structures. The links of the list must be rearranged to achieve the
     * desired result.</p>
     */
    public void shift() {
        // DONE: Assignment part 2.2

        // Error checking
        if (this.front == null || this.front.next == null) {
            return;
        }

        // Initialize pointers
        ListNode evenPtr = front;
        ListNode oddPtr = front.next;
        ListNode oddHead = oddPtr;

        // Traverse the list and the links
        while (evenPtr != null && evenPtr.next != null && oddPtr != null && oddPtr.next != null) {
            // Always skip odd nodes so we can add them at the end
            evenPtr.next = oddPtr.next;
            evenPtr = evenPtr.next;
            oddPtr.next = evenPtr.next;
            oddPtr = oddPtr.next;
        }

        evenPtr.next = oddHead; // Link the end of the even list to the head of the odd list
    }
}
