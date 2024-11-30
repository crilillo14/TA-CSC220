package lab06;

public class Tester {
	public static void main(String[] args) {
		
		 String[] largestAnagramGroup = AnagramUtil.getLargestAnagramGroup("sample_word_list.txt");

	        if (largestAnagramGroup.length == 0) {
	            System.out.println("No anagram groups found.");
	        } 
	        else {
	            
	        	System.out.println("Largest Anagram Group:");
	            
	        	
	        	for (String word : largestAnagramGroup) {
	                
	        		System.out.print(word + " ");
	            }
	            
	        	System.out.println();
	        }
	
	        
	        
	        
	            
	        	
	        	System.out.println("Test 1: AnagramUtil getLargestAnagramGroup(SortedString[] stringList)");

	            String[] words = {"carets", "Caters", "caster", "crates", "Reacts", "recast", "traces", "apple", "banana", "cherry"};
	            
	            SortedString[] sortedStrings = SortedString.toSortedString(words);

	            String[] largestAnagramGroup1 = AnagramUtil.getLargestAnagramGroup(sortedStrings);

	            if (largestAnagramGroup1.length == 0) {
	            	
	                System.out.println("No anagram groups found.");
	            } 
	            else {
	                System.out.println("Largest Anagram Group:");
	                
	                for (String word : largestAnagramGroup1) {
	                    System.out.print(word + " ");
	                }
	                
	                System.out.println();
	            
	            System.out.println();
	        
	}
	        
	        
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

}
}

