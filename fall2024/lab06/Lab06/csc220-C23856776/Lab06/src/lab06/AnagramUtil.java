package lab06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Utility class for handling anagram-related operations such as determining
 * if two strings are anagrams and finding the largest group of anagrams from
 * a list.
 */
public class AnagramUtil {

    /**
     * Determines if two SortedString objects are anagrams of each other.
     *
     * @param str1 the first SortedString
     * @param str2 the second SortedString
     * @return true if the two strings are anagrams, false otherwise
     */
    public static boolean areAnagrams(SortedString str1, SortedString str2){
        
    	return str1.compareTo(str2) == 0;
    	
    }

    /**
     * Finds the largest group of anagrams in a file. The file should contain one
     * word per line.
     *
     * @param filename the name of the file containing the list of words
     * @return an array of strings containing the largest group of anagrams
     */
    public static String[] getLargestAnagramGroup(String filename){
    	
  
        SortedString[] words = readFile(filename);
       return getLargestAnagramGroup(words);
       
   
       
       
    }

    /**
     * Finds the largest group of anagrams in a list of SortedString objects.
     *
     * @param stringList an array of SortedString objects
     * @return an array of strings containing the largest group of anagrams
     */
    public static String[] getLargestAnagramGroup(SortedString[] stringList){
    	
    	if (stringList == null || stringList.length == 0) {
    		
    		return new String[0];
    		
    	}

    	
    	
    	Arrays.sort(stringList);
    	
    	
    	 int end = 0;
    	 int length = 1; 
    	 int maxLength = 0;
    	 
    	 for (int i = 1; i < stringList.length; i++) {
    		 
    		 if (areAnagrams(stringList[i - 1], stringList[i])) {
    			 
    			 length++;
    			 
    		 } else {
    			 
    			 if (length > maxLength) {
    				 
    				 maxLength = length;
    				 end = i - 1;
    				 
    			 }
    			 
    			 length = 1;
    		 }
    	 }
    	 
    	 if (length > maxLength) {
    		 
    		 maxLength = length;
    		 
    		 end = stringList.length - 1;
    		 
    		 
    	 
    	 }
    	 
    	 if (maxLength <= 1) {
    		 
    		 return new String[0];
    	 }
    		 
    			 
    			 String[] largest = new String[maxLength];
    			 
    			 for (int i = 0; i < maxLength; i++) {
    				 
    				 largest[i] = stringList[i + end - maxLength + 1].getUnsorted();
    				 
    				 
    				 
    			 }
    			 
    			 return largest; 
    			 
    		 }
    			
    		 
    		 
    		
    	 
  




    /**
     * Reads a list of words from a file, where each line of the file contains a single word.
     * Converts the words to SortedString objects.
     *
     * @param filename the name of the file to read from
     * @return an array of SortedString objects representing the words in the file
     */
    
    
    public static SortedString[] readFile(String filename) {
        ArrayList<SortedString> results = new ArrayList<SortedString>();
        try {
            BufferedReader input = new BufferedReader(new FileReader(filename));
            while(input.ready()) {
                results.add(new SortedString(input.readLine()));
            }
            input.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        SortedString[] retval = new SortedString[1];
        return results.toArray(retval);

    }
    
}
    

