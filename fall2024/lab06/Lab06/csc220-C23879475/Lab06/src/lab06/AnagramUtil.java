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
        // Check if the sorted versions of the two strings are equal
        return str1.getSorted().equals(str2.getSorted());
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

        // Handle edge cases: empty list or single element list
        if (stringList == null || stringList.length == 0) {
            return new String[]{"empty array"};
        }
       
        if (stringList.length == 1) {
            return new String[]{stringList[0].getUnsorted()};
        }

        // Sort the array using InsertionSort (or you could implement MergeSort)
        InsertionSort<SortedString> sorter = new InsertionSort<>();
        stringList = sorter.sort(stringList);

        int maxLength = 0;
        int currentLength = 1;
        int endIndex = 0;

        // Loop through the sorted array to find the largest anagram group
        for (int i = 0; i < stringList.length - 1; i++) {
            if (areAnagrams(stringList[i], stringList[i + 1])) {
                currentLength++;
            } else {
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    endIndex = i;
                }
                currentLength = 1;
            }
        }

        // Handle the last group
        if (currentLength > maxLength) {
            maxLength = currentLength;
            endIndex = stringList.length - 1;
        }

        // If no group of anagrams larger than 1 is found
        if (maxLength == 1) {
            return new String[]{"none"};
        }

        // Prepare the result array
        String[] toReturn = new String[maxLength];
        for (int i = 0; i < maxLength; i++) {
            toReturn[i] = stringList[endIndex - maxLength + 1 + i].getUnsorted();
        }

        return toReturn;
    }

    /**
     * Reads a list of words from a file, where each line of the file contains a single word.
     * Converts the words to SortedString objects.
     *
     * @param filename the name of the file to read from
     * @return an array of SortedString objects representing the words in the file
     */
    public static SortedString[] readFile(String filename) {
        ArrayList<SortedString> results = new ArrayList<>();
        try {
            BufferedReader input = new BufferedReader(new FileReader(filename));
            while (input.ready()) {
                results.add(new SortedString(input.readLine()));
            }
            input.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return results.toArray(new SortedString[results.size()]);
    }
}