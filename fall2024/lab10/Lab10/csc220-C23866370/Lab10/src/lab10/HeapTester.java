package lab10;

public class HeapTester {

    public static void main(String[] arg) {
        // ********************* TESTS FOR LAB ****************************//
        MaxHeap maxHeap = new MaxHeap(15);

        int[] arr1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        if (!maxHeap.IsEqual(arr1))
            System.err.println("TEST FAILED: offer 0");

        maxHeap.offer(5);
        int[] arr2 = {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        if (!maxHeap.IsEqual(arr2))
            System.err.println("TEST FAILED: offer 1");

        maxHeap.offer(3);
        int[] arr3 = {5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        if (!maxHeap.IsEqual(arr3))
            System.err.println("TEST FAILED: offer 2");

        maxHeap.offer(17);
        int[] arr4 = {17, 3, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        if (!maxHeap.IsEqual(arr4))
            System.err.println("TEST FAILED: offer 3");

        maxHeap.offer(10);
        int[] arr5 = {17, 10, 5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        if (!maxHeap.IsEqual(arr5))
            System.err.println("TEST FAILED: offer 4");

        maxHeap.offer(84);
        int[] arr6 = {84, 17, 5, 3, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        if (!maxHeap.IsEqual(arr6))
            System.err.println("TEST FAILED: offer 5");

        maxHeap.offer(19);
        int[] arr7 = {84, 17, 19, 3, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        if (!maxHeap.IsEqual(arr7))
            System.err.println("TEST FAILED: offer 6");

        maxHeap.offer(6);
        int[] arr8 = {84, 17, 19, 3, 10, 5, 6, 0, 0, 0, 0, 0, 0, 0, 0};
        if (!maxHeap.IsEqual(arr8))
            System.err.println("TEST FAILED: offer 7");

        maxHeap.offer(22);
        int[] arr9 = {84, 22, 19, 17, 10, 5, 6, 3, 0, 0, 0, 0, 0, 0, 0};
        if (!maxHeap.IsEqual(arr9))
            System.err.println("TEST FAILED: offer 8");

        maxHeap.offer(9);
        int[] arr10 = {84, 22, 19, 17, 10, 5, 6, 3, 9, 0, 0, 0, 0, 0, 0};
        if (!maxHeap.IsEqual(arr10))
            System.err.println("TEST FAILED: offer 9");

        // Testing the sort function
        int[] unsortedArr = {3, 5, 1, 10, 2};
        maxHeap.sort(unsortedArr);
        int[] sortedArr = {1, 2, 3, 5, 10};
        if (!maxHeap.IsEqual(sortedArr)) {
            System.err.println("TEST FAILED: sort");
        } else {
            System.out.println("Sort passed!");
        }

        System.out.println("Testing Done (lab) !!!");
    }
}


