package lab06;

import java.util.Arrays;

public class Tester {
	
	

	    public static void main(String[] args) {
	        MergeSort<Integer> set = new MergeSort<Integer>();
	        Integer[] ob1 = {1, 2, 3, 4, 3};
	        set.sort(ob1);
	        int ob1leng = ob1.length;
	        set.fit(ob1);
	        System.out.println(set.predict(ob1leng));
	       
	        InsertionSort<Integer> Iset = new InsertionSort<Integer>();
	        Integer[] Iobj = {2, 5, 3};
	        Iset.sort(Iobj);
	        Iset.fit(Iobj);
	        System.out.println(Iset.predict(Iobj.length));

	        String[] s3 = AnagramUtil.getLargestAnagramGroup("sample_word_list.txt");
	        System.out.println(Arrays.toString(s3));
	        
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
	}



