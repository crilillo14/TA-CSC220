package lab06;
import java.util.Arrays;
public class Tester {
	 /* 
	    * As a reminder these are the methods we did in lab:
	    * 1.1) SortedString Constructor
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
	
    //You will need to write your own tests
	public static void main(String[] args) {
		
		//Strings for testing
		SortedString string1 = new SortedString("Aidan");
		SortedString string2 = new SortedString("Levine");
		SortedString string3 = new SortedString("ZYXWabcd");
		SortedString strAnagram1 = new SortedString("heart");
		SortedString strAnagram2 = new SortedString("earth");
		
		System.out.println("SortedString tests:");
		System.out.println(string1);
		System.out.println(string2);
		System.out.println(string3);
		
		System.out.println("\nCompareTo tests:");
		System.out.println(string1.compareTo(string2));
		System.out.println(string2.compareTo(string3));
		System.out.println(string3.compareTo(string1));
		
		System.out.println("\nAnagramUtil tests:");
		System.out.println(AnagramUtil.areAnagrams(strAnagram1,strAnagram2)); //true
		System.out.println(AnagramUtil.areAnagrams(string1,string2)); //false

		System.out.println("\nInsertionSort Test:");
        InsertionSort<Integer> sorter = new InsertionSort<>();
		SortedString[] stringArray = SortedString.toSortedString(new String[]{"Math", "Science", "Video", "English", "Business"});
		SortedString[] sortedStrings = new InsertionSort<SortedString>().sort(stringArray);
		System.out.println("Sorted Strings by Insertionsort: "+ Arrays.toString(sortedStrings));
		
		System.out.println("\nInsertionSort Run Time Prediction Test:");
		Integer[] smallArray = {5, 3, 8, 1, 2, 9, 4, 6, 7};
		sorter.fit(smallArray);
		int largeSize1 = 100000;
        int largeSize2 = 1000000;
        double predictedTimeForLarge1 = sorter.predict(largeSize1);
        double predictedTimeForLarge2 = sorter.predict(largeSize2);

        System.out.println("Predicted time for sorting array of size " + largeSize1 + ": " + predictedTimeForLarge1 + " microseconds");
        System.out.println("Predicted time for sorting array of size " + largeSize2 + ": " + predictedTimeForLarge2 + " microseconds");
        
        System.out.println("\nLargest Anagram Group Test:");
        // Testing the largest group of anagrams using the sample word list
        String[] largestGroup = AnagramUtil.getLargestAnagramGroup("sample_word_list.txt");
        System.out.println("Largest anagram group: " + Arrays.toString(largestGroup));

        // Edge case 1: An empty file 
        System.out.println("\nTesting with an empty file:");
        String[] emptyGroup = AnagramUtil.getLargestAnagramGroup("empty_file.txt");
        System.out.println("Largest anagram group from empty file: " + Arrays.toString(emptyGroup));

        // Edge case 2: A file with one word 
        System.out.println("\nTesting with a file containing one word:");
        String[] oneWordGroup = AnagramUtil.getLargestAnagramGroup("one_word_file.txt");
        System.out.println("Largest anagram group from one word: " + Arrays.toString(oneWordGroup));

        // Edge case 3: A file with no anagrams 
        System.out.println("\nTesting with a file with no anagrams:");
        String[] noAnagramGroup = AnagramUtil.getLargestAnagramGroup("no_anagrams_file.txt");
        System.out.println("Largest anagram group from no anagrams: " + Arrays.toString(noAnagramGroup));

        // Edge case 4: A file where the largest group of anagrams is in the middle
        System.out.println("\nTesting with a file where the largest anagram group is in the middle:");
        String[] middleAnagramGroup = AnagramUtil.getLargestAnagramGroup("middle_anagrams_file.txt");
        System.out.println("Largest anagram group from the middle of the file: " + Arrays.toString(middleAnagramGroup));
        System.out.println("\nMergeSort Run Time Prediction Test:");
        MergeSort<Integer> mergeSorter = new MergeSort<>();
        
        Integer[] smallMergeArray = {5, 3, 8, 1, 2, 9, 4, 6, 7};
        mergeSorter.fit(smallMergeArray);
        
        int MlargeSize1 = 100000;
        int MlargeSize2 = 1000000;
        double mergePredictedTimeForLarge1 = mergeSorter.predict(MlargeSize1);
        double mergePredictedTimeForLarge2 = mergeSorter.predict(MlargeSize2);
        System.out.printf("\nPredicted time for MergeSort sorting array of size 100000: " + mergePredictedTimeForLarge1 + "microseconds");
        System.out.printf("\nPredicted time for MergeSort sorting array of size 1000000: " + mergePredictedTimeForLarge2 + "microseconds");

        // ========================


	}


}

