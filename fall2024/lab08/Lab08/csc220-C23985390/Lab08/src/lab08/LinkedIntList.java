package lab08;


public class LinkedIntList {
    public ListNode front; 

    public LinkedIntList() {
        front = null;
    }

    public LinkedIntList(int[] arr) {
        this();
        for (int i = 0; i < arr.length; i++) {
            add(arr[i]);
        }
    }

    public int size() {
        int count = 0;
        ListNode current = front;
        while (current != null) {
            current = current.next;
            count++;
        }
        return count;
    }


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

    private ListNode nodeAt(int index) {
        ListNode current = front;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }


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

    public int lastIndexOf(int value) {
    	int index = -1; 
        int currentIndex = 0; 
        ListNode current = front; 

        while (current != null) {
            if (current.data == value) {
                index = currentIndex; 
            }
            current = current.next; 
            currentIndex++; 
        }

        return index; 
    }


    public void removeAll(int value) {

    	if (front == null) {
            return;
        }
    	while (front != null && front.data == value) {
            front = front.next;
    	}
    	ListNode current = front;
    	while (current != null && current.next != null) {
            if (current.next.data == value) {
                current.next = current.next.next; 
            } else {
                current = current.next; 
            }
        }
    }

    public void stutter() {

        ListNode current = front;

        while (current != null) {
           
            ListNode duplicate = new ListNode(current.data);

            duplicate.next = current.next;
            current.next = duplicate;

            current = duplicate.next;
        }
    }


    public void shift() {

        if (front == null || front.next == null) {
            return;
        }

        ListNode even = front;      
        ListNode odd = front.next;  
        ListNode oddHead = odd;     

        
        while (even != null && odd != null && odd.next != null) {
           
            even.next = odd.next; 
            even = even.next; 

            
            odd.next = even.next; 
            odd = odd.next; 
        }

       
        even.next = oddHead;
    }

    
}