package lab06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

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
        // TODO: Lab Part 2
    	//===============================================================================================================================
        return str1.getSorted().equals(str2.getSorted());
        //===============================================================================================================================
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

        //TODO Assignment Part 1
        /* Initialize a sorting algorithm of type SortedString using either
         MergeSort or InsertionSort */

        /* sort the input string list */


        /* The case where stringList is of size 1 or less */


        /* The variables for the logic following */
    	if (stringList.length <= 1) {
            return new String[0];
        }

        
        MergeSort<SortedString> mergeSort = new MergeSort<>();
        SortedString[] sortedList = mergeSort.sort(stringList);

        
        int maxLength = 0, currentLength = 1, endIndex = 0;

        
        for (int i = 1; i < sortedList.length; i++) {
            if (areAnagrams(sortedList[i - 1], sortedList[i])) {
                currentLength++;
            } else {
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    endIndex = i - 1;
                }
                currentLength = 1;
            }
        }

        
        if (currentLength > maxLength) {
            maxLength = currentLength;
            endIndex = sortedList.length - 1;
        }

        
        String[] largestGroup = new String[maxLength];
        for (int j = 0; j < maxLength; j++) {
            largestGroup[j] = sortedList[endIndex - maxLength + 1 + j].getUnsorted();
        }

        return largestGroup;
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
        return results.toArray(new SortedString[0]);
    }
}
