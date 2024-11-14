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
    public static boolean areAnagrams(SortedString str1, SortedString str2) {
        // Two strings are anagrams if their sorted versions are the same
        return str1.compareTo(str2) == 0;
    }

    /**
     * Finds the largest group of anagrams in a file. The file should contain one
     * word per line.
     *
     * @param filename the name of the file containing the list of words
     * @return an array of strings containing the largest group of anagrams
     */
    public static String[] getLargestAnagramGroup(String filename) {
        SortedString[] words = readFile(filename);
        return getLargestAnagramGroup(words);
    }

    /**
     * Finds the largest group of anagrams in a list of SortedString objects.
     *
     * @param stringList an array of SortedString objects
     * @return an array of strings containing the largest group of anagrams
     */
    public static String[] getLargestAnagramGroup(SortedString[] stringList) {
        if (stringList.length <= 1) {
            return new String[0]; // No anagrams if list has 1 or fewer items
        }

        // Sort the list of SortedString objects using merge sort
        MergeSort<SortedString> mergeSort = new MergeSort<>();
        stringList = mergeSort.sort(stringList);

        // Variables to track the largest anagram group
        int maxLength = 0;
        int currentLength = 1;
        int endIndex = 0;

        // Iterate through the sorted array to find the largest group of anagrams
        for (int i = 1; i < stringList.length; i++) {
            if (areAnagrams(stringList[i], stringList[i - 1])) {
                currentLength++;
            } else {
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    endIndex = i - 1;
                }
                currentLength = 1;
            }
        }

        // Final check for the last group of anagrams
        if (currentLength > maxLength) {
            maxLength = currentLength;
            endIndex = stringList.length - 1;
        }

        // Prepare to return the largest group of anagrams
        String[] result = new String[maxLength];
        for (int i = 0; i < maxLength; i++) {
            result[i] = stringList[endIndex - maxLength + 1 + i].getUnsorted();
        }

        return result;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results.toArray(new SortedString[0]);
    }
}
