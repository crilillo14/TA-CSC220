package lab06;

import java.util.Arrays;

public class Tester {

    public static void main(String[] args) {
        // 1.1 Test SortedString Constructor
        System.out.println("1.1 Testing SortedString Constructor");
        SortedString s1 = new SortedString("alert");
        SortedString s2 = new SortedString("later");
        SortedString s3 = new SortedString("random");
        System.out.println("Original: " + s1.getUnsorted() + ", Sorted: " + s1.getSorted());
        System.out.println("Original: " + s2.getUnsorted() + ", Sorted: " + s2.getSorted());
        System.out.println("Original: " + s3.getUnsorted() + ", Sorted: " + s3.getSorted());

        // 1.2 Test SortedString compareTo
        System.out.println("\n1.2 Testing SortedString compareTo");
        System.out.println(s1.compareTo(s2)); // Should be 0 since they are anagrams
        System.out.println(s1.compareTo(s3)); // Should be non-zero since they are not anagrams

        // 2 Test AnagramUtil areAnagrams
        System.out.println("\n2 Testing AnagramUtil areAnagrams");
        System.out.println(AnagramUtil.areAnagrams(s1, s2)); // Should return true
        System.out.println(AnagramUtil.areAnagrams(s1, s3)); // Should return false

        // 3 Test InsertionSort sort
        System.out.println("\n3 Testing InsertionSort sort");
        InsertionSort<SortedString> insertionSort = new InsertionSort<>();
        SortedString[] stringArray = SortedString.toSortedString(new String[]{"cat", "dog", "act", "god", "tac"});
        System.out.println("Original: " + Arrays.toString(stringArray));
        SortedString[] sortedArray = insertionSort.sort(stringArray);
        System.out.println("Sorted: " + Arrays.toString(sortedArray));

        // 4.1, 4.2, 4.3 Test InsertionSort O(), fit(), and predict()
        System.out.println("\n4.1, 4.2, 4.3 Testing InsertionSort O(), fit(), and predict()");
        insertionSort.fit(stringArray);
        System.out.println("Predicted time for sorting 100 elements: " + insertionSort.predict(100) + " microseconds");
        System.out.println("Predicted time for sorting 1000 elements: " + insertionSort.predict(1000) + " microseconds");

        // Assignment Part 1 Test AnagramUtil getLargestAnagramGroup
        System.out.println("\nAssignment Part 1 Testing AnagramUtil getLargestAnagramGroup");
        String[] largestGroup = AnagramUtil.getLargestAnagramGroup(stringArray);
        System.out.println("Largest anagram group: " + Arrays.toString(largestGroup));

        // Assignment Part 2 Test MergeSort sort
        System.out.println("\nAssignment Part 2 Testing MergeSort sort");
        MergeSort<SortedString> mergeSort = new MergeSort<>();
        System.out.println("Original: " + Arrays.toString(stringArray));
        SortedString[] mergeSortedArray = mergeSort.sort(stringArray);
        System.out.println("Sorted using MergeSort: " + Arrays.toString(mergeSortedArray));

        // Assignment Part 3.1, 3.2, 3.3 Test MergeSort O(), fit(), and predict()
        System.out.println("\nAssignment Part 3.1, 3.2, 3.3 Testing MergeSort O(), fit(), and predict()");
        mergeSort.fit(stringArray);
        System.out.println("Predicted time for sorting 100 elements using MergeSort: " + mergeSort.predict(100) + " microseconds");
        System.out.println("Predicted time for sorting 1000 elements using MergeSort: " + mergeSort.predict(1000) + " microseconds");
    }
}
