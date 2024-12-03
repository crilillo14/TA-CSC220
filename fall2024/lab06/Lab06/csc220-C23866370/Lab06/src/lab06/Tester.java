package lab06;

import java.util.Arrays;

public class Tester {
    public static void main(String[] args) {
        // Test for MERGESORT
        System.out.println("Testing MergeSort");
        MergeSort<Integer> mergeSort = new MergeSort<>();

        // Case 1: Empty array
        Integer[] emptyArray = {};
        System.out.println("Empty array: " + Arrays.toString(mergeSort.sort(emptyArray)));

        // Case 2: One element
        Integer[] oneElement = {42};
        System.out.println("One element: " + Arrays.toString(mergeSort.sort(oneElement)));

        // Case 3: Two elements
        Integer[] twoElements = {7, 3};
        System.out.println("Two elements: " + Arrays.toString(mergeSort.sort(twoElements)));

        // Case 4: Already sorted array
        Integer[] sortedArray = {1, 2, 3, 4, 5};
        System.out.println("Sorted array: " + Arrays.toString(mergeSort.sort(sortedArray)));

        // Case 5: Reverse sorted array
        Integer[] reverseSortedArray = {9, 8, 7, 6, 5};
        System.out.println("Reverse sorted array: " + Arrays.toString(mergeSort.sort(reverseSortedArray)));

        // Case 6: Random array
        Integer[] randomArray = {4, 1, 7, 3, 2};
        System.out.println("Random array: " + Arrays.toString(mergeSort.sort(randomArray)));

        // Test for ANAGRAMUTIL
        System.out.println("Testing AnagramUtil");

        // Case 1: An array with anagrams
        SortedString[] anagramArray = SortedString.toSortedString(new String[]{"listen", "silent", "enlist", "apple"});
        String[] largestGroup1 = AnagramUtil.getLargestAnagramGroup(anagramArray);
        System.out.println("Anagrams in array (listen, silent, enlist, apple): " + Arrays.toString(largestGroup1));

        // Case 2: No anagrams
        SortedString[] noAnagramArray = SortedString.toSortedString(new String[]{"cat", "dog", "bird"});
        String[] largestGroup2 = AnagramUtil.getLargestAnagramGroup(noAnagramArray);
        System.out.println("No anagrams (cat, dog, bird): " + Arrays.toString(largestGroup2));

        // Case 3: Mixed case anagrams
        SortedString[] mixedCaseAnagrams = SortedString.toSortedString(new String[]{"Listen", "Silent", "Enlist"});
        String[] largestGroup3 = AnagramUtil.getLargestAnagramGroup(mixedCaseAnagrams);
        System.out.println("Mixed case anagrams (Listen, Silent, Enlist): " + Arrays.toString(largestGroup3));

        // Case 4: Empty array
        SortedString[] emptyAnagramArray = {};
        String[] largestGroup4 = AnagramUtil.getLargestAnagramGroup(emptyAnagramArray);
        System.out.println("Empty array: " + Arrays.toString(largestGroup4));

        // Case 5: One word
        SortedString[] oneWordArray = SortedString.toSortedString(new String[]{"word"});
        String[] largestGroup5 = AnagramUtil.getLargestAnagramGroup(oneWordArray);
        System.out.println("One word (word): " + Arrays.toString(largestGroup5));

        // Test for RUNTIME PREDICTION
        System.out.println("Testing Runtime Prediction");

        // Fit with a small array of 10 elements
        Integer[] smallArray = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        mergeSort.fit(smallArray);  // Fit with small array

        // Predict runtime for 100,000 elements
        System.out.println("Predicted time for array of size 100,000: " + mergeSort.predict(100000) + " microseconds");

        // Predict runtime for 1,000,000 elements
        System.out.println("Predicted time for array of size 1,000,000: " + mergeSort.predict(1000000) + " microseconds");
    }
}
