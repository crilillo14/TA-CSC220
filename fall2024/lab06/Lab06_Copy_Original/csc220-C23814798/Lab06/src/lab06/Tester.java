package lab06;

public class Tester {

    //You will need to write your own tests

    /* 
    * As a reminder these are the methods we did in lab:
    * 1.1) SortedString Constuctor
    * 1.2) SortedString compareTo(SortedString other)
    * 2) AnagramUtil areAnagrams(SortedString str1, SortedString str2)
    * 3) InsetionSort sort(E[] array) (HINT: use the toSortedString() convenience function from SortedString)
    * 4.1, 4.2, 4.3) InsertionSort  O(int n), fit(E[] array), and predict(int n)
    */

    /* 
    * As a reminder these are the methods we did in assignment:
    * 1) AnagramUtil getLargestAnagramGroup(SortedString[] stringList) (Hint: You can use getLargestAnagramGroup(String filename))
    * 2) MergeSort sort(E[] array1, E[] array2, int first, int last) (Hint: You can use sort(E[] array))
    * 3.1, 3.2, 3.3) MergeSort  O(int n), fit(E[] array), and predict(int n)
    */
    public static void main(String[] args) {
        testSortedString();

        testAnagrams();

        testInsertionSort();

        testPredictInsertionSort();

        testLargestAnagramGroup();

        testMergeSort();
    }


    public static void testSortedString() {
        SortedString str1 = new SortedString("carets");
        SortedString str2 = new SortedString("Caters");

        assert str1.compareTo(str2) == 0 : "Test failed: 'carets' and 'Caters' should be equal when sorted.";
        System.out.println("SortedString tests passed!");
    }


    public static void testAnagrams() {
        SortedString str1 = new SortedString("listen");
        SortedString str2 = new SortedString("silent");

        assert AnagramUtil.areAnagrams(str1, str2) : "Test failed: 'listen' and 'silent' should be anagrams.";
        System.out.println("AnagramUtil.areAnagrams tests passed!");
    }

 
    public static void testInsertionSort() {
        Integer[] array = {5, 3, 8, 1, 2};
        Integer[] sortedArray = new InsertionSort<Integer>().sort(array);

        Integer[] expected = {1, 2, 3, 5, 8};
        assert java.util.Arrays.equals(sortedArray, expected) : "Test failed: InsertionSort failed to sort correctly.";
        System.out.println("InsertionSort tests passed!");
    }

 
    public static void testPredictInsertionSort() {
        Integer[] array = {5, 3, 8, 1, 2};
        InsertionSort<Integer> sorter = new InsertionSort<>();
        sorter.fit(array);  // Fit the constant for runtime prediction

        double predictedTime = sorter.predict(100000);  // Predict the time for sorting 100K elements
        System.out.println("Predicted time for 100K elements: " + predictedTime + " microseconds");
    }

 
    public static void testLargestAnagramGroup() {
        String[] words = {"carets", "Caters", "caster", "crates", "Reacts", "recast", "traces"};
        SortedString[] sortedWords = SortedString.toSortedString(words);

        String[] largestGroup = AnagramUtil.getLargestAnagramGroup(sortedWords);
        System.out.println("Largest anagram group: " + java.util.Arrays.toString(largestGroup));
    }


    public static void testMergeSort() {
        Integer[] array = {5, 3, 8, 1, 2};
        MergeSort<Integer> sorter = new MergeSort<>();
        Integer[] sortedArray = sorter.sort(array);

        Integer[] expected = {1, 2, 3, 5, 8};
        assert java.util.Arrays.equals(sortedArray, expected) : "Test failed: MergeSort failed to sort correctly.";
        System.out.println("MergeSort tests passed!");
    }
}

