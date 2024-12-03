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
        // Compare the sorted versions of both strings
        return str1.getSorted().equals(str2.getSorted());
    }

    public static String[] getLargestAnagramGroup(String filename){
        SortedString[] words = readFile(filename);
        String[] toReturn = getLargestAnagramGroup(words);
        return toReturn;
    }

    /**
     * Finds the largest group of anagrams in a list of SortedString objects.
     *
     * @param stringList an array of SortedString objects
     * @return an array of strings containing the largest group of anagrams
     */
    public static String[] getLargestAnagramGroup(SortedString[] stringList) {
        // Handle edge case: if the input array is empty, return an empty array
        if (stringList.length == 0) {
            return new String[0];
        }

        // Sort the list of SortedString objects using MergeSort
        MergeSort<SortedString> mergeSort = new MergeSort<>();
        stringList = mergeSort.sort(stringList);

        // Rest of the logic remains the same
        // Variables to keep track of the largest group of anagrams
        ArrayList<String> largestGroup = new ArrayList<>();
        ArrayList<String> currentGroup = new ArrayList<>();

        // Start by adding the first word to the current group
        currentGroup.add(stringList[0].getUnsorted());

        // Loop through the sorted list to group anagrams together
        for (int i = 1; i < stringList.length; i++) {
            if (stringList[i].getSorted().equals(stringList[i - 1].getSorted())) {
                currentGroup.add(stringList[i].getUnsorted());
            } else {
                if (currentGroup.size() > largestGroup.size()) {
                    largestGroup = new ArrayList<>(currentGroup);
                }
                currentGroup.clear();
                currentGroup.add(stringList[i].getUnsorted());
            }
        }

        // Final check after the loop
        if (currentGroup.size() > largestGroup.size()) {
            largestGroup = new ArrayList<>(currentGroup);
        }

        // Convert the largest group into an array and return
        return largestGroup.toArray(new String[0]);
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
        SortedString[] retval = new SortedString[1];
        return results.toArray(retval);
    }
}

