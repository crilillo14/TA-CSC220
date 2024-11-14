package lab06;

import java.util.Arrays;

public class Tester {

    public static void main(String[] args) {
        // Test 1.1: SortedString Constructor and toString()
        testSortedStringConstructor();

        // Test 1.2: SortedString compareTo()
        testSortedStringCompareTo();

        // Test 2: AnagramUtil areAnagrams()
        testAnagramUtilAreAnagrams();

        // Test 3: InsertionSort sorting
        testInsertionSort();

        // Test 4: InsertionSort performance methods (O, fit, predict)
        testInsertionSortPerformance();

        // Test Assignment 1: AnagramUtil getLargestAnagramGroup()
        testGetLargestAnagramGroup();

        // Test Assignment 2: MergeSort sorting
        testMergeSort();

        // Test Assignment 3: MergeSort performance methods (O, fit, predict)
        testMergeSortPerformance();
    }

    // Test SortedString constructor and toString() method
    public static void testSortedStringConstructor() {
        SortedString s = new SortedString("alert");
        System.out.println("Test SortedString constructor and toString(): " + s);
    }

    // Test SortedString compareTo() method
    public static void testSortedStringCompareTo() {
        SortedString s1 = new SortedString("alert");
        SortedString s2 = new SortedString("later");
        System.out.println("Test SortedString compareTo: " + s1.compareTo(s2));
    }

    // Test AnagramUtil areAnagrams() method
    public static void testAnagramUtilAreAnagrams() {
        SortedString s1 = new SortedString("alert");
        SortedString s2 = new SortedString("later");
        SortedString s3 = new SortedString("banana");
        System.out.println("Test AnagramUtil areAnagrams (alert, later): " + AnagramUtil.areAnagrams(s1, s2));
        System.out.println("Test AnagramUtil areAnagrams (alert, banana): " + AnagramUtil.areAnagrams(s1, s3));
    }

    // Test InsertionSort sort() method
    public static void testInsertionSort() {
        InsertionSort<SortedString> sorter = new InsertionSort<>();
        String[] words = {"alert", "later", "apple", "banana"};
        SortedString[] sortedStrings = SortedString.toSortedString(words);
        SortedString[] sortedResult = sorter.sort(sortedStrings);
        System.out.println("Test InsertionSort:");
        for (SortedString s : sortedResult) {
            System.out.println(s);
        }
    }

    // Test InsertionSort performance methods
    public static void testInsertionSortPerformance() {
        InsertionSort<SortedString> sorter = new InsertionSort<>();
        String[] words = {"alert", "later", "apple", "banana"};
        SortedString[] sortedStrings = SortedString.toSortedString(words);

        // Test O(n) function
        System.out.println("InsertionSort O(4): " + sorter.O(sortedStrings.length));

        // Test fit() and predict()
        sorter.fit(sortedStrings);
        System.out.println("InsertionSort predict for n = 1000: " + sorter.predict(1000));
    }

    // Test AnagramUtil getLargestAnagramGroup()
    public static void testGetLargestAnagramGroup() {
        String[] words = {"carets", "caters", "caster", "crates", "reacts", "recast", "traces", "apple"};
        SortedString[] sortedStrings = SortedString.toSortedString(words);
        String[] result = AnagramUtil.getLargestAnagramGroup(sortedStrings);
        System.out.println("Test getLargestAnagramGroup:");
        for (String s : result) {
            System.out.println(s);
        }
    }

    // Test MergeSort sort() method
    public static void testMergeSort() {
        MergeSort<SortedString> sorter = new MergeSort<>();
        String[] words = {"alert", "later", "apple", "banana"};
        SortedString[] sortedStrings = SortedString.toSortedString(words);
        SortedString[] sortedResult = sorter.sort(sortedStrings);
        System.out.println("Test MergeSort:");
        for (SortedString s : sortedResult) {
            System.out.println(s);
        }
    }

    // Test MergeSort performance methods
    public static void testMergeSortPerformance() {
        MergeSort<SortedString> sorter = new MergeSort<>();
        String[] words = {"alert", "later", "apple", "banana"};
        SortedString[] sortedStrings = SortedString.toSortedString(words);

        // Test O(n) function
        System.out.println("MergeSort O(4): " + sorter.O(sortedStrings.length));

        // Test fit() and predict()
        sorter.fit(sortedStrings);
        System.out.println("MergeSort predict for n = 1000: " + sorter.predict(1000));
    }
}
