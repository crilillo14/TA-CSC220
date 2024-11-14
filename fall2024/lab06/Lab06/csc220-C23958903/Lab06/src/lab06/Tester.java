package lab06;

import java.util.Arrays;

public class Tester {

    public static void main(String[] args) {
        try {
            testSortedString();
            testAnagramUtil();
            testInsertionSort();
            testMergeSort();
            testAnagramUtilLargestGroup();
            System.out.println("All tests passed.");
        } catch (AssertionError e) {
            System.out.println("Test failed: " + e.getMessage());
        }
    }

    // 1.1 and 1.2: Testing SortedString Constructor and compareTo
    public static void testSortedString() {
        SortedString s1 = new SortedString("alert");
        SortedString s2 = new SortedString("later");
        SortedString s3 = new SortedString("hello");
        SortedString s4 = new SortedString("world");

        // Test compareTo (should return 0 for anagrams)
        assert s1.compareTo(s2) == 0 : "SortedString.compareTo failed: alert and later should be anagrams.";
        assert s3.compareTo(s4) != 0 : "SortedString.compareTo failed: hello and world should not be anagrams.";
    }

    // 2: Testing AnagramUtil areAnagrams method
    public static void testAnagramUtil() {
        SortedString s1 = new SortedString("alert");
        SortedString s2 = new SortedString("later");
        SortedString s3 = new SortedString("hello");
        SortedString s4 = new SortedString("world");

        // Test areAnagrams
        assert AnagramUtil.areAnagrams(s1, s2) : "AnagramUtil.areAnagrams failed: alert and later should be anagrams.";
        assert !AnagramUtil.areAnagrams(s3, s4) : "AnagramUtil.areAnagrams failed: hello and world should not be anagrams.";
    }

    // 3, 4.1, 4.2, 4.3: Testing InsertionSort sort(), O(), fit(), and predict()
    public static void testInsertionSort() {
        InsertionSort<SortedString> insertionSort = new InsertionSort<>();
        String[] words = {"alert", "later", "world", "hello"};
        SortedString[] sortedStrings = SortedString.toSortedString(words);

        // Test sorting
        SortedString[] sortedResult = insertionSort.sort(sortedStrings);
        assert sortedResult != null : "InsertionSort.sort failed: returned null.";
        assert sortedResult[0].getUnsorted().equals("alert") : "InsertionSort.sort failed: wrong sorting order.";
    }

    // 2, 3.1, 3.2, 3.3: Testing MergeSort sort(), O(), fit(), and predict()
    public static void testMergeSort() {
        MergeSort<SortedString> mergeSort = new MergeSort<>();
        String[] words = {"alert", "later", "world", "hello"};
        SortedString[] sortedStrings = SortedString.toSortedString(words);

        // Test sorting
        SortedString[] sortedResult = mergeSort.sort(sortedStrings);
        assert sortedResult != null : "MergeSort.sort failed: returned null.";
        assert sortedResult[0].getUnsorted().equals("alert") : "MergeSort.sort failed: wrong sorting order.";
    }

    // Testing AnagramUtil getLargestAnagramGroup with the sample word list
    public static void testAnagramUtilLargestGroup() {
        String[] largestGroup = AnagramUtil.getLargestAnagramGroup("src/lab06/sample_word_list.txt");
        assert largestGroup.length > 0 : "AnagramUtil.getLargestAnagramGroup failed: No anagrams found.";
    }
}
