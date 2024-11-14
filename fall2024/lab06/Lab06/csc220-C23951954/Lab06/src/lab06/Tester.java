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
		// Build some SortedString objects
		SortedString word1 = new SortedString("abc");
		SortedString word2 = new SortedString("dfg");
		SortedString word3 = new SortedString("");
		SortedString word4 = new SortedString("ABC");
		SortedString [] list = new SortedString[] {word1,word2,word3,word4};
		
		AnagramUtil check = new AnagramUtil();
		System.out.println(word1.compareTo(word2));//-3
		System.out.println(check.areAnagrams(word1, word2));//f
		System.out.println(check.areAnagrams(word1, word3));//f
		System.out.println(check.areAnagrams(word1, word4));//t

		 
		 
		InsertionSort <Integer> inserter = new InsertionSort <Integer>();
		Integer [] array1 = new Integer[0];
		Integer [] array2 = new Integer[] {1,2,3,4};
		Integer [] array3 = new Integer[] {1,2,4,3,5,4,6,7,1,0};
		Integer [] array4 = new Integer[] {};
		Integer [] array5 = new Integer[] {1};	
		Integer [] SortedArray = inserter.sort(array3);
		for(int i=0; i < SortedArray.length; i++) {
			System.out.println(SortedArray[i]);
		}
		
		//System.out.println(inserter.sort(array2));
		//System.out.println(inserter.sort(array3));
		//System.out.println(inserter.sort(array4));
		//System.out.println(inserter.sort(array5));

		
		
		String[] s3 = AnagramUtil.getLargestAnagramGroup("sample_word_list.txt");
		System.out.println(s3);
		for(int i=0; i < s3.length; i++) {
			System.out.println(s3[i]);
		}
		
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
		

		
		
		// File Test
		
		// Test getLargestAnagramGroup
		String[] anagrams = AnagramUtil.getLargestAnagramGroup("sample_word_list.txt");
		
		String[] expected = {"carets", "Caters", "caster", "crates", "Reacts", "recast", "traces"};
		
		if (anagrams.length != expected.length) {
			System.err.println("Error getting largest anagram group from text file");
		} else {
			for (int i = 0; i < expected.length; i++) {
				if (!anagrams[i].equals(expected[i])) {
					System.err.println("Error getting largest anagram group from text file");
				}
			}
		}
		
		// Test empty file
		String[] fileTest1 = AnagramUtil.getLargestAnagramGroup("blank.txt");
		if (fileTest1.length != 0) {
			System.out.println("failed blank");
		}
		
		// Test file with one word
		String[] fileTest2 = AnagramUtil.getLargestAnagramGroup("one_word.txt");
		if (fileTest2.length != 0) {
			System.out.println("failed one-word file");
		}
		
		// Test file with no anagrams
		String[] fileTest3 = AnagramUtil.getLargestAnagramGroup("No_ana.txt");
		if (fileTest3.length != 0) {
			System.out.println("failed No_ana");
		}
		
		
		
		// Test Merge Sort
		MergeSort<Double> merge = new MergeSort<Double>();
		
		
		
		String[] strings2 = {"house", "mouse", "cat", "dog", "act", "rat", "tar", "god", "soume"};
		SortedString[] arr10 = SortedString.toSortedString(strings2);
		
		
		
		
		
		for (int i = 1; i < arr10.length; i++) {
			if (arr10[i].compareTo(arr10[i-1]) < 0) {
				System.out.println("sorted string error");
			}
		}
		
		// Test Runtime Prediction
		Double[] arr11 = {10.4, 2.5, 6.6, 4.7, 7.3, 29.3, 200.5, 300.6};
		
		merge.fit(arr11);
		
		System.out.println("Merge Sort Timing");
		System.out.println("Time for 1000: " + merge.predict(1000) + " microseconds\nTime for 10000: " + merge.predict(10000) + " microseconds\nTime for 100K: " + merge.predict(100000) + " microseconds\nTime for 1M: " + merge.predict(1000000) + " microseconds\n");

		System.out.println("All Done !");
		
		
	}

}

