package lab08;

public class LinkedIntList {

    private ListNode front; // Reference to the first node in the list

    // Constructor to create an empty list
    public LinkedIntList() {
        front = null;
    }

    // Constructor to create a list from an array of values
    public LinkedIntList(int[] arr) {
        if (arr.length > 0) {
            front = new ListNode(arr[0]);
            ListNode current = front;
            for (int i = 1; i < arr.length; i++) {
                current.next = new ListNode(arr[i]);
                current = current.next;
            }
        }
    }

    // Method to find the last index of a given value
    public int lastIndexOf(int value) {
        int index = 0;
        int lastIndex = -1;
        ListNode current = front;

        while (current != null) {
            if (current.data == value) {
                lastIndex = index;
            }
            current = current.next;
            index++;
        }
        return lastIndex;
    }

    // Method to remove all occurrences of a given value
    public void removeAll(int value) {
        // Remove from head if it matches
        while (front != null && front.data == value) {
            front = front.next;
        }

        ListNode current = front;

        // Traverse the list and update links to skip nodes with 'value'
        while (current != null && current.next != null) {
            if (current.next.data == value) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
    }

    // Method to double the list by repeating each element
    public void stutter() {
        ListNode current = front;
        while (current != null) {
            ListNode duplicate = new ListNode(current.data, current.next);
            current.next = duplicate;
            current = duplicate.next;
        }
    }

    // Method to shift odd-indexed elements to the end of the list
    public void shift() {
        if (front == null || front.next == null) return;

        ListNode even = front;
        ListNode odd = front.next;
        ListNode oddStart = odd;

        // Separate the even and odd nodes
        while (even.next != null && odd.next != null) {
            even.next = odd.next;
            even = even.next;
            if (even.next != null) {
                odd.next = even.next;
                odd = odd.next;
            }
        }
        even.next = oddStart; // Attach the odd list to the end of the even list
        odd.next = null; // End the list at the last odd element
    }

    // Method to convert list to a string format for easy comparison
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        ListNode current = front;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}

